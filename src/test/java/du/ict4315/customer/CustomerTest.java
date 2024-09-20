/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package du.ict4315.customer;

import du.ict4315.car.Car;
import du.ict4315.car.CarType;
import java.util.List;
import org.junit.Test;
//import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import java.util.Objects;

/**
 *
 * @author Bobby Vocque
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerTest {

  public static Customer testCustomer;
  
  @BeforeClass
  public static void setCustomer() {
    testCustomer = new Customer("007", "James", "Bond");
  }

  public CustomerTest() {

    
  }
  
  public void setMultiCar() {
	  
	    System.out.println("setMultiCar");
	    String license = "ABC-123";
	    CarType type = CarType.SUV;

	    testCustomer.registerCar(license, type);
	    
	    license = "ABC-1234";
	    type = CarType.COMPACT;
	    
	    testCustomer.registerCar(license, type);
	  
  }

  @Test
  public void test1_ID() {

    assertEquals("007", testCustomer.getId());
    testCustomer.setId("Agent99");
    assertEquals("Agent99", testCustomer.getId());
    testCustomer.setId("007");  //put it back
  }

  @Test
  public void test2_Name() {

    System.out.println("Name");

    assertEquals("James Bond", testCustomer.getName());
    testCustomer.setName("Albert", "Einstein");
    assertEquals("Albert Einstein", testCustomer.getName());
    testCustomer.setName("James", "Bond"); //put it back

  }

  @Test
  public void test3_Phone() {
    System.out.println("PhoneNumber");
    testCustomer.setPhoneNumber("9785551212");
    assertEquals("9785551212", testCustomer.getPhoneNumber());

  }

  @Test
  public void test4_Address() {
    System.out.println("Address");
    Address testAddress = new Address("123 A St", "Apt 7", "Gnome",
            "Somewhere", "00001");
    testCustomer.setAddress("123 A St", "Apt 7", "Gnome",
            "Somewhere", "00001");
    assertEquals(testAddress.getAddressInfo(), testCustomer.getAddress().getAddressInfo());

  }

  /**
   * Test of registerCar method, of class Customer.
   */
  @Test
  public void test5_RegisterSingleCar() {
    System.out.println("registerCar");
    String license = "ABC-123";
    CarType type = CarType.SUV;

    List<Car> result = testCustomer.registerCar(license, type);
    assertEquals("007", result.get(result.size() - 1).getOwnerId());
    assertEquals(license, result.get(result.size() - 1).getLicense());
    assertEquals(type, result.get(result.size() - 1).getType());

  }

  @Test
  public void test6_RegisterMultipleCars() {
    System.out.println("registerCars");
    String license = "ABC-1234";
    CarType type = CarType.COMPACT;

    List<Car> result = testCustomer.registerCar(license, type);
    assertEquals("007", result.get(result.size() - 1).getOwnerId());
    assertEquals(license, result.get(result.size() - 1).getLicense());
    assertEquals(type, result.get(result.size() - 1).getType());

  }

  @Test
  public void test7_GetCars() {

    System.out.println("getCars");
    //setMultiCar();
    
    String id = testCustomer.getId();
    List<Car> result = testCustomer.getCars(id);
    assertEquals(2, result.size());
    assertEquals(CarType.SUV, result.get(0).getType());
    assertEquals(CarType.COMPACT, result.get(1).getType());

  }

  @Test
  public void test8_RemoveCar() {
    System.out.println("removeCar");
    
    
    List<Car> result = testCustomer.removeCar("ABC-123");
    assertEquals(1, result.size());
    assertEquals("ABC-1234", result.get(result.size() - 1).getLicense());
  }

  @Test
  public void test9_ModifyLicense() {
    System.out.println("modifyLicense");
    
    setMultiCar();
    
    Car result = testCustomer.modifyLicense("ABC-1234", "DEG-456");
    if (!(Objects.isNull(result))) {
      assertEquals("DEG-456", result.getLicense());
    } else {
      fail("Did not return the rxpected object.");
    }
  }
}
