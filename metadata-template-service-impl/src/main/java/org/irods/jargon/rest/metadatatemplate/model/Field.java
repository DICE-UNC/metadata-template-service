package org.irods.jargon.rest.metadatatemplate.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.List;




@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-22T16:39:27.094-05:00")
public class Field   {
  
  private String name = null;
  private String uniqueId = null;
  private String description = null;
  private String type = null;
  private String formElement = null;
  private List<String> currentValue = new ArrayList<String>();
  private List<String> defaultValue = new ArrayList<String>();
  private List<String> displayValue = new ArrayList<String>();
  private List<String> paramList = new ArrayList<String>();

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
  
  @JsonProperty("uniqueId")
  public String getUniqueId() {
    return uniqueId;
  }
  public void setUniqueId(String uniqueId) {
    this.uniqueId = uniqueId;
  }

  /**
   **/
  
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   **/
  
  @JsonProperty("type")
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

  /**
   **/
  
  @JsonProperty("formElement")
  public String getFormElement() {
    return formElement;
  }
  public void setFormElement(String formElement) {
    this.formElement = formElement;
  }

  /**
   **/
  
  @JsonProperty("currentValue")
  public List<String> getCurrentValue() {
    return currentValue;
  }
  public void setCurrentValue(List<String> currentValue) {
    this.currentValue = currentValue;
  }

  /**
   **/
  
  @JsonProperty("defaultValue")
  public List<String> getDefaultValue() {
    return defaultValue;
  }
  public void setDefaultValue(List<String> defaultValue) {
    this.defaultValue = defaultValue;
  }

  /**
   **/
  
  @JsonProperty("displayValue")
  public List<String> getDisplayValue() {
    return displayValue;
  }
  public void setDisplayValue(List<String> displayValue) {
    this.displayValue = displayValue;
  }

  /**
   **/
  
  @JsonProperty("paramList")
  public List<String> getParamList() {
    return paramList;
  }
  public void setParamList(List<String> paramList) {
    this.paramList = paramList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Field field = (Field) o;
    return Objects.equals(name, field.name) &&
        Objects.equals(uniqueId, field.uniqueId) &&
        Objects.equals(description, field.description) &&
        Objects.equals(type, field.type) &&
        Objects.equals(formElement, field.formElement) &&
        Objects.equals(currentValue, field.currentValue) &&
        Objects.equals(defaultValue, field.defaultValue) &&
        Objects.equals(displayValue, field.displayValue) &&
        Objects.equals(paramList, field.paramList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, uniqueId, description, type, formElement, currentValue, defaultValue, displayValue, paramList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Field {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    uniqueId: ").append(toIndentedString(uniqueId)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    formElement: ").append(toIndentedString(formElement)).append("\n");
    sb.append("    currentValue: ").append(toIndentedString(currentValue)).append("\n");
    sb.append("    defaultValue: ").append(toIndentedString(defaultValue)).append("\n");
    sb.append("    displayValue: ").append(toIndentedString(displayValue)).append("\n");
    sb.append("    paramList: ").append(toIndentedString(paramList)).append("\n");
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

