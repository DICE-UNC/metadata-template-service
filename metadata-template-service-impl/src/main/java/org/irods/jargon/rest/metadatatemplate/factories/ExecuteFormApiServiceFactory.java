package org.irods.jargon.rest.metadatatemplate.factories;

import org.irods.jargon.rest.metadatatemplate.ExecuteFormApiService;
import org.irods.jargon.rest.metadatatemplate.impl.ExecuteFormApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-23T16:25:45.614-05:00")
public class ExecuteFormApiServiceFactory {

   private final static ExecuteFormApiService service = new ExecuteFormApiServiceImpl();

   public static ExecuteFormApiService getExecuteFormApi()
   {
      return service;
   }
}
