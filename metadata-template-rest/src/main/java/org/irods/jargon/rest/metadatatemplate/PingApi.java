package org.irods.jargon.rest.metadatatemplate;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/ping")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-23T17:08:15.207-05:00")
public class PingApi {
	@Autowired
	private PingApiService pingApiService;

	@GET
	@Produces({ "application/json", "application/xml" })
	public Response ping(@QueryParam("midTierOnly") Boolean midTierOnly,
			@Context SecurityContext securityContext) throws NotFoundException,
			org.irods.jargon.rest.metadatatemplate.NotFoundException {
		return pingApiService.ping(midTierOnly, securityContext);
	}

	/**
	 * @return the pingApiService
	 */
	public PingApiService getPingApiService() {
		return pingApiService;
	}

	/**
	 * @param pingApiService
	 *            the pingApiService to set
	 */
	public void setPingApiService(PingApiService pingApiService) {
		this.pingApiService = pingApiService;
	}
}
