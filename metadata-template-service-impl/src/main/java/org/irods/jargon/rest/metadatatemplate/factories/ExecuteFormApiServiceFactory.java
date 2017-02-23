package org.irods.jargon.rest.metadatatemplate.factories;

import org.irods.jargon.rest.metadatatemplate.ExecuteFormApiService;
import org.irods.jargon.rest.metadatatemplate.impl.ExecuteFormApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-22T16:39:27.094-05:00")
public class ExecuteFormApiServiceFactory {

   private final static ExecuteFormApiService service = new ExecuteFormApiServiceImpl();

   public static ExecuteFormApiService getExecuteFormApi()
   {
      return service;
   }
}
