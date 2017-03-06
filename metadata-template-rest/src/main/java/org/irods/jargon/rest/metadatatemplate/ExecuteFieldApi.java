package org.irods.jargon.rest.metadatatemplate;

import org.irods.jargon.rest.metadatatemplate.model.*;
import org.irods.jargon.rest.metadatatemplate.ExecuteFieldApiService;
import org.irods.jargon.rest.metadatatemplate.factories.ExecuteFieldApiServiceFactory;
import org.irods.jargon.rest.metadatatemplate.model.Field;
import org.irods.jargon.rest.metadatatemplate.model.ExecutionResult;

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
@Path("/executeField")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-23T16:25:45.614-05:00")
public class ExecuteFieldApi  {
	@Autowired
	private ExecuteFieldApiService executeFieldApiService;

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response executeField( Field field, @QueryParam("path") String path,@Context SecurityContext securityContext)
    throws NotFoundException {
        return executeFieldApiService.executeField(field,path,securityContext);
    }

	public ExecuteFieldApiService getExecuteFieldApiService() {
		return executeFieldApiService;
	}

	public void setExecuteFieldApiService(
			ExecuteFieldApiService executeFieldApiService) {
		this.executeFieldApiService = executeFieldApiService;
	}
}
