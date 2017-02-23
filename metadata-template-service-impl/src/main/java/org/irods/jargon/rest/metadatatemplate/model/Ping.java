package org.irods.jargon.rest.metadatatemplate.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;




@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2017-02-23T16:25:45.614-05:00")
public class Ping   {
  
  private Float pingTime = null;

  /**
   * milliseconds to connect to iRODS and get a response from the perspective of the mid-tier service
   **/
  
  @JsonProperty("pingTime")
  public Float getPingTime() {
    return pingTime;
  }
  public void setPingTime(Float pingTime) {
    this.pingTime = pingTime;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ping ping = (Ping) o;
    return Objects.equals(pingTime, ping.pingTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pingTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ping {\n");
    
    sb.append("    pingTime: ").append(toIndentedString(pingTime)).append("\n");
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

