/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package du.ict4315.parkinglot;

import du.ict4315.car.Car;
import du.ict4315.car.CarType;
import du.ict4315.car.Permit;
import du.ict4315.customer.Address;
import du.ict4315.customer.Customer;
//import java.time.Instant;
import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vocqueb
 */
public class ParkingObserverCapacityTest {
    
    
    static Address lotAddress = new Address("456 B St", "", "Timbuktu", 
                "Veryfar", "00002");
    static ParkingLot lot = new ParkingLot("testLot", lotAddress,175L, 2, false, false);
    
    static Customer customer = new Customer("AL123", "Al", "Bundy");
    
    static Car car = new Car("Al Bundy", "ABC-123", CarType.SUV);
    
    static Car car2 = new Car("Paul Bunyon", "ABC-456", CarType.COMPACT);
    
    static Permit permit = new Permit(customer.getId(), car, LocalDate.now(),
      LocalDate.now().plusYears(1L));
    
    public ParkingObserverCapacityTest() {
    }

    /**
     * Test of update method, of class ParkingObserverCapacity.
     */
    @Test
    public void testUpdateEntry() {
        System.out.println("update for entry");
        car.newPermit("ABD123", LocalDate.now(), LocalDate.now().plusYears(1l));
        car2.newPermit("DEF456", LocalDate.now(), LocalDate.now().plusYears(1l));
        //this observer is called by the lot
        lot.entry(car);
        
        
        int expResult = 1;
        assertEquals(expResult, lot.getRemainingCapacity());
        assertEquals(false,lot.getIsFull());
        
        lot.entry(car2);
        
        assertEquals(0,lot.getRemainingCapacity());
        assertTrue(lot.getIsFull());
        
    }
    @Test
    public void testUpdateExit() {
        System.out.println("update for exit");
        lot.entry(car2);
        //this observer is called by the lot
        lot.exit(car);
        
        
        int expResult = 1;
        assertEquals(expResult, lot.getRemainingCapacity());
        assertFalse(lot.getIsFull());
        
        lot.exit(car2);
        assertEquals(2, lot.getRemainingCapacity());
        assertFalse(lot.getIsFull());
        
        
    }
    
}
