package org.irods.jargon.rest.metadatatemplate.impl;

import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.formbot.FormBotValidationEnum;
import org.irods.jargon.formbot.FormBotValidationResult;
import org.irods.jargon.metadatatemplate.FormBasedMetadataTemplate;
import org.irods.jargon.metadatatemplate.JargonMetadataResolver;
import org.irods.jargon.metadatatemplate.MetadataElement;
import org.irods.jargon.metadatatemplate.MetadataTemplate;
import org.irods.jargon.metadatatemplate.MetadataTemplateParsingException;
import org.irods.jargon.metadatatemplate.MetadataTemplateProcessingException;
import org.irods.jargon.metadatatemplate.TemplateTypeEnum;
import org.irods.jargon.metadatatemplate.ValidationReturnEnum;
import org.irods.jargon.metadatatemplate.ValidatorSingleton;
import org.irods.jargon.rest.metadatatemplate.*;
import org.irods.jargon.rest.metadatatemplate.model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.irods.jargon.rest.metadatatemplate.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-22T16:39:27.094-05:00")
public class ValidateFieldApiServiceImpl extends ValidateFieldApiService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private IRODSAccessObjectFactory irodsAccessObjectFactory;

	@Override
	public Response validateField(Field body, SecurityContext securityContext)
			throws NotFoundException {

		ValidationResult validationResult = null;

		if (body.getName().isEmpty() || body.getUniqueId().isEmpty()) {
			log.error("Insufficient information to validate field: both name and uniqueId must be populated");

			validationResult = new ValidationResult(
					FormBotValidationEnum.ERROR,
					"Ill-formed request - both name and uniqueId must be populated");
			return Response
					.status(400)
					.entity(validationResult)
					.build();
		}


		JargonMetadataResolver resolver = null;
		MetadataTemplate template = null;
		
		try {
			resolver = new JargonMetadataResolver(irodsAccount,
					irodsAccessObjectFactory);
		} catch (JargonException e) {
			log.error(
					"JargonException: JargonMetadataResolver could not be created",
					e);
		}
		
		if (resolver == null) {
			log.error("Unable to instantiate JargonMetadataResolver");
			
			validationResult = new ValidationResult(
					FormBotValidationEnum.ERROR,
					"Could not create JargonMetadataResolver");
			
			return Response
					.status(500)
					.entity(validationResult)
					.build();
		}

		try {
			template = resolver.findTemplateByUUID(body.getUniqueId());
		} catch (MetadataTemplateParsingException e) {
			log.error("MetadataTemplateParsingException: Error parsing metadata template");

			validationResult = new ValidationResult(
					FormBotValidationEnum.ERROR,
					"Error parsing metadata template");
			
			return Response
					.status(500)
					.entity(validationResult)
					.build();
		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException: Metadata template not found");
			
			validationResult = new ValidationResult(
					FormBotValidationEnum.ERROR,
					"Metadata template not found");
			
			return Response
					.status(404)
					.entity(validationResult)
					.build();
		} catch (MetadataTemplateProcessingException e) {
			log.error("MetadataTemplateProcessingException: Error processing metadata template");

			validationResult = new ValidationResult(
					FormBotValidationEnum.ERROR,
					"Error processing metadata template");
			
			return Response
					.status(500)
					.entity(validationResult)
					.build();
		} catch (IOException e) {
			log.error("IOException: Error reading metadata template from disk");

			validationResult = new ValidationResult(
					FormBotValidationEnum.ERROR,
					"Error reading metadata template from disk");
			
			return Response
					.status(500)
					.entity(validationResult)
					.build();
		}

		if (template.getType() == TemplateTypeEnum.FORM_BASED) {
			FormBasedMetadataTemplate fbmt = (FormBasedMetadataTemplate) template;
			for (MetadataElement me : fbmt.getElements()) {
				if (me.getName().equalsIgnoreCase(body.getName())) {
					me.setCurrentValue(body.getCurrentValue());
					ValidationReturnEnum validationReturn = ValidatorSingleton.VALIDATOR
							.validate(irodsAccount, irodsAccessObjectFactory,
									me);

					FormBotValidationEnum fbv;
					if (validationReturn == ValidationReturnEnum.SUCCESS) {
						fbv = FormBotValidationEnum.SUCCESS;
					} else if ((validationReturn == ValidationReturnEnum.NOT_VALIDATED)
							|| (validationReturn == ValidationReturnEnum.REGEX_FAILED)) {
						fbv = FormBotValidationEnum.NOT_VALIDATED;
					} else {
						fbv = FormBotValidationEnum.FAILURE;
					}

					validationResult = new ValidationResult(fbv,
							validationReturn.toString());

					return Response
							.status(200)
							.entity(validationResult)
							.build();
				}
			}
		} // TODO else if (other type of MetadataTemplate)

		validationResult = new ValidationResult(
				FormBotValidationEnum.ERROR,
				"Field not found");
		
		return Response
				.status(404)
				.entity(validationResult)
				.build();
	}
}
