package org.irods.jargon.rest.metadatatemplate.factories;

import org.irods.jargon.rest.metadatatemplate.ExecuteFieldApiService;
import org.irods.jargon.rest.metadatatemplate.impl.ExecuteFieldApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-22T16:39:27.094-05:00")
public class ExecuteFieldApiServiceFactory {

   private final static ExecuteFieldApiService service = new ExecuteFieldApiServiceImpl();

   public static ExecuteFieldApiService getExecuteFieldApi()
   {
      return service;
   }
}
