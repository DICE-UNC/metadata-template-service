package org.irods.jargon.rest.metadatatemplate;

import org.irods.jargon.rest.metadatatemplate.model.*;
import org.irods.jargon.rest.metadatatemplate.BuildFormApiService;
import org.irods.jargon.rest.metadatatemplate.factories.BuildFormApiServiceFactory;

import org.irods.jargon.rest.metadatatemplate.model.Form;
import org.irods.jargon.rest.metadatatemplate.model.MetadataTemplateRequest;

import java.util.List;
import org.irods.jargon.rest.metadatatemplate.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/buildForm")


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-22T16:39:27.094-05:00")
public class BuildFormApi  {
   private final BuildFormApiService delegate = BuildFormApiServiceFactory.getBuildFormApi();

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response buildForm( MetadataTemplateRequest body,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.buildForm(body,securityContext);
    }
}
