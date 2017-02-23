package org.irods.jargon.rest.metadatatemplate.factories;

import org.irods.jargon.rest.metadatatemplate.PingApiService;
import org.irods.jargon.rest.metadatatemplate.impl.PingApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-23T16:25:45.614-05:00")
public class PingApiServiceFactory {

   private final static PingApiService service = new PingApiServiceImpl();

   public static PingApiService getPingApi()
   {
      return service;
   }
}
