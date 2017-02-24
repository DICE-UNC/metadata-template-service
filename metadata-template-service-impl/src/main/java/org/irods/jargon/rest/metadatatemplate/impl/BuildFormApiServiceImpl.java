package org.irods.jargon.rest.metadatatemplate.impl;

import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.formbot.FormElementEnum;
import org.irods.jargon.rest.metadatatemplate.*;
import org.irods.jargon.rest.metadatatemplate.model.*;
import org.irods.jargon.metadatatemplate.FormBasedMetadataTemplate;
import org.irods.jargon.metadatatemplate.JargonMetadataResolver;
import org.irods.jargon.metadatatemplate.MetadataElement;
import org.irods.jargon.metadatatemplate.MetadataTemplate;
import org.irods.jargon.metadatatemplate.MetadataTemplateParsingException;
import org.irods.jargon.metadatatemplate.MetadataTemplateProcessingException;
import org.irods.jargon.metadatatemplate.TemplateTypeEnum;
import org.irods.jargon.metadatatemplate.ValidationStyleEnum;

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
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-22T16:39:27.094-05:00")
public class BuildFormApiServiceImpl extends BuildFormApiService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IRODSAccessObjectFactory irodsAccessObjectFactory;

	@Override
	public Response buildForm(MetadataTemplateRequest body,
			SecurityContext securityContext) throws NotFoundException {

		JargonMetadataResolver resolver = null;
		MetadataTemplate template = null;
		String uuidString = null;

		if (securityContext == null) {
			// throw new IllegalArgumentException("null securityContext");
			return Response.status(405).entity("null securityContext").build();
		}

		log.info("authentication:{}", SecurityContextHolder.getContext()
				.getAuthentication());
		IrodsAuthentication irodsAuthentication = (IrodsAuthentication) SecurityContextHolder
				.getContext().getAuthentication();

		Form form = new Form();

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

			return Response
					.status(500)
					.entity("Internal server error - Unable to instantiate JargonMetadataResolver")
					.build();
		}

		try {
			if (!body.getUuid().isEmpty()) {
				// Prefer UUID if provided
				template = resolver.findTemplateByUUID(body.getUuid());
			} else if (!body.getFqName().isEmpty()) {
				// Prefer FQ name if no UUID provided
				template = resolver.findTemplateByFqName(body.getFqName());
			} else if (!body.getName().isEmpty()
					&& !body.getActiveDir().isEmpty()) {
				template = resolver.findTemplateByName(body.getName(),
						body.getActiveDir());
			} else {
				return Response
						.status(400)
						.entity("Ill-formed request - insufficient information provided to find a Metadata Template")
						.build();
			}
		} catch (FileNotFoundException e) {
			log.error("Metadata template file not found");

			return Response.status(404).entity("File not found").build();
		} catch (MetadataTemplateParsingException e) {
			log.error("Error parsing metadata template file JSON");

			return Response
					.status(500)
					.entity("Internal server error - Error parsing metadata template file JSON")
					.build();
		} catch (MetadataTemplateProcessingException e) {
			log.error("Error when processing metadata template file");

			return Response
					.status(500)
					.entity("Internal server error - Error processing metadata template file")
					.build();
		} catch (IOException e) {
			log.error("IOException when trying to load metadata template file");

			return Response
					.status(500)
					.entity("Internal server error - IOException when trying to load metadata template file")
					.build();
		}

		form.setName(template.getName());
		form.setDescription(template.getDescription());
		form.setUniqueId(uuidString);

		if (template.getType() == TemplateTypeEnum.FORM_BASED) {
			FormBasedMetadataTemplate fbmt = (FormBasedMetadataTemplate) template;
			for (MetadataElement me : fbmt.getElements()) {
				Field field = new Field();
				field.setName(me.getName());
				field.setDescription(me.getDescription());
				field.setType(me.getType());
				field.setCurrentValue(me.getCurrentValue());
				field.setDisplayValue(me.getDisplayValue());
				field.setDefaultValue(me.getDefaultValue());

				// uniqueId is template UUID + field name
				// i.e. 01234567-0123-0123-0123-0123456789abFieldName
				// UUID is in first 36 characters
				String uniqueId = uuidString + me.getName();
				field.setUniqueId(uniqueId);

				for (String s : me.getValidationOptions()) {
					field.getParamList().add(s);
				}

				// TODO if me.renderingOptions exists, should really look at
				// that instead. But it seems pretty redundant at this point.
				switch (me.getType()) {
				case RAW_STRING:
					if (me.getValidationStyle() == ValidationStyleEnum.IN_LIST) {
						field.setFormElement(FormElementEnum.SELECT);
					} else {
						field.setFormElement(FormElementEnum.TEXT);
					}

					break;
				case RAW_TEXT:
					field.setFormElement(FormElementEnum.TEXT_AREA);

					break;
				case RAW_URL:
					field.setFormElement(FormElementEnum.URL);

					break;
				case RAW_INT:
				case RAW_FLOAT:
					if (me.getValidationStyle() == ValidationStyleEnum.IN_LIST) {
						field.setFormElement(FormElementEnum.SELECT);
					} else if (me.getValidationStyle() == ValidationStyleEnum.IN_RANGE
							|| me.getValidationStyle() == ValidationStyleEnum.IN_RANGE_EXCLUSIVE) {
						field.setFormElement(FormElementEnum.RANGE);
					} else {
						field.setFormElement(FormElementEnum.NUMBER);
					}

					break;
				case RAW_BOOLEAN:
					field.setFormElement(FormElementEnum.CHECK_BOX);

					break;
				case RAW_DATE:
					field.setFormElement(FormElementEnum.DATE);

					break;
				case RAW_TIME:
					field.setFormElement(FormElementEnum.TIME);

					break;
				case RAW_DATETIME:
					field.setFormElement(FormElementEnum.DATETIME);

					break;
				case REF_IRODS_QUERY:
				case REF_IRODS_CATALOG:
					field.setFormElement(FormElementEnum.TEXT);

					break;
				case REF_URL:
					field.setFormElement(FormElementEnum.URL);

					break;
				case LIST_STRING:
					if (me.getValidationStyle() == ValidationStyleEnum.IN_LIST) {
						field.setFormElement(FormElementEnum.SELECT_MULTIPLE);
					} else {
						field.setFormElement(FormElementEnum.TEXT);
					}

					break;
				case LIST_INT:
				case LIST_FLOAT:
					if (me.getValidationStyle() == ValidationStyleEnum.IN_LIST) {
						field.setFormElement(FormElementEnum.SELECT_MULTIPLE);
					} else if (me.getValidationStyle() == ValidationStyleEnum.IN_RANGE
							|| me.getValidationStyle() == ValidationStyleEnum.IN_RANGE_EXCLUSIVE) {
						field.setFormElement(FormElementEnum.RANGE);
					} else {
						field.setFormElement(FormElementEnum.NUMBER);
					}

					break;
				default:
					field.setFormElement(FormElementEnum.TEXT);

					break;
				}

				form.getFields().add(field);
			}
		} // TODO else if (other type of MetadataTemplate)

		return Response.status(200).entity(form).build();
	}

	public IRODSAccessObjectFactory getIrodsAccessObjectFactory() {
		return irodsAccessObjectFactory;
	}

	public void setIrodsObjectFactory(
			IRODSAccessObjectFactory irodsAccessObjectFactory) {
		this.irodsAccessObjectFactory = irodsAccessObjectFactory;
	}
}
