package du.ict4315.charges;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

//import javax.inject.Injector;

import du.ict4315.customer.Address;
import du.ict4315.parkinglot.ParkingLot;

import org.junit.Before;

/**
*
* @author Bobby Vocque
*/
public class ParkingChargeBase{
	
	
	    protected Injector injector = Guice.createInjector(new AbstractModule() {
	      @Override
	      protected void configure() {
	         //String id = "Lot A";
	         //Using the address bind in creating lot2 in the test class.
	         bind(Address.class).toInstance(new Address("123 ABD St", "", "Bearclaw",
	                 "AK","99999"));
	         /*this will be lotA in the test class
	         bind(ParkingLot.class).toInstance(new ParkingLot(id, new Address("123 ABD St", "",
	             "Bearclaw",
	                 "AK","99999"), 175L, 100,
	         false, false));*/
	      }
	    });
	
	@Before 
	public void setup(){
	    //setupInjector();
	    injector.injectMembers(this);
	}
	    
	
	
}