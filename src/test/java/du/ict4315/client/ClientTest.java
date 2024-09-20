/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package du.ict4315.client;

//import java.util.Map;
//import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;
//import java.util.HashMap;
//import java.util.Map;
//import org.junit.Test;
//import du.ict4315.server.Server;
//import org.junit.Before;
import org.junit.Ignore;


/**
 *
 * @author vocqueb
 */
public class ClientTest {
  
  
    
    public ClientTest() {
       
    }
    @Ignore //must be manually run due to server
    @Test
  public void testRunCommand() throws Exception {
    System.out.println("runCommand");
    String command[] = {"CUSTOMER", "firstname=Rob", "lastname=Zombie", "address1= 123 Main St", 
      "city=Smallville", "state=IN", "zipcode=12345", "phonenumber=(999)555-1212"};
    String command2[] = {"CUSTOMER", "firstname=Bill", "lastname=Blaster", "address1= 33 Acme St", 
      "city=Industry", "state=NJ", "zipcode=12345", "phonenumber=(999)555-1213"};
    String command3[] = {"CUSTOMER", "firstname=George", "lastname=Jungle", "address1= 123 Main St", 
      "city=Smallville", "state=IN", "zipcode=12345", "phonenumber=(999)555-1214"};
    String command4[] = {"CUSTOMER", "firstname=Jed", "lastname=Clampet", "address1= 123 Main St", 
      "city=Smallville", "state=IN", "zipcode=12345", "phonenumber=(999)555-1215"};
    
    String expResult = "";
    Client.main(command);
    Client.main(command2);
    Client.main(command3);
    Client.main(command4);
    Client.main(command);
    //Client.runCommand( data)
    assertEquals(expResult, expResult);
    // TODO review the generated test code and remove the default call to fail.
    //fail("The test case is a prototype.");
  }
}


