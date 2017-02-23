package org.irods.jargon.rest.metadatatemplate;

import org.irods.jargon.rest.metadatatemplate.model.*;
import org.irods.jargon.rest.metadatatemplate.ExecuteFormApiService;
import org.irods.jargon.rest.metadatatemplate.factories.ExecuteFormApiServiceFactory;

import org.irods.jargon.rest.metadatatemplate.model.Form;
import org.irods.jargon.rest.metadatatemplate.model.ExecutionResult;

import java.util.List;
import org.irods.jargon.rest.metadatatemplate.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/executeForm")


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-22T16:39:27.094-05:00")
public class ExecuteFormApi  {
   private final ExecuteFormApiService delegate = ExecuteFormApiServiceFactory.getExecuteFormApi();

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response executeForm( Form body,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.executeForm(body,securityContext);
    }
}
