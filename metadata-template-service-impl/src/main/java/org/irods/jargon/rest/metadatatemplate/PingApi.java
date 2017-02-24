package org.irods.jargon.rest.metadatatemplate;

import org.irods.jargon.rest.metadatatemplate.model.*;
import org.irods.jargon.rest.metadatatemplate.PingApiService;
import org.irods.jargon.rest.metadatatemplate.factories.PingApiServiceFactory;

import org.irods.jargon.rest.metadatatemplate.model.Ping;

import java.util.List;
import org.irods.jargon.rest.metadatatemplate.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/ping")


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-23T17:08:15.207-05:00")
public class PingApi  {
   private final PingApiService delegate = PingApiServiceFactory.getPingApi();

    @GET
    
    
    @Produces({ "application/json", "application/xml" })
    public Response ping( @QueryParam("midTierOnly") Boolean midTierOnly,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.ping(midTierOnly,securityContext);
    }
}
