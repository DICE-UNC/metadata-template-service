package org.irods.jargon.rest.metadatatemplate;

import org.irods.jargon.rest.metadatatemplate.model.*;
import org.irods.jargon.rest.metadatatemplate.ExecuteFieldApiService;
import org.irods.jargon.rest.metadatatemplate.factories.ExecuteFieldApiServiceFactory;

import org.irods.jargon.rest.metadatatemplate.model.Field;
import org.irods.jargon.rest.metadatatemplate.model.ExecutionResult;

import java.util.List;
import org.irods.jargon.rest.metadatatemplate.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/executeField")


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-22T16:39:27.094-05:00")
public class ExecuteFieldApi  {
   private final ExecuteFieldApiService delegate = ExecuteFieldApiServiceFactory.getExecuteFieldApi();

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response executeField( Field body,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.executeField(body,securityContext);
    }
}
