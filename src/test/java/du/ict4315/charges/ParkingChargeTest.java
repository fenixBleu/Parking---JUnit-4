/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package du.ict4315.charges;




//import com.google.inject.AbstractModule;
//import com.google.inject.Guice;
//import com.google.inject.Injector;
import java.time.Instant;
import du.ict4315.car.Permit;
import du.ict4315.car.CarType;
import du.ict4315.car.Car;

import du.ict4315.customer.Address;
import du.ict4315.parkinglot.ParkingLot;
import java.time.DayOfWeek;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
//import javax.inject.Inject;




/**
 *
 * @author Bobby Vocque
 */


public class ParkingChargeTest {
	  
	  /*protected static Injector injector;
	  //@Before
	  public void setupInjector(){
	    injector = Guice.createInjector(new AbstractModule() {
	      @Override
	      protected void configure() {
	         String id = "Lot A";
	         bind(Address.class).toInstance(new Address("123 ABD St", "", "Bearclaw",
	                 "AK","99999"));
	         bind(ParkingLot.class).toInstance(new ParkingLot(id, new Address("123 ABD St", "",
	             "Bearclaw",
	                 "AK","99999"), 175L, 100,
	         false, false));
	      }
	    });
	  }
	  public void setup(){
	    setupInjector();
	    injector.injectMembers(this);
	  }
	  */
	  

	  //@Inject
	  //Address lotAddress;
	  //@Inject
	  //ParkingLot lot;
	    
	  
	    Instant incurred;
	    Instant exit;
	    Car car = new Car("Tom Saywer", "XYZ-456", CarType.SUV);
	    Permit permit = new Permit("ASC123", car, LocalDate.now(), 
	    LocalDate.now().plusYears(1L));
	    Address lotAddress = new Address("123 z Street", "", "Acity", "IL","12345");
	    //ParkingLot lot;
	    ParkingLot lot = new ParkingLot("Lot 2", lotAddress, 175L, 100, false, false);
	    ParkingLot lot2 = new ParkingLot("Lot2", lotAddress, 175L, 100, false, false);
	    
	    
	    //ParkingLot lot2 = new ParkingLot("Lot02",lotAddress, 175L, 50, false, false );
	    LocalDateTime ldt;
	    DayOfWeek thisDay;
	    long globalResult = 175L;
	    
	    public ParkingChargeTest() {
	      //setup();
	      this.incurred = Instant.now();
	      
	      this.exit = Instant.now().plus(1, ChronoUnit.HOURS);
	      ldt = LocalDateTime.ofInstant(exit, ZoneOffset.systemDefault());
	      thisDay = ldt.getDayOfWeek();
	      //Address lot2 = new Address("123 z Street", "", "Acity", "IL","12345");
	      
	      
	    }
	    
	    
	    

	    /**
	     * Test of getPermitId method, of class ParkingCharge.
	     */
	    @Test
	    public void test1() {
	        
	        ParkingCharge instance = new ParkingCharge(incurred, exit, permit, lot);
	        System.out.println("getPermitId");
	        //Address addr = lotAddress;
	        String expResult = "ASC123";
	        String result = instance.getPermit().getId();
	        assertEquals(expResult, result);
	        
	        
	    }

	    /**
	     * Test of getLotId method, of class ParkingCharge.
	     */
	    @Test
	    public void test2() {
	        ParkingCharge instance = new ParkingCharge(incurred, exit, permit, lot);
	        System.out.println("getLotId");
	        String expResult = "Lot 2";
	        String result = instance.getLot().getLotId();
	        assertEquals(expResult, result);
	        Address chkAddr = lot.getAddress();
	        System.out.println(chkAddr.getAddressInfo());
	        
	    }

	    /**
	     * Test of getIncurred method, of class ParkingCharge.
	     */
	    @Test
	    public void test3() {
	      
	        ParkingCharge instance = new ParkingCharge(incurred, exit, permit, lot);
	        System.out.println("getIncurred");
	        
	        Instant result = instance.getIncurred();
	        assertEquals(exit, result);
	        
	    }

	    /**
	     * Test of getAmount method, of class ParkingCharge.
	     */
	    @Test
	    public void test4() {
	        //setup();
	        ParkingCharge instance = new ParkingCharge(incurred, exit, permit, lot);
	        System.out.println("getAmount");
	        long longResult = 175L;
	        if (thisDay.getValue() < 1 || thisDay.getValue() > 5){
	          
	          longResult = (long)(longResult * 0.9);
	          
	        }
	        double expDollars = (double)longResult/100;
	        Money money = instance.getAmount();
	        assertEquals(longResult, money.getCents(), 0);
	        assertEquals(expDollars, money.getDollars(), 0.0);
	        //return value to original state
	        //instance.getLot().setRate(175L);
	        
	    }

	    /**
	     * Test of toString method, of class ParkingCharge.
	     */
	    @Test
	    public void test5() {
	      ParkingCharge instance = new ParkingCharge(incurred, exit, permit, lot);
	        System.out.println("toString");
	        long longResult = 175L;
	        if (thisDay.getValue() < 1 || thisDay.getValue() > 5){
	          
	          longResult = (long)(longResult * 0.9);
	          
	        }
	        double expDollars = (double)longResult/100;
	       
	        String date = exit.toString();
	        String expResult = "Permit ID: ASC123 Lot ID: Lot 2 Amount: " + Double.toString(expDollars)
	            + " Time: " + date;
	        String result = instance.toString();
	        assertEquals(expResult, result);
	        
	    }
	    @Test
	    public void test6() 
	        {
	        ParkingCharge instance = new ParkingCharge(incurred, exit, permit, lot);
	        System.out.println("Equal To Self");
	        
	        assertEquals(true, instance.equals(instance));
	      
	      
	    }
	    @Test
	    public void test7() {
	        ParkingCharge instance = new ParkingCharge(incurred, exit, permit, lot);
	        System.out.println("Equal To Other");
	        ParkingCharge testInstance =  instance;
	        //testInstance.setIncurred(instance.getIncurred());
	        
	        assertEquals(true, instance.equals(testInstance));
	      
	      
	    }
	    @Test
	    public void test8() {
	        ParkingCharge instance = new ParkingCharge(incurred, exit, permit, lot);
	        System.out.println("Not Equal To Other, money");
	        ParkingLot newLot = lot;
	        newLot.setRate(lot.getRate() + 1L);
	        ParkingCharge testInstance = new ParkingCharge(incurred,exit, permit, newLot);
	        testInstance.setIncurred(instance.getIncurred());
	        
	        assertEquals(false, instance.equals(testInstance));
	      
	      
	    }
	    @Test
	    public void test9() {
	        ParkingCharge instance = new ParkingCharge(incurred, exit, permit, lot);
	        System.out.println("Not Equal To Other, incurred");
	        
	        ParkingCharge testInstance = new ParkingCharge(incurred, exit,  permit, lot);
	        testInstance.setIncurred(Instant.ofEpochSecond(9000000L));
	        
	        assertEquals(false, instance.equals(testInstance));
	      
	      
	    }
	    @Test
	    public void test10() {
	        ParkingCharge instance = new ParkingCharge(incurred, exit, permit, lot);
	        System.out.println("Not Equal To Other, lot id");
	        
	        ParkingCharge testInstance = new ParkingCharge(incurred, exit, permit, lot2);
	        testInstance.setIncurred(instance.getIncurred());
	        
	        assertEquals(false, instance.equals(testInstance));
	      
	      
	    }
	    @Test
	    public void test11() {
	        ParkingCharge instance = new ParkingCharge(incurred, exit, permit, lot);
	        System.out.println("Not Equal To Other, time");
	        ParkingCharge testInstance = new ParkingCharge(incurred, exit, permit, lot);
	        
	        testInstance.setIncurred(Instant.now().plusSeconds(15));
	        
	        
	        assertEquals(false, instance.equals(testInstance));
	      
	      
	    }
	     @Test
	    public void test12() {
	        ParkingCharge instance = new ParkingCharge(incurred, exit, permit, lot);
	        lot.setRate(175L);
	        System.out.println("Hash is equal");
	        ParkingCharge testInstance = new ParkingCharge(incurred, exit,  permit, lot);
	        //testInstance.setIncurred(instance.getIncurred());
	        
	        assertEquals(testInstance.hashCode(), instance.hashCode());
	      
	      
	    }
	     @Test
	     public void test13() {
	        ParkingCharge instance = new ParkingCharge(incurred, exit, permit, lot);
	        System.out.println("Hash not equal, permit");
	        Permit permit2 = new Permit("ACC123", car, LocalDate.now(), 
	             LocalDate.now().plusYears(1L));
	        ParkingCharge testInstance = new ParkingCharge(incurred, exit,  permit2, lot);
	        
	        testInstance.setIncurred(instance.getIncurred());
	        
	        assertFalse(testInstance.hashCode() == instance.hashCode());
	      
	      
	    }
	     @Test
	     public void test14() {
	        ParkingCharge instance = new ParkingCharge(incurred, exit, permit, lot);
	        System.out.println("Hash not equal, amount");
	        ParkingLot newLot = lot;
	        newLot.setRate(lot.getRate() + 1L);
	        ParkingCharge testInstance = new ParkingCharge(incurred, exit, permit, newLot);
	        
	        testInstance.setIncurred(Instant.now());
	        
	        assertFalse(testInstance.hashCode() == instance.hashCode());
	      
	      
	    }
	     @Test
	     public void test15() {
	        ParkingCharge instance = new ParkingCharge(incurred, exit, permit, lot);
	        System.out.println("Hash not equal, time");
	        ParkingCharge testInstance = new ParkingCharge(incurred, exit, permit, lot);
	        
	        testInstance.setIncurred(Instant.now().plusSeconds(15));
	        
	        assertFalse((testInstance.hashCode() == instance.hashCode()));
	      
	      
	    }
	   
	    
	    
	}
