/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package du.ict4315.parkingoffice.service;

import du.ict4315.client.ResponseData;
import du.ict4315.customer.Address;
import du.ict4315.parkingoffice.ParkingOffice;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;
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
  @Ignore //these are now testted through client
  @Test
  public void testCustCommand() {
    System.out.println("performCommand: Customer");
    String command[] = {"CUSTOMER", "firstname=Rob", "lastname=Zombie", "address1= 123 Main St", 
      "city=Smallville", "state=IN", "zipcode=12345", "phonenumber=(999)555-1212"};
    JSONArray jArray = new JSONArray(command);
    JSONObject json = new JSONObject(jArray.get(0).toString());
    
    ParkingService instance = new ParkingService(office);
    ResponseData result = instance.performCommand(json.toString());
    String expResult = "Customer Registered";
    assertEquals(expResult, result);
    
    
  }
  @Ignore
  @Test
  public void testInvalidCustCommand() {
    System.out.println("performCommand: Cust wrong number of args");
    String[] args = {"Willy Wonka", "123 Abc St", "Home" , "IL" , "12345" , "4445551212"};
  
    JSONArray jArray = new JSONArray(args);
    JSONObject json = new JSONObject(jArray.get(0).toString());
    
    ParkingService instance = new ParkingService(office);
    ResponseData result = instance.performCommand(json.toString());
    String expResult = "Customer Registered";
    assertNotEquals(expResult, result);
    
    
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
