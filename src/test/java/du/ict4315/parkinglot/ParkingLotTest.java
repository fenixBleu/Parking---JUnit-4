/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package du.ict4315.parkinglot;

import du.ict4315.car.Car;
import du.ict4315.car.CarType;
//import du.ict4315.car.Permit;
import du.ict4315.charges.Money;
import du.ict4315.customer.Address;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import du.ict4315.charges.factory.StandardCharge;
import du.ict4315.charges.factory.DiscountCharge;
import du.ict4315.charges.factory.IChargeStrategy;
//import static du.ict4315.parkinglot.ParkingObserverCapacityTest.car;
//import static du.ict4315.parkinglot.ParkingObserverCapacityTest.car2;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Bobby Vocque
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParkingLotTest {
    
    public static ParkingLot parkingLot;
    private static Car car;
    private static Car car2;
    LocalDateTime ldt;
    DayOfWeek thisDay;
    
    public ParkingLotTest() {
        
        Address lotAddress = new Address("456 B St", "", "Timbuktu", 
                "Veryfar", "00002");
        
        parkingLot = new ParkingLot("testLot", lotAddress,175L, 2, false, false);
        
        
    }
    
    @Test 
    public void testA() {
            
            assertEquals("testLot", parkingLot.getLotId());
            assertEquals(2, parkingLot.getMaxCapacity());
            
    }

    /**
     * Test of entry method, of class ParkingLot.
     */
    @Test
    public void testB() {
        System.out.println("entry");
        car = new Car("AC200", "XYZ-5009", CarType.SUV);
        car2 = new Car("AC201", "LMO-5009", CarType.COMPACT);
        car.newPermit("ABD123", LocalDate.now(), LocalDate.now().plusYears(1l));
        car2.newPermit("DEF456", LocalDate.now(), LocalDate.now().plusYears(1l));
        parkingLot.entry(car);
        assertEquals(1, parkingLot.getRemainingCapacity());
        assertFalse(parkingLot.getIsFull());
        parkingLot.entry(car2);
        assertEquals(0, parkingLot.getRemainingCapacity());
        assertTrue(parkingLot.getIsFull());
        
    }

    /**
     * Test of exit method, of class ParkingLot.
     */
    @Test
    public void testC() {
        System.out.println("exit");
        car = new Car("AC200", "XYZ-5009", CarType.SUV);
        car2 = new Car("AC201", "LMO-5009", CarType.COMPACT);
        car.newPermit("ABD123", LocalDate.now(), LocalDate.now().plusYears(1l));
        car2.newPermit("DEF456", LocalDate.now(), LocalDate.now().plusYears(1l));
        parkingLot.entry(car);
        parkingLot.entry(car2);
        assertTrue(parkingLot.getIsFull());
        parkingLot.exit(car);
        assertFalse(parkingLot.getIsFull());
        assertEquals(1, parkingLot.getRemainingCapacity());
        
        
    }

   

    /**
     * Test of toString method, of class ParkingLot.
     */
    @Test
    public void testD() {
        System.out.println("toString");
        String[] result = parkingLot.toString().split(" ", 0);
        assertEquals(parkingLot.getLotId(), result[0]);
        assertEquals(Integer.toString(parkingLot.getMaxCapacity()), result[1]);
       
    }
    @Test
    public void testE() {
        System.out.println("getRate");
        Long result = parkingLot.getRate();
        
        assertEquals(175L, result.longValue());
        
       
    }
    @Test
    public void testF(){
       System.out.println("set standard strategy");
       parkingLot.setIsDiscounted(Boolean.FALSE);
       IChargeStrategy strat = new StandardCharge();
       
       assertEquals(strat.getClass(), parkingLot.getLotStrategy().getClass());
      
    }
    @Test
    public void testG(){
       System.out.println("set standard strategy");
       parkingLot.setIsDiscounted(Boolean.TRUE);
       IChargeStrategy strat = new DiscountCharge();
       
       assertEquals(strat.getClass(), parkingLot.getLotStrategy().getClass());
      
    }
    
     @Test
    public void testH(){
       System.out.println("clear observers");
       parkingLot.removeAllObservers();
       
       
       assertEquals(0, parkingLot.getObservers().size());
      
    }
     @Test
    public void testJ(){
       System.out.println("add observer");
       ParkingAction observer = new ParkingObserverCapacity();
       parkingLot.registerObserver(observer);
       
       
       assertEquals(3, parkingLot.getObservers().size());
      
    }
     @Test
    public void testK(){
       System.out.println("remove observer");
       ParkingAction observer = parkingLot.getObservers().get(1);
       parkingLot.unregisterObserver(observer);
       
       
       assertEquals(1, parkingLot.getObservers().size());
      
    }
    @Test
    public void testL(){
      
      //Instant entry = Instant.now();
      Instant exit = Instant.now().plus(1, ChronoUnit.HOURS);
      //Instant exit2 = Instant.now().plus(2, ChronoUnit.HOURS);
      Car car = new Car("Tom Saywer", "XYZ-456", CarType.COMPACT);
      //Permit permit = new Permit("ASC123", car, LocalDate.now(), 
         //LocalDate.now().plusYears(1L));
        ldt = LocalDateTime.ofInstant(exit, ZoneOffset.systemDefault());
        thisDay = ldt.getDayOfWeek();
      car.newPermit("XYZ-456", LocalDate.now().minusMonths(2), LocalDate.now().plusYears(1));
      //Address lotAddress = new Address("123 z Street", "", "Acity", "IL","12345");
      //ParkingLot lot = new ParkingLot("Lot01",lotAddress, 175L, 50 );
      //car.
      parkingLot.setIsDiscounted(Boolean.TRUE);
      parkingLot.setRemainingCapacity(4);
      
      parkingLot.entry(car);
      parkingLot.exit(car);
      
      long exp = 175L;
      if ((thisDay.getValue() == 0) || thisDay.getValue() == 6){
        
        exp = 157L;
      }
      
      Money charge = parkingLot.testDecorators();
      long result = charge.getCents();
      assertEquals(exp, result);
      //restore setting
      parkingLot.setRate(175L);
      parkingLot.setRemainingCapacity(11);
      charge = parkingLot.testDecorators();
      exp = 140L;
      result = charge.getCents();
      assertEquals(exp, result);
      //restore setting
      parkingLot.setRate(175L);
      parkingLot.setIsDiscounted(Boolean.FALSE);
      car.setType(CarType.SUV);
      parkingLot.entry(car);
      parkingLot.exit(car);
      charge = parkingLot.testDecorators();
      exp = 175L;
      result = charge.getCents();
      assertEquals(exp, result);
      
    }


}