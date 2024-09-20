/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package du.ict4315.charges.decorator;

import du.ict4315.car.Car;
import du.ict4315.car.CarType;
import du.ict4315.car.Permit;
import du.ict4315.charges.Money;
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
public class FlatRateDecoratorTest {
  
  public static final Instant entry = Instant.now();
  public static final Instant exit = Instant.now().plus(1, ChronoUnit.HOURS);
  public static final Instant exit2 = Instant.now().plus(2, ChronoUnit.HOURS);
  public static Car car = new Car("Tom Saywer", "XYZ-456", CarType.COMPACT);
  public static Permit permit = new Permit("ASC123", car, LocalDate.now(), 
      LocalDate.now().plusYears(1L));
  private static Address lotAddress = new Address("123 z Street", "", "Acity", "IL","12345");
  private static ParkingLot lot = new ParkingLot("Lot01",lotAddress, 175L, 50, false, false );
  
  
  public FlatRateDecoratorTest() {
  }

  
  

  /**
   * Test of getParkingCharge method, of class FlatRateDecorator.
   */
  @Test
  public void testGetParkingCharge() {
    System.out.println("getParkingCharge");
    FlatRateDecorator instance =  new FlatRateDecorator(new RateCalculator());
    Money expResult = new Money(175L);
    Money result = instance.getParkingCharge(entry, exit, lot, permit);
    assertEquals(expResult.getCents(), result.getCents());
  }
  
}
