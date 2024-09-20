/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du.ict4315.parkingoffice.helper;

/**
 *
 * @author Bobby vocque
 */

import du.ict4315.customer.Customer;
import du.ict4315.customer.CustomerName;
import du.ict4315.customer.Address;
import du.ict4315.parkingoffice.ParkingOffice;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterCustomerCommand implements Command {
  
  private ParkingOffice office;
  private int numParams = 0;
  
  private static final Logger logger = Logger.getLogger(RegisterCustomerCommand.class.getName());
  
  public RegisterCustomerCommand(ParkingOffice office){
    
    this.office = office;
    
  }
  @Override
  public String getCommandName(){
    
    return this.getClass().getSimpleName();
    
    
  }
  @Override
  public String getDisplayName() {
    
    return "CUSTOMER";
    
  }
  @Override
  public Customer perform(String parameters){
    
    JSONObject jsonObject = new JSONObject(parameters);
      
                Address customerAddress = new Address( 
                        jsonObject.getString("Address 1") ,jsonObject.optString("Address 2", null),
                        jsonObject.getString("City"), jsonObject.getString("State"), 
                        jsonObject.getString("Zipcode"));
               try{
                 
                 Customer c = office.register(jsonObject.getString("First Name"),
                     jsonObject.getString("Last Name"), customerAddress, jsonObject.optString("Phone number") );
                 
                 System.out.println ("customer name: " + c.getName());
                 return c;
                  
                 
               }  catch (JSONException ex) {
                    Logger.getLogger(ParkingOffice.class.getName()).log(Level.SEVERE, null, ex);
                    return null;
               }
  }
               
               
  
}
