/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package du.ict4315.charges;

import du.ict4315.charges.factory.ParkingChargeStrategy;
import du.ict4315.car.Car;
import du.ict4315.car.CarType;
import du.ict4315.car.Permit;
import du.ict4315.customer.Address;
import du.ict4315.parkinglot.ParkingLot;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vocqueb
 */
public class ParkingTransactionsTest {
  public static final Instant incurred = Instant.now();
  public static final Instant exit = Instant.now().plus(1, ChronoUnit.HOURS);
  public static Car car = new Car("Tom Saywer", "XYZ-456", CarType.SUV);
  public static Permit permit = new Permit("ASC123", car, LocalDate.now(), 
      LocalDate.now().plusYears(1L));
  private static Address lotAddress = new Address("123 z Street", "", "Acity", "IL","12345");
  private static ParkingLot lot = new ParkingLot("Lot01",lotAddress, 175L, 50, false, false);
  //private static ParkingLot lot2 = new ParkingLot("Lot02",lotAddress, 175L, 50, false, false );
  LocalDateTime ldt;
  DayOfWeek thisDay;
  
  public ParkingTransactionsTest() {
  }

  /**
   * Test of getCharge method, of class ParkingTransactions.
   */
  @Test
  public void testGetChargeDiscount() {
    System.out.println("getCharge - Discount");
    lot.setIsDiscounted(Boolean.TRUE);
    ParkingChargeStrategy instance = new ParkingChargeStrategy(lot,incurred, exit, permit);
    Money expResult = new Money(140L);
    Money result = instance.getCharge();
    assertEquals(expResult.getDollars(), result.getDollars(), 0);
    
  }
  @Test
  public void testGetChargeStandard() {
    System.out.println("getCharge - Discount");
    lot.setIsDiscounted(Boolean.FALSE);
    ParkingChargeStrategy instance = new ParkingChargeStrategy(lot,incurred, exit, permit);
    Money expResult = new Money(175L);
    Money result = instance.getCharge();
    assertEquals(expResult.getDollars(), result.getDollars(), 0);
    
  }
  
}
