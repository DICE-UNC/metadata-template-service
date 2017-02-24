package org.irods.jargon.rest.metadatatemplate.impl;

import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.formbot.FormBotExecutionEnum;
import org.irods.jargon.formbot.FormBotExecutionResult;
import org.irods.jargon.metadatatemplate.FormBasedMetadataTemplate;
import org.irods.jargon.metadatatemplate.JargonMetadataExporter;
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
import java.util.List;

import org.irods.jargon.rest.metadatatemplate.NotFoundException;
import org.irods.jargon.rest.security.IrodsAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Component
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-23T16:25:45.614-05:00")
public class ExecuteFormApiServiceImpl extends ExecuteFormApiService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IRODSAccessObjectFactory irodsAccessObjectFactory;

	@Override
	public Response executeForm(Form form, String path,
			SecurityContext securityContext) throws NotFoundException {
		List<ExecutionResult> executionResultList = new ArrayList<ExecutionResult>();

		if (securityContext == null) {
			// throw new IllegalArgumentException("null securityContext");
			return Response.status(405).entity("null securityContext").build();
		}

		if (form == null || form.getName().isEmpty() || form.getUniqueId().isEmpty()) {
			log.error("Insufficient information to execute field: both name and uniqueId must be populated");

			executionResultList.add(new ExecutionResult(
					FormBotExecutionEnum.ERROR,
					"Ill-formed request - both name and uniqueId in field must be populated"));
			return Response.status(400).entity(executionResultList).build();
		}
		
		
		if (path == null || path.isEmpty()){
			log.error("Insufficient information to execute field: path must be populated");

			executionResultList.add(new ExecutionResult(
					FormBotExecutionEnum.ERROR,
					"Ill-formed request - path must be populated must be populated"));
			return Response.status(400).entity(executionResultList).build();
		}

		JargonMetadataResolver resolver = null;
		JargonMetadataExporter exporter = null;
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

			executionResultList.add(new ExecutionResult(
					FormBotExecutionEnum.ERROR,
					"Could not create JargonMetadataResolver"));

			return Response.status(500).entity(executionResultList).build();
		}
		
		exporter = new JargonMetadataExporter(irodsAccessObjectFactory,
				irodsAuthentication.getIrodsAccount());

		try {
			template = resolver.findTemplateByUUID(form.getUniqueId());
		} catch (MetadataTemplateParsingException e) {
			log.error("MetadataTemplateParsingException: Error parsing metadata template");

			executionResultList.add(new ExecutionResult(
					FormBotExecutionEnum.ERROR,
					"Error parsing metadata template"));

			return Response.status(500).entity(executionResultList).build();
		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException: Metadata template not found");

			executionResultList.add(new ExecutionResult(
					FormBotExecutionEnum.ERROR, "Metadata template not found"));

			return Response.status(404).entity(executionResultList).build();
		} catch (MetadataTemplateProcessingException e) {
			log.error("MetadataTemplateProcessingException: Error processing metadata template");

			executionResultList.add(new ExecutionResult(
					FormBotExecutionEnum.ERROR,
					"Error processing metadata template"));

			return Response.status(500).entity(executionResultList).build();
		} catch (IOException e) {
			log.error("IOException: Error reading metadata template from disk");

			executionResultList.add(new ExecutionResult(
					FormBotExecutionEnum.ERROR,
					"Error reading metadata template from disk"));

			return Response.status(500).entity(executionResultList).build();
		}
		
		boolean validationFailed = false;
		boolean error = false;

		if (template.getType() == TemplateTypeEnum.FORM_BASED) {
			FormBasedMetadataTemplate fbmt = (FormBasedMetadataTemplate) template;
			for (Field field : form.getFields()) {
				for (MetadataElement me : fbmt.getElements()) {
					if (me.getName().equalsIgnoreCase(field.getName())) {
						me.setCurrentValue(field.getCurrentValue());
						ValidationReturnEnum validationReturn = ValidatorSingleton.VALIDATOR
								.validate(irodsAuthentication.getIrodsAccount(),
										irodsAccessObjectFactory, me);
						if (validationReturn == ValidationReturnEnum.SUCCESS
								|| validationReturn == ValidationReturnEnum.NOT_VALIDATED
								|| validationReturn == ValidationReturnEnum.REGEX_SYNTAX_ERROR) {
							try {
								exporter.saveElementToSystemMetadataOnObject(
										me, path);
							} catch (JargonException e) {
								log.error("JargonException when trying to add metadata to data object");
								executionResultList
										.add(new ExecutionResult(
												FormBotExecutionEnum.ERROR,
												"JargonException when adding metadata to data obj"));
								error = true;
								break;
							}

							executionResultList.add(new ExecutionResult(
									FormBotExecutionEnum.SUCCESS,
									validationReturn.toString()));
							break;
						} else {
							executionResultList.add(new ExecutionResult(
									FormBotExecutionEnum.VALIDATION_FAILED,
									validationReturn.toString()));
							validationFailed = true;
							break;
						}
					}
				}
			}
		} // TODO else if (other type of MetadataTemplate)

		if (error) {
			executionResultList.add(0, new ExecutionResult(
					FormBotExecutionEnum.ERROR,
					"At least one field generated an error"));
		} else if (validationFailed) {
			executionResultList.add(0, new ExecutionResult(
					FormBotExecutionEnum.VALIDATION_FAILED,
					"At least one field failed validation"));
		} else {
			executionResultList.add(0, new ExecutionResult(
					FormBotExecutionEnum.SUCCESS,
					"Metadata added to iRODS object"));
		}
		
		return Response.status(200).entity(executionResultList).build();	
	}

	public IRODSAccessObjectFactory getIrodsAccessObjectFactory() {
		return irodsAccessObjectFactory;
	}

	public void setIrodsObjectFactory(
			IRODSAccessObjectFactory irodsAccessObjectFactory) {
		this.irodsAccessObjectFactory = irodsAccessObjectFactory;
	}
}
