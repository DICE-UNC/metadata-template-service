package org.irods.jargon.rest.metadatatemplate;

import org.irods.jargon.rest.metadatatemplate.model.*;
import org.irods.jargon.rest.metadatatemplate.ValidateFieldApiService;
import org.irods.jargon.rest.metadatatemplate.factories.ValidateFieldApiServiceFactory;
import org.irods.jargon.rest.metadatatemplate.model.Field;
import org.irods.jargon.rest.metadatatemplate.model.ValidationResult;

import java.util.List;

import org.irods.jargon.rest.metadatatemplate.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Component
@Path("/validateField")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-22T16:39:27.094-05:00")
public class ValidateFieldApi  {
	@Autowired
	private ValidateFieldApiService validateFieldApiService;

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response validateField( Field body,@Context SecurityContext securityContext)
    throws NotFoundException {
        return validateFieldApiService.validateField(body,securityContext);
    }

	public ValidateFieldApiService getValidateFieldApiService() {
		return validateFieldApiService;
	}

	public void setValidateFieldApiService(
			ValidateFieldApiService validateFieldApiService) {
		this.validateFieldApiService = validateFieldApiService;
	}
}
