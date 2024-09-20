/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package du.ict4315.car;

import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;
import du.ict4315.customer.Customer;
//import du.ict4315.customer.CustomerName;

/**
 *
 * @author vocqueb
 */
public class PermitTest {
  
  static Car car;
  static Customer cust;
  
  public PermitTest() {
    
    cust = new Customer("AL123", "Al", "Bundy");
    car = new Car("Al Bundy", "ABC-123", CarType.SUV);
    
  }

  /**
   * Test of getId method, of class Permit.
   */
  @Test
  public void testGetId() {
    System.out.println("getId");
    String expResult = cust.getId();
    Permit instance = new Permit(expResult, car, LocalDate.now(),
      LocalDate.now().plusYears(1L));
    
    String result = instance.getId();
    assertEquals(expResult, result);
    
  }

  /**
   * Test of setID method, of class Permit.
   */
  @Test
  public void testSetID() {
    System.out.println("setID");
    String id = "XYZ900";
    Permit instance = new Permit("ABC123", car, LocalDate.now(),
      LocalDate.now().plusYears(1L));
    
    instance.setID(id);
    
    assertEquals(id, instance.getId());
  }

  /**
   * Test of isExpired method, of class Permit.
   */
  @Test
  public void testIsExpired() {
    System.out.println("isExpired = false");
    Permit instance = new Permit("ABC123", car, LocalDate.now(),
    LocalDate.now().plusDays(1L));
    boolean expResult = false;
    boolean result = instance.isExpired();
    assertEquals(expResult, result);
    
  }
  @Test
  public void testIsExpired2() {
    System.out.println("isExpired = true");
    Permit instance = new Permit("ABC123", car, LocalDate.now(),
    LocalDate.now().minusDays(1L));
    boolean expResult = true;
    boolean result = instance.isExpired();
    assertEquals(expResult, result);
    
  }

  /**
   * Test of renewPermit method, of class Permit.
   */
  @Test
  public void testRenewPermit() {
    System.out.println("renewPermit");
    Permit instance = new Permit("ABC123", car, LocalDate.now(),
    LocalDate.now().minusDays(1L));
    LocalDate permitRenew = LocalDate.now().plusDays(1L);
    LocalDate permitExpire = permitRenew.plusYears(1L);
    
    instance.renewPermit(permitRenew, permitExpire);
 
    assertEquals(permitRenew, instance.getRegistration());
    assertEquals(permitExpire, instance.getExpiration());
    assertFalse(instance.isExpired());
    
  }

  
  
  /**
   * Test of equals method, of class Permit.
   */
  @Test
  public void testEqualsA() {
    System.out.println("equals self");
    Permit instance = new Permit("ABC123", car, LocalDate.now(),
    LocalDate.now().plusDays(1L));
    
    
    boolean expResult = true;
    boolean result = instance.equals(instance);
    assertEquals(expResult, result);
    
  }
  @Test
  public void testEqualsB() {
    System.out.println("equals other");
    Permit instance = new Permit("ABC123", car, LocalDate.now(),
    LocalDate.now().plusDays(1L));
    
    Permit obj = new Permit("ABC123", car, LocalDate.now(),
    LocalDate.now().plusDays(1L));
    
    boolean expResult = true;
    boolean result = instance.equals(obj);
    assertEquals(expResult, result);
    
  }
  @Test
  public void testEqualsC() {
    System.out.println("not equals id");
    Permit instance = new Permit("ABC123", car, LocalDate.now(),
    LocalDate.now().plusDays(1L));
    
    Permit obj = new Permit("ABC124", car, LocalDate.now(),
    LocalDate.now().plusDays(1L));
    
    boolean expResult = false;
    boolean result = instance.equals(obj);
    assertEquals(expResult, result);
    
  }
  @Test
  public void testEqualsD() {
    System.out.println("not equals registration date");
    Permit instance = new Permit("ABC123", car, LocalDate.now(),
    LocalDate.now().plusDays(1L));
    
    Permit obj = new Permit("ABC123", car, LocalDate.now().minusDays(1L),
    LocalDate.now().plusDays(1L));
    
    boolean expResult = false;
    boolean result = instance.equals(obj);
    assertEquals(expResult, result);
    
  }
  @Test
  public void testEqualsE() {
    System.out.println("not equals expiration date");
    Permit instance = new Permit("ABC123", car,  LocalDate.now(),
    LocalDate.now().plusDays(1L));
    
    Permit obj = new Permit("ABC123", car, LocalDate.now(),
    LocalDate.now().plusDays(2L));
    
    boolean expResult = false;
    boolean result = instance.equals(obj);
    assertEquals(expResult, result);
    
  }

  /**
   * Test of hashCode method, of class Permit.
   */
  @Test
  public void testHashCodeA() {
    System.out.println("hashCode");
    Permit instance = new Permit("ABC123", car, LocalDate.now(),
    LocalDate.now().plusDays(1L));
    
    Permit instanceB = new Permit("ABC123", car,  LocalDate.now(),
    LocalDate.now().plusDays(1L));
    
    int expResult = instanceB.hashCode();
    int result = instance.hashCode();
    assertEquals(expResult, result);
    
  }
  
   @Test
  public void testHashCodeB() {
    System.out.println("hashCode not equal id");
    Permit instance = new Permit("ABC123", car, LocalDate.now(),
    LocalDate.now().plusDays(1L));
    
    Permit instanceB = new Permit("ABC124", car, LocalDate.now(),
    LocalDate.now().plusDays(1L));
    
    int expResult = instanceB.hashCode();
    int result = instance.hashCode();
    assertFalse(expResult == result);
    
  }
   @Test
  public void testHashCodeC() {
    System.out.println("hashCode not equal registraion");
    Permit instance = new Permit("ABC123", car, LocalDate.now(),
    LocalDate.now().plusDays(1L));
    
    Permit instanceB = new Permit("ABC123", car, LocalDate.now().minusDays(1L),
    LocalDate.now().plusDays(1L));
    
    int expResult = instanceB.hashCode();
    int result = instance.hashCode();
    assertFalse(expResult == result);
    
  }
   @Test
  public void testHashCodeD() {
    System.out.println("hashCode not equal expiration");
    Permit instance = new Permit("ABC123", car, LocalDate.now(),
    LocalDate.now().plusDays(1L));
    
    Permit instanceB = new Permit("ABC123", car, LocalDate.now(),
    LocalDate.now().plusDays(2L));
    
    int expResult = instanceB.hashCode();
    int result = instance.hashCode();
    assertFalse(expResult == result);
    
  }
}
