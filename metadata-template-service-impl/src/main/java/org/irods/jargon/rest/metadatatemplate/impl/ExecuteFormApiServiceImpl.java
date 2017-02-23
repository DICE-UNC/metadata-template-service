package org.irods.jargon.rest.metadatatemplate.impl;

import org.irods.jargon.rest.metadatatemplate.*;
import org.irods.jargon.rest.metadatatemplate.model.*;


import org.irods.jargon.rest.metadatatemplate.model.Form;
import org.irods.jargon.rest.metadatatemplate.model.ExecutionResult;

import java.util.List;
import org.irods.jargon.rest.metadatatemplate.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-22T16:39:27.094-05:00")
public class ExecuteFormApiServiceImpl extends ExecuteFormApiService {
      @Override
      public Response executeForm(Form body,SecurityContext securityContext)
      throws NotFoundException {
      // do some magic!
      return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
  }
}
