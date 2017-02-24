package org.irods.jargon.rest.metadatatemplate.impl;

import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.EnvironmentalInfoAO;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.rest.security.IrodsAuthentication;
import org.irods.jargon.rest.metadatatemplate.*;
import org.irods.jargon.rest.metadatatemplate.model.*;

import org.irods.jargon.rest.metadatatemplate.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Component
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-23T17:08:15.207-05:00")
public class PingApiServiceImpl extends PingApiService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IRODSAccessObjectFactory irodsAccessObjectFactory;
	
      @Override
  	/**
  	 * Send back a heartbeat ping
  	 * 
  	 * @param midTierOnly
  	 *            <code>boolean</code>. If <code>true</code>, don't ping iRODS,
  	 *            if <code>false</code>, iRODS is pinged and the round trip time
  	 *            is returned
  	 * @param securityContext
  	 * @return {@link Response}
  	 * @throws NotFoundException
  	 */
      public Response ping(Boolean midTierOnly,SecurityContext securityContext)
      throws NotFoundException {
  		log.info("ping()");
  		if (midTierOnly == null) {
  			midTierOnly = false;
  		}

  		if (securityContext == null) {
 // 			throw new IllegalArgumentException("null securityContext");
  			return Response.status(405).entity("null securityContext").build();
  		}

  		log.info("authentication:{}", SecurityContextHolder.getContext()
  				.getAuthentication());
  		IrodsAuthentication irodsAuthentication = (IrodsAuthentication) SecurityContextHolder
  				.getContext().getAuthentication();

  		float millis = 0.0f;
  		if (!midTierOnly) {
  			log.info("doing a ping!");
  			log.info("authentication:{}", securityContext.getUserPrincipal());
  			long startTime = System.currentTimeMillis();
  			log.info("startTime:{}", startTime);
  			try {
  				log.info("accessing irods");
  				EnvironmentalInfoAO environmentalInfoAO = irodsAccessObjectFactory
  						.getEnvironmentalInfoAO(irodsAuthentication
  								.getIrodsAccount());
  				environmentalInfoAO.getIRODSServerCurrentTime();
  				log.info("got properties");
  			} catch (JargonException e) {
  				log.error("Error pinging back end irods server", e);
 // 				throw new IrodsRestException(e);
  				return Response.status(500).entity("Error pinging back end irods server").build();
  			}
  			long endTime = System.currentTimeMillis();
  			millis = endTime - startTime;
  			log.info("endTime:{}", endTime);

  		}

  		log.info("principal:{}", securityContext.getUserPrincipal());

  		Ping ping = new Ping();
  		ping.setPingTime(millis);

  		return Response.status(200).entity(ping).build();
  }
      
  	/**
  	 * @return the irodsAccessObjectFactory
  	 */
  	public IRODSAccessObjectFactory getIrodsAccessObjectFactory() {
  		return irodsAccessObjectFactory;
  	}

  	/**
  	 * @param irodsAccessObjectFactory
  	 *            the irodsAccessObjectFactory to set
  	 */
  	public void setIrodsAccessObjectFactory(
  			IRODSAccessObjectFactory irodsAccessObjectFactory) {
  		this.irodsAccessObjectFactory = irodsAccessObjectFactory;
  	}
}
