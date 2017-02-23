package org.irods.jargon.rest.metadatatemplate.factories;

import org.irods.jargon.rest.metadatatemplate.BuildFormApiService;
import org.irods.jargon.rest.metadatatemplate.impl.BuildFormApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-22T16:39:27.094-05:00")
public class BuildFormApiServiceFactory {

   private final static BuildFormApiService service = new BuildFormApiServiceImpl();

   public static BuildFormApiService getBuildFormApi()
   {
      return service;
   }
}
