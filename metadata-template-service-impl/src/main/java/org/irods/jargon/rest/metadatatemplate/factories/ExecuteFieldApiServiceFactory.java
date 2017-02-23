package org.irods.jargon.rest.metadatatemplate.factories;

import org.irods.jargon.rest.metadatatemplate.ExecuteFieldApiService;
import org.irods.jargon.rest.metadatatemplate.impl.ExecuteFieldApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-23T16:25:45.614-05:00")
public class ExecuteFieldApiServiceFactory {

   private final static ExecuteFieldApiService service = new ExecuteFieldApiServiceImpl();

   public static ExecuteFieldApiService getExecuteFieldApi()
   {
      return service;
   }
}
