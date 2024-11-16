/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package du.ict4315.charges;




//import com.google.inject.AbstractModule;
//import com.google.inject.Guice;
import com.google.inject.Inject;
//import com.google.inject.Injector;
import java.time.Instant;
import du.ict4315.car.Permit;
import du.ict4315.car.CarType;
import du.ict4315.car.Car;

import du.ict4315.customer.Address;
import du.ict4315.parkinglot.ParkingLot;



import java.time.DayOfWeek;

import org.junit.Test;
import org.junit.Before; 
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
//import org.junit.runner.Runwith;



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

//these tests run in no particular order

public class ParkingChargeTest{
	
	 static Instant incurred;
     static Instant exit;
     static LocalDateTime ldt;
     static DayOfWeek thisDay;
     long globalResult = 175L;
     static Permit permit;
     static Address lotAddress, lotAddress2;
     ParkingLot lot;
     ParkingLot lot2;
     static Car car;
	
	@BeforeClass
	public static void SetupClass() {
	    
	    car = new Car("Tom Saywer", "XYZ-456", CarType.SUV);
	    permit = new Permit("ASC123", car, LocalDate.now(), 
	    LocalDate.now().plusYears(1L));
	    
	    lotAddress = new Address("123 z Street", "", "Acity", "IL","12345");
	    lotAddress2 = new Address("456 Y Street","", "Acity", "IL","12345");
	    //lot = new ParkingLot("Lot1", lotAddress, 175L, 100, false, false);
	    //lot2 = new ParkingLot("Lot2", lotAddress2, 150L, 100, false, false);  
	
	    incurred = Instant.now();
	      
	    exit = Instant.now().plus(1, ChronoUnit.HOURS);
	    ldt = LocalDateTime.ofInstant(exit, ZoneOffset.systemDefault());
	    thisDay = ldt.getDayOfWeek();
	          
	      
	    }
	
	@Before
	public void reinitLots() {
		
		
		lot = new ParkingLot("Lot1", lotAddress, 175L, 100, false, false);
	    lot2 = new ParkingLot("Lot2", lotAddress2, 150L, 100, false, false); 
		
	}
	
	@After
	public void resetLots() {
		
		lot = null;
		lot2 = null;
		
	}
	
	
	@AfterClass
	public static void Close() {
		
		//we can let garbage collection take care of the class.  This is just an exercise to provide some scope
		//we can debug and see these clear.
		incurred = null;
		exit = null;
	
		
		
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
	        String expResult = "Lot1";
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
	        String expResult = "Permit ID: ASC123 Lot ID: Lot1 Amount: " + Double.toString(expDollars)
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
	        System.out.println("Not Equal To Other, entry time");
	        ParkingCharge testInstance = new ParkingCharge(incurred, exit, permit, lot);
	        
	        testInstance.setEntry(Instant.now().plusSeconds(15));
	        
	        
	        assertEquals(false, instance.equals(testInstance));
	      
	      
	    }
	     @Test
	    public void test12() {
	    	lot.setRate(175L);
	        ParkingCharge instance = new ParkingCharge(incurred, exit, permit, lot);
	        
	        System.out.println("Hash is equal");
	        lot.setRate(175L);
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
	        
	        //testInstance.setIncurred(instance.getIncurred());
	        
	        assertFalse(testInstance.hashCode() == instance.hashCode());
	      
	      
	    }
	     @Test
	     public void test14() throws InstantiationException, IllegalAccessException {
	        ParkingCharge instance = new ParkingCharge(incurred, exit, permit, lot);
	        System.out.println("Hash not equal, amount");
	        ParkingLot newLot = null;
			try {
				newLot = (ParkingLot) lot.clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        newLot.setRate(lot.getRate() + 1L);
	        ParkingCharge testInstance = new ParkingCharge(incurred, exit, permit, newLot);
	        
	        //testInstance.setIncurred(Instant.now());
	        
	        assertFalse(testInstance.hashCode() == instance.hashCode());
	      
	      
	    }
	     @Test
	     public void test15() {
	        ParkingCharge instance = new ParkingCharge(incurred, exit, permit, lot);
	        System.out.println("Hash not equal, exit time");
	        ParkingCharge testInstance = new ParkingCharge(incurred, exit, permit, lot);
	        
	        testInstance.setIncurred(Instant.now().plusSeconds(15));
	        
	        assertFalse((testInstance.hashCode() == instance.hashCode()));
	      
	      
	    }
	   
	    
	    
	}
