package org.irods.jargon.rest.metadatatemplate;

import org.irods.jargon.rest.metadatatemplate.model.*;
import org.irods.jargon.rest.metadatatemplate.ValidateFormApiService;
import org.irods.jargon.rest.metadatatemplate.factories.ValidateFormApiServiceFactory;

import org.irods.jargon.rest.metadatatemplate.model.Form;
import org.irods.jargon.rest.metadatatemplate.model.ValidationResult;

import java.util.List;
import org.irods.jargon.rest.metadatatemplate.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/validateForm")


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-22T16:39:27.094-05:00")
public class ValidateFormApi  {
   private final ValidateFormApiService delegate = ValidateFormApiServiceFactory.getValidateFormApi();

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response validateForm( Form body,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.validateForm(body,securityContext);
    }
}
