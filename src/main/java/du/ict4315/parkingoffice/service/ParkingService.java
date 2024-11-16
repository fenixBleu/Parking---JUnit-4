/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du.ict4315.parkingoffice.service;

/**
 *
 * @author Bobby Vocque
 */


import du.ict4315.parkingoffice.ParkingOffice;
import du.ict4315.parkingoffice.helper.RegisterCarCommand;
import du.ict4315.parkingoffice.helper.RegisterCustomerCommand;
import du.ict4315.client.ResponseData;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;


public class ParkingService {
  
    private final ParkingOffice parkingOffice;
    
    private static final Logger logger = Logger.getLogger(ParkingService.class.getName());
    
    
    private  HashMap<String, Object> map = new HashMap<String, Object>();
    

    public  ParkingService(ParkingOffice parkingOffice) {
      
      this.parkingOffice = parkingOffice;
      RegisterCustomerCommand customer = new RegisterCustomerCommand(parkingOffice);
      RegisterCarCommand car = new RegisterCarCommand(parkingOffice); 
      map.put(customer.getDisplayName().toUpperCase(), customer);
      map.put(car.getDisplayName().toUpperCase(), car);
    }
    
    public ResponseData handleInput(InputStream in) throws Exception {
        @SuppressWarnings("resource")
        ObjectInputStream objectInputStream = new ObjectInputStream(in);
        String requestData = (String) objectInputStream.readObject();
        //System.out.println("service handleInput: " + requestData);
        return performCommand(requestData);
    }
    
    public ResponseData performCommand(String requestData){
      
      
      ResponseData responseData = new ResponseData();
        
      System.out.println("performCommand: " + requestData);
      //the json data is received as an array, will need to convert
      JSONArray jsonArray = new JSONArray(requestData);
        
      //JSONObject jsonObject = new JSONObject(requestData);
      JSONObject jsonObject = jsonArray.getJSONObject(0);
      String command = jsonObject.getString("command");
      logger.log(Level.INFO, "Performing {0} command", jsonObject.getString("command"));
      
      Object obj = map.get(command.toUpperCase());
     
        
      try {
        Method methd = obj.getClass().getDeclaredMethod("perform", String.class);
        Object[] params = {jsonObject.toString()};
        //Object exObj = methd.invoke(obj, params);
        responseData.setResponse(methd.invoke(obj, params));
        System.out.println("debug");
        
      } catch (NoSuchMethodException | SecurityException | IllegalAccessException |
          IllegalArgumentException | InvocationTargetException ex) {
        //Logger.getLogger(ParkingService.class.getName()).log(Level.SEVERE, null, ex.getMessage());
    	  responseData.setSuccess(false);
    	  
    	  String exObj = ((Object)ex).getClass().getSimpleName();
    	  switch (exObj) {
    	  
    	    case "InvocationTargetException":
    	    	
    	    	Logger.getLogger(ParkingService.class.getName()).log(Level.SEVERE, "Error in JSON data passed to service");
    	    	break;
    	    	
		    default:
			    break;
    		  
    	  
    	  }
    	  //Logger.getLogger(ParkingService.class.getName()).log(Level.SEVERE, command, ex.toString());
        
      }
       
      return responseData;
      
    }
    /*private void register(){
      
      
    }*/
  
    
}
