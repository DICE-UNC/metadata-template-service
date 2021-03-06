package org.irods.jargon.rest.metadatatemplate;

import org.irods.jargon.rest.metadatatemplate.model.*;
import org.irods.jargon.rest.metadatatemplate.ExecuteFormApiService;
import org.irods.jargon.rest.metadatatemplate.factories.ExecuteFormApiServiceFactory;
import org.irods.jargon.rest.metadatatemplate.model.Form;
import org.irods.jargon.rest.metadatatemplate.model.ExecutionResult;

import java.util.List;

import org.irods.jargon.rest.metadatatemplate.NotFoundException;
import org.springframework.stereotype.Component;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Component
@Path("/executeForm")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-23T16:25:45.614-05:00")
public class ExecuteFormApi {
	private ExecuteFormApiService executeFormApiService;

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response executeForm(Form form, @QueryParam("path") String path,
			@Context SecurityContext securityContext) throws NotFoundException {
		return executeFormApiService.executeForm(form, path, securityContext);
	}

	public ExecuteFormApiService getExecuteFormApiService() {
		return executeFormApiService;
	}

	public void setExecuteFormApiService(ExecuteFormApiService executeFormApiService) {
		this.executeFormApiService = executeFormApiService;
	}
}
