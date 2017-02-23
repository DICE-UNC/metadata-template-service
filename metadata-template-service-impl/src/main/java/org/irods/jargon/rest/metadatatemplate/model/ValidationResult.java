package org.irods.jargon.rest.metadatatemplate.model;

import java.util.Objects;
import java.util.ArrayList;

import org.irods.jargon.formbot.FormBotValidationEnum;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;




@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-22T16:39:27.094-05:00")
public class ValidationResult   {
  
  private FormBotValidationEnum code = null;
  private String message = null;
  
  public ValidationResult(FormBotValidationEnum code, String message) {
	  this.code = code;
	  this.message = message;
  }

  /**
   **/
  
  @JsonProperty("code")
  public FormBotValidationEnum getCode() {
    return code;
  }
  public void setCode(FormBotValidationEnum code) {
    this.code = code;
  }

  /**
   **/
  
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidationResult validationResult = (ValidationResult) o;
    return Objects.equals(code, validationResult.code) &&
        Objects.equals(message, validationResult.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidationResult {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

