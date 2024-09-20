/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package du.ict4315.parkinglot;

import du.ict4315.car.Car;
import du.ict4315.car.CarType;
import du.ict4315.car.Permit;
import du.ict4315.charges.Money;
import du.ict4315.customer.Address;
import du.ict4315.customer.Customer;
//import static du.ict4315.parkinglot.ParkingObserverCapacityTest.car;
//import static du.ict4315.parkinglot.ParkingObserverCapacityTest.car2;
//import du.ict4315.parkingoffice.ParkingOffice;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author vocqueb
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParkingObserverTransactionTest {
    
    static Address lotAddress = new Address("456 B St", "", "Timbuktu", 
                "Veryfar", "00002");
    ParkingLot lot = new ParkingLot("testLot", lotAddress,175L, 2, false, false);
    
    //static ParkingOffice office = new ParkingOffice("test");
    
    static Customer customer = new Customer("AL123", "Al", "Bundy");
    
    static Car car = new Car("Al Bundy", "ABC-123", CarType.SUV);
    
    static Car car2 = new Car("Paul Bunyon", "ABC-456", CarType.COMPACT);
    
    static Permit permit = new Permit(customer.getId(), car, LocalDate.now(),
      LocalDate.now().plusYears(1L));
    
    List<ParkingAction> observers = new ArrayList<ParkingAction>();
    LocalDateTime ldt;
    DayOfWeek thisDay;
    
    public ParkingObserverTransactionTest() {
        
        car.newPermit("ABD123", LocalDate.now(), LocalDate.now().plusYears(1l));
        car2.newPermit("DEF456", LocalDate.now(), LocalDate.now().plusYears(1l));
        
    }

    /**
     * Test of update method, of class ParkingObserverTransaction.
     */
    @Test
    public void testUpdateA() {
        System.out.println("update");
        lot.setIsHourlyRate(Boolean.TRUE);
        car.newPermit("ABD123", LocalDate.now(), LocalDate.now().plusYears(1l));
        car2.newPermit("DEF456", LocalDate.now(), LocalDate.now().plusYears(1l));
 
        lot.removeAllObservers();
        
        
        
        ParkingAction instance = new ParkingObserverTransaction(false);
        
        lot.registerObserver(instance);
       
        lot.entry(car);
        lot.exit(car);
        
        ldt = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.systemDefault());
        thisDay = ldt.getDayOfWeek();
        
        long exp = 175L;
        if ((thisDay.getValue() == 0) || thisDay.getValue() == 6){
        
           exp = 157L;
        }
        
        boolean comp = lot.getTransManager().getTransactions().get(0).getIsCompleted();
        Money amount = lot.getCharges().get(0).getAmount();
        Money expResult = new Money(exp);
        assertEquals(true, comp);
        assertEquals(expResult.getCents(), amount.getCents());
        
        
        
    }
     @Test
    public void testUpdateB() {
        System.out.println("update");
        lot.setIsHourlyRate(Boolean.TRUE);
        
 
        lot.removeAllObservers();
        
        
        
        ParkingAction instance = new ParkingObserverTransaction(false);
        
        lot.registerObserver(instance);
       
        lot.entry(car2);
        
        
        boolean comp = lot.getTransManager().getTransactions().get(0).getIsCompleted();
        
        assertEquals(false, comp);
        
        
        
    }
     @Test
    public void testUpdateC(    ) {
        System.out.println("update");
        lot.setIsHourlyRate(Boolean.TRUE);
        car.newPermit("ABD123", LocalDate.now(), LocalDate.now().plusYears(1l));
        car2.newPermit("DEF456", LocalDate.now(), LocalDate.now().plusYears(1l));
 
        lot.removeAllObservers();
        
        
        
        ParkingAction instance = new ParkingObserverTransaction(false);
        
        lot.registerObserver(instance);
       
        lot.entry(car);
        lot.entry(car2);
        
        
        boolean comp = lot.getTransManager().getTransactions().get(0).getIsCompleted();
        int i = lot.getTransManager().getTransactions().size();
        assertEquals(2,i);
        assertEquals(false, comp);
        
        
        
    }
    
}
