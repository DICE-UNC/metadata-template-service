package org.irods.jargon.rest.metadatatemplate.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;




@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-22T16:39:27.094-05:00")
public class MetadataTemplateRequest   {
  
  private String uuid = null;
  private String fqName = null;
  private String name = null;
  private String activeDir = null;

  /**
   **/
  
  @JsonProperty("uuid")
  public String getUuid() {
    return uuid;
  }
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  /**
   **/
  
  @JsonProperty("fqName")
  public String getFqName() {
    return fqName;
  }
  public void setFqName(String fqName) {
    this.fqName = fqName;
  }

  /**
   **/
  
  @JsonProperty("name")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  /**
   **/
  
  @JsonProperty("activeDir")
  public String getActiveDir() {
    return activeDir;
  }
  public void setActiveDir(String activeDir) {
    this.activeDir = activeDir;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataTemplateRequest metadataTemplateRequest = (MetadataTemplateRequest) o;
    return Objects.equals(uuid, metadataTemplateRequest.uuid) &&
        Objects.equals(fqName, metadataTemplateRequest.fqName) &&
        Objects.equals(name, metadataTemplateRequest.name) &&
        Objects.equals(activeDir, metadataTemplateRequest.activeDir);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, fqName, name, activeDir);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MetadataTemplateRequest {\n");
    
    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
    sb.append("    fqName: ").append(toIndentedString(fqName)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    activeDir: ").append(toIndentedString(activeDir)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

