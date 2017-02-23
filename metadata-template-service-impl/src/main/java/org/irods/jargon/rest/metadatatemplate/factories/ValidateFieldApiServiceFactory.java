package org.irods.jargon.rest.metadatatemplate.factories;

import org.irods.jargon.rest.metadatatemplate.ValidateFieldApiService;
import org.irods.jargon.rest.metadatatemplate.impl.ValidateFieldApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-22T16:39:27.094-05:00")
public class ValidateFieldApiServiceFactory {

   private final static ValidateFieldApiService service = new ValidateFieldApiServiceImpl();

   public static ValidateFieldApiService getValidateFieldApi()
   {
      return service;
   }
}
