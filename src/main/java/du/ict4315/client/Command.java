/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package du.ict4315.client;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
/**
 *
 * @author student
 */

public class Command {
  private final String name;
  private final String command;
  private final List<String> fieldNames;
  
  public Command(String name, String command, List<String> fieldNames) {
    this.name = name;
    this.command = command;
    this.fieldNames = Collections.unmodifiableList(fieldNames);
  }
  
  public String name() {
    return name;
  }
  public String command(){
      return command;
  }
  
  public List<String> fieldNames() {
    return fieldNames;
  }

  public String execute(JSONObject data) {
    try {
      return Client.runCommand(data);
    } catch (IOException e) {
      e.printStackTrace();
      return e.getMessage();
    }
  }
}
