/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du.ict4315.parkingoffice.helper;

import du.ict4315.car.Car;
import du.ict4315.car.CarType;
import du.ict4315.customer.Address;
import du.ict4315.customer.Customer;
import du.ict4315.parkingoffice.ParkingOffice;
import du.ict4315.parkingoffice.service.ParkingService;
import du.ict4315.car.Permit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author vocqueb
 */
public class RegisterCarCommand implements Command {
  
  private ParkingOffice office;
  
  
  private static final Logger logger = Logger.getLogger(RegisterCarCommand.class.getName());
  
  public RegisterCarCommand(ParkingOffice office){
    
    
    
    this.office = office;
    
  }
  @Override
  public String getCommandName(){
    
    return this.getClass().getSimpleName();
    
    
  }
  @Override
  public String getDisplayName() {
    
    return "Car";
    
  }
  @Override
  public Permit perform(String parameters){
     
    JSONObject jsonObject = new JSONObject(parameters);  
                
        
          
            try {
              
             return office.register(jsonObject.getString("Customer Id"), jsonObject.getString("License"), 
                 jsonObject.getString("COMPACT/SUV"));
                  
              
            }  catch (Exception ex) {
                    Logger.getLogger(ParkingOffice.class.getName()).log(Level.SEVERE, null, ex);
                    return null;
                }
             
        }
  
  
}
