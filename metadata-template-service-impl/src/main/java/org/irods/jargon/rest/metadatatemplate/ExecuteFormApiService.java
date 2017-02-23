package org.irods.jargon.rest.metadatatemplate;

import org.irods.jargon.rest.metadatatemplate.*;
import org.irods.jargon.rest.metadatatemplate.model.*;


import org.irods.jargon.rest.metadatatemplate.model.Form;
import org.irods.jargon.rest.metadatatemplate.model.ExecutionResult;

import java.util.List;
import org.irods.jargon.rest.metadatatemplate.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-23T16:25:45.614-05:00")
public abstract class ExecuteFormApiService {
      public abstract Response executeForm(Form form,String path,SecurityContext securityContext)
      throws NotFoundException;
}
