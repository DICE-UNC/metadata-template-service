package org.irods.jargon.rest.metadatatemplate.factories;

import org.irods.jargon.rest.metadatatemplate.ValidateFormApiService;
import org.irods.jargon.rest.metadatatemplate.impl.ValidateFormApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-22T16:39:27.094-05:00")
public class ValidateFormApiServiceFactory {

   private final static ValidateFormApiService service = new ValidateFormApiServiceImpl();

   public static ValidateFormApiService getValidateFormApi()
   {
      return service;
   }
}
