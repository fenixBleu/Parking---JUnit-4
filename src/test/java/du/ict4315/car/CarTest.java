/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package du.ict4315.car;


import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;



/**
 *
 * @author Bobby Vocque
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarTest {
  
   static Car testCar;
  
  public CarTest() {
    
    
    //System.out.println("newCar");
    String ownerId = "owner01";
    String license = "cde-456";
    CarType type = CarType.COMPACT;
    testCar = new Car(ownerId, license, type);
    
  }

  /**
   * Test of Constructor method, of class Car.
   */
  @Test
  public void testA() {
    System.out.println("newCar");
    String ownerId = "owner01";
    String license = "cde-456";
    CarType type = CarType.COMPACT;
    assertEquals(ownerId, testCar.getOwnerId());
    assertEquals(license, testCar.getLicense());
    assertEquals(type, testCar.getType());
  }

  /**
   * Test of setType method, of class Car.
   */
  @Test
  public void testB() {
    System.out.println("setType");
    //note changing from compact to suv
    CarType type = CarType.SUV;
    testCar.setType(type);
    CarType result = testCar.getType();
    assertEquals(type, result);
  }

  /**
   * Test of getPermit method, of class Car.
   */
  @Test
  public void testC() {
    System.out.println("testPermit");
    
    //create and test new permit
    String permit = "AB123456";
    LocalDate date = LocalDate.of(2022, 12, 26);
    testCar.newPermit(permit, date, date.plusYears(1L));
    
    assertEquals(permit, testCar.getPermit().getId());
    assertEquals(date, testCar.getPermit().getRegistration());
    assertEquals(date.plusYears(1L), testCar.getPermit().getExpiration());
    
  }

  
  /**
   * Test of getLicense method, of class Car.
   */
  @Test
  public void testF() {
    System.out.println("setLicense");
    String license = "YNT-5432";
    testCar.setLicense(license);
    String result =  testCar.getLicense();
    assertEquals(license, result);
    
  }
  /**
   * Test of setLicense method, of class Car.
   */
  @Test
  public void testG() {
    System.out.println("toString");
    //System.out.println(testCar.toString());
    String result[] = testCar.toString().split(" ");
    
    assertEquals(testCar.getOwnerId(), result[0]);
    assertEquals(testCar.getLicense(), result[1]);
    CarType type = testCar.getType();
    assertEquals(type.toString(), result[2]);
    
    
  }
}
 

  
  
  
 