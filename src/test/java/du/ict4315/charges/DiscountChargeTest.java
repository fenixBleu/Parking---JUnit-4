/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package du.ict4315.charges;

import du.ict4315.charges.factory.DiscountCharge;
import du.ict4315.car.Car;
import du.ict4315.car.CarType;
import du.ict4315.car.Permit;
import du.ict4315.customer.Address;
import du.ict4315.parkinglot.ParkingLot;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vocqueb
 */
public class DiscountChargeTest {
  
  public static final Instant incurred = Instant.now();
  public static final Instant exit = Instant.now().plus(1, ChronoUnit.HOURS);
  public static final Instant exit2 = Instant.now().plus(2, ChronoUnit.HOURS);
  public static Car car = new Car("Tom Saywer", "XYZ-456", CarType.SUV);
  public static Permit permit = new Permit("ASC123", car, LocalDate.now(), 
      LocalDate.now().plusYears(1L));
  private static Address lotAddress = new Address("123 z Street", "", "Acity", "IL","12345");
  private static ParkingLot lot = new ParkingLot("Lot01",lotAddress, 175L, 50, false,false );
  
  public DiscountChargeTest() {
  }

  /**
   * Test of calculateCharge method, of class DiscountCharge.
   */
  @Test
  public void testCalculateCharge0() {
    System.out.println("calculateCharge 1hour");
    
    lot.setIsDiscounted(Boolean.TRUE);
    lot.setIsHourlyRate(Boolean.FALSE);
    
    DiscountCharge instance = new DiscountCharge();
    Money expResult = new Money(140L);
    Money result = instance.calculateCharge(lot, incurred, exit, permit);
    assertEquals(expResult.getCents(), result.getCents());
    assertEquals(expResult.getDollars(), result.getDollars(), 0);
    
    
  }
  @Test
  public void testCalculateCharge1() {
    System.out.println("calculateCharge 1hour");
    
    lot.setIsDiscounted(Boolean.TRUE);
    lot.setIsHourlyRate(Boolean.TRUE);
    
    DiscountCharge instance = new DiscountCharge();
    Money expResult = new Money(140L);
    Money result = instance.calculateCharge(lot, incurred, exit, permit);
    assertEquals(expResult.getCents(), result.getCents());
    assertEquals(expResult.getDollars(), result.getDollars(), 0);
    
    
  }
  @Test
  public void testCalculateCharge2() {
    System.out.println("calculateCharge 2hour");
    
    lot.setIsDiscounted(Boolean.TRUE);
    lot.setIsHourlyRate(Boolean.TRUE);
    
    DiscountCharge instance = new DiscountCharge();
    Money expResult = new Money(280L);
    Money result = instance.calculateCharge(lot, incurred, exit2, permit);
    assertEquals(expResult.getCents(), result.getCents());
    assertEquals(expResult.getDollars(), result.getDollars(), 0);
    
    
  }

  /**
   * Test of setRateMult method, of class DiscountCharge.
   */
  @Test
  public void testCalculateCharge3() {
    System.out.println("setRateMult");
    lot.setIsDiscounted(Boolean.TRUE);
    double rateMult = 0.7;
    DiscountCharge instance = new DiscountCharge();
    instance.setRateMult(rateMult);
    Money expResult = new Money(122L);
    Money result = instance.calculateCharge(lot, incurred, exit, permit);
    assertEquals(rateMult, instance.getRateMult(), 0);
    assertEquals(expResult.getCents(), result.getCents());
  }

  /**
   * Test of getRateMult method, of class DiscountCharge.
   */
  @Test
  public void testCalculateCharge4() {
    System.out.println("compactCalc with discount");
    DiscountCharge instance = new DiscountCharge();
    lot.setIsDiscounted(Boolean.TRUE);
    permit.getCar().setType(CarType.COMPACT);
    Money expResult = new Money(112L);
    Money result = instance.calculateCharge(lot, incurred, exit, permit);
    assertEquals(expResult.getDollars(), result.getDollars(), 0);
    
  }
  @Test
  public void testCalculateCharge5() {
    System.out.println("noDiscount");
    DiscountCharge instance = new DiscountCharge();
    permit.getCar().setType(CarType.SUV);
    Money expResult = new Money(140L);
    Money result = instance.calculateCharge(lot, incurred, exit, permit);
    assertEquals(expResult.getDollars(), result.getDollars(), 0);
  }
  @Test
  public void testCalculateCharge6() {
    System.out.println("enter = exit");
    DiscountCharge instance = new DiscountCharge();
    lot.setIsDiscounted(Boolean.TRUE);
    Money expResult = new Money(140L);
    Money result = instance.calculateCharge(lot, incurred, incurred, permit);
    assertEquals(expResult.getDollars(), result.getDollars(), 0);
  }
  
}
