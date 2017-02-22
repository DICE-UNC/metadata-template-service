### generating code

```
java -jar /home/mconway/javalib/swagger-codegen/swagger-codegen-cli-2.2.1.jar generate \
 -i https://raw.githubusercontent.com/DICE-UNC/irods_rest_services/master/base/base.yaml \
  -l jaxrs-resteasy \
  --model-package org.irods.jargon.rest.base.model \
  --api-package org.irods.jargon.rest.base \
  -o target/codegen


```