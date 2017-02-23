package org.irods.jargon.rest.metadatatemplate;

import org.irods.jargon.rest.metadatatemplate.*;
import org.irods.jargon.rest.metadatatemplate.model.*;


import org.irods.jargon.rest.metadatatemplate.model.Field;
import org.irods.jargon.rest.metadatatemplate.model.ExecutionResult;

import java.util.List;
import org.irods.jargon.rest.metadatatemplate.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-22T16:39:27.094-05:00")
public abstract class ExecuteFieldApiService {
      public abstract Response executeField(Field body,SecurityContext securityContext)
      throws NotFoundException;
}
