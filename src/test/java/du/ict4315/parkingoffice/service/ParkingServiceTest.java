/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package du.ict4315.parkingoffice.service;

import du.ict4315.client.Command;
import du.ict4315.client.Client;
import du.ict4315.client.ResponseData;
import du.ict4315.customer.Address;
import du.ict4315.parkingoffice.ParkingOffice;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;


/**
 *
 * @author vocqueb
 */
public class ParkingServiceTest {
  
  static ParkingOffice office = new ParkingOffice();
 
  
  public ParkingServiceTest() {
    
    office.setName("TestOffice");
    
    
  }

 
   /* Test of performCommand method, of class ParkingService.
   */
   //looking at simplifying.
  @Test
  public void testCustCommand() {
	  
    System.out.println("performCommand: Customer");
    String customer[] = {"CUSTOMER", "firstname=Rob", "lastname=Zombie", "address1= 123 Main St", "city=Smallville", "state=IN", "zipcode=12345", "phonenumber=9995551212"};
    
    		
    //https://www.selenium.dev/selenium-ide/
    //Need to determine the command
    Command command = Client.commands().get(customer[0]);
    
   //{command=CUSTOMER, First Name=Tom, Last Name=Sawyer, Phone number=9786967524, Address 1=123 ABC St, Address 2=, City=Dallas, State=TX, Zipcode=73737}
    //will change the string, just wanting to go through this exercise for the momemtn.
    Map<String, String> values = new LinkedHashMap<>();
    values.put("command", command.command());
    
    for (String label : command.fieldNames()) {
        for (int i = 0; i < customer.length; ++i) {
            if (customer[i].startsWith(label.replaceAll(" ", "").toLowerCase())) {
                values.put(label, customer[i].replaceAll(".*=", ""));
                break;
            }
        }
    }
    
    
    
    //make a json obeject
    JSONObject json = new JSONObject(values);
    
    //transform it into a json array
    JSONArray jArray = new JSONArray();
    jArray.put(json);
    
    
    //send it to parking service and expect success.
    ParkingService instance = new ParkingService(office);
    ResponseData result = instance.performCommand(jArray.toString());
    boolean expResult = true;
    assertEquals(expResult, result.getSuccess());
    
    
  }
  
  @Test
  public void testInvalidCustCommand() {
    System.out.println("performCommand: Cust wrong number of args");
    String[] args = {"CUSTOMER", "firstname=Willy", "lastname=Wonka", "address1=123 Abc St", "state=IL" , "zipcode=12345" , "phonenumber=4445551212"};
    
  //Need to determine the command
    Command command = Client.commands().get(args[0]);
    
  //will change the string, just wanting to go through this exercise for the momemtn.
    Map<String, String> values = new LinkedHashMap<>();
    values.put("command", command.command());
    
    for (String label : command.fieldNames()) {
        for (int i = 0; i < args.length; ++i) {
            if (args[i].startsWith(label.replaceAll(" ", "").toLowerCase())) {
                values.put(label, args[i].replaceAll(".*=", ""));
                break;
            }
        }
    }
    
   //make a json object
    JSONObject json = new JSONObject(values);
    
    //transform it into a json array
    JSONArray jArray = new JSONArray();
    jArray.put(json);
  
    ParkingService instance = new ParkingService(office);
    ResponseData result = instance.performCommand(jArray.toString());
    System.out.println(result.getSuccess());
    //Boolean expResult = false;
    assertEquals(false, result.getSuccess());
    
    
  }
  @Ignore
  @Test
  public void testCarCommand() {
    System.out.println("performCommand: Car");
    String[] args = {"Willy", "Wonka", "123 Abc St", "" ,  "Home" , "IL" , "12345" , "4445551212"};
    String nameOffice = "Parking Office";
        ParkingOffice office = new ParkingOffice();
        office.setName(nameOffice);
        
    JSONArray jArray = new JSONArray(args);
    JSONObject json = new JSONObject(jArray.get(0).toString());
    
    ParkingService instance = new ParkingService(office);
    ResponseData result = instance.performCommand(json.toString(0));
    
    String[] args2 = {"Willy Wonka", "ABC-123", "Compact"};
    //String result = instance.performCommand("Car", args2);
    String expResult = "Car Registered";
    //assertEquals(expResult, result);
    
  }
  @Ignore
  @Test
  public void testInvalidCarCommand() {
    System.out.println("performCommand: Car, wrong arguments");
    String[] args = {"Willy Wonka", "123 Abc St", "" ,  "Home" , "IL" , "12345" , "4445551212"};
    String nameOffice = "Parking Office";
        ParkingOffice office = new ParkingOffice();
        office.setName(nameOffice);
    ParkingService instance = new ParkingService(office);
    //instance.performCommand("Customer", args);
    
    String[] args2 = {"Willy Wonka", "ABC-123"};
   // String result = instance.performCommand("Car", args2);
    String expResult = "Car Registered";
    //assertNotEquals(expResult, result);
    
  }
  
}
