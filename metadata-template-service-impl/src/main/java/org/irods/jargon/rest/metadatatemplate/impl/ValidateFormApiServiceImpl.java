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
import org.irods.jargon.rest.security.IrodsAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Component
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-22T16:39:27.094-05:00")
public class ValidateFormApiServiceImpl extends ValidateFormApiService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IRODSAccessObjectFactory irodsAccessObjectFactory;

	@Override
	public Response validateForm(Form body, SecurityContext securityContext)
			throws NotFoundException {

		List<ValidationResult> returnList = new ArrayList<ValidationResult>();

		if (securityContext == null) {
			// throw new IllegalArgumentException("null securityContext");
			return Response.status(405).entity("null securityContext").build();
		}

		if (body.getUniqueId().isEmpty() || body.getFields().isEmpty()) {
			log.error("Insufficient information to validate form: both uniqueId and fields list must be populated");

			returnList
					.add(new ValidationResult(FormBotValidationEnum.ERROR,
							"Ill-formed request - both name and uniqueId must be populated"));
			return Response.status(400).entity(returnList).build();
		}

		JargonMetadataResolver resolver = null;
		MetadataTemplate template = null;

		log.info("authentication:{}", SecurityContextHolder.getContext()
				.getAuthentication());
		IrodsAuthentication irodsAuthentication = (IrodsAuthentication) SecurityContextHolder
				.getContext().getAuthentication();

		try {
			resolver = new JargonMetadataResolver(
					irodsAuthentication.getIrodsAccount(),
					irodsAccessObjectFactory);
		} catch (JargonException e) {
			log.error(
					"JargonException: JargonMetadataResolver could not be created",
					e);
		}

		if (resolver == null) {
			log.error("Unable to instantiate JargonMetadataResolver");

			returnList.add(new ValidationResult(FormBotValidationEnum.ERROR,
					"Could not create JargonMetadataResolver"));

			return Response.status(500).entity(returnList).build();
		}

		try {
			template = resolver.findTemplateByUUID(body.getUniqueId());
		} catch (MetadataTemplateParsingException e) {
			log.error("MetadataTemplateParsingException: Error parsing metadata template");

			returnList.add(new ValidationResult(FormBotValidationEnum.ERROR,
					"Error parsing metadata template"));

			return Response.status(500).entity(returnList).build();
		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException: Metadata template not found");

			returnList.add(new ValidationResult(FormBotValidationEnum.ERROR,
					"Metadata template not found"));

			return Response.status(404).entity(returnList).build();
		} catch (MetadataTemplateProcessingException e) {
			log.error("MetadataTemplateProcessingException: Error processing metadata template");

			returnList.add(new ValidationResult(FormBotValidationEnum.ERROR,
					"Error processing metadata template"));

			return Response.status(500).entity(returnList).build();
		} catch (IOException e) {
			log.error("IOException: Error reading metadata template from disk");

			returnList.add(new ValidationResult(FormBotValidationEnum.ERROR,
					"Error reading metadata template from disk"));

			return Response.status(500).entity(returnList).build();
		}

		boolean validationFailed = false;

		if (template.getType() == TemplateTypeEnum.FORM_BASED) {
			FormBasedMetadataTemplate fbmt = (FormBasedMetadataTemplate) template;
			for (Field field : body.getFields()) {
				for (MetadataElement me : fbmt.getElements()) {
					if (me.getName().equalsIgnoreCase(field.getName())) {
						me.setCurrentValue(field.getCurrentValue());
						ValidationReturnEnum validationReturn = ValidatorSingleton.VALIDATOR
								.validate(
										irodsAuthentication.getIrodsAccount(),
										irodsAccessObjectFactory, me);
						if ((validationReturn == ValidationReturnEnum.SUCCESS)
								|| (validationReturn == ValidationReturnEnum.NOT_VALIDATED)
								|| (validationReturn == ValidationReturnEnum.REGEX_SYNTAX_ERROR)) {
							returnList.add(new ValidationResult(
									FormBotValidationEnum.SUCCESS,
									validationReturn.toString()));
							break;
						} else {
							returnList.add(new ValidationResult(
									FormBotValidationEnum.FAILURE,
									validationReturn.toString()));
							validationFailed = true;
							break;
						}
					}
				}
			}
		} // TODO else if (other type of MetadataTemplate)

		if (validationFailed) {
			returnList.add(0, new ValidationResult(
					FormBotValidationEnum.FAILURE,
					"At least one field failed validation"));
		} else {
			returnList.add(0, new ValidationResult(
					FormBotValidationEnum.SUCCESS,
					"All fields passed validation"));
		}

		return Response.status(200).entity(returnList).build();
	}

	public IRODSAccessObjectFactory getIrodsAccessObjectFactory() {
		return irodsAccessObjectFactory;
	}

	public void setIrodsObjectFactory(
			IRODSAccessObjectFactory irodsAccessObjectFactory) {
		this.irodsAccessObjectFactory = irodsAccessObjectFactory;
	}
}
