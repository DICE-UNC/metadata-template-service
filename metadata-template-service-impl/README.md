### generating code

```
java -jar /Users/rskarbez/javalib/swagger-codegen-cli-2.2.1.jar generate \
 -i https://raw.githubusercontent.com/DICE-UNC/jargon-extensions/development/metadata-templates/swagger/swagger.yaml \
  -l jaxrs-resteasy \
  --model-package org.irods.jargon.rest.metadatatemplate.model \
  --api-package org.irods.jargon.rest.metadatatemplate \
  -o target/codegen


```