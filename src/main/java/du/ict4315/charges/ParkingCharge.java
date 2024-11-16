/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du.ict4315.charges;

import du.ict4315.charges.factory.ParkingChargeStrategy;
import java.time.Instant;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Objects;

//import du.ict4315.car.CarType;
import du.ict4315.car.Permit;
//import du.ict4315.charges.Money;
import du.ict4315.parkinglot.ParkingLot;

import java.time.ZoneOffset;

/**
 *
 * @author Bobby Vocque
 */
public class ParkingCharge {
    
    private Permit permit;
    private ParkingLot lot;
    private Instant entry;
    private Instant incurred; //exit date/time will be when charge was incurred.
    private Money amount;
    private Long newRate = 00L;
    
   //prefer not to use no arg constructor for this
   public ParkingCharge (Instant entry, Instant exit, Permit permit, ParkingLot lot) {
   
	   	boolean specialDate = false;
	   	
        //leaving the additional items for future use.  
        this.permit = permit;
        this.lot = lot;
        this.entry = entry;
        
        /*determine the day of week. Using the exit instant as the time ro process the charge
        if the lot is flat rate then entry = exit*/
        LocalDateTime ldt = LocalDateTime.ofInstant(exit, ZoneOffset.systemDefault());
        DayOfWeek thisDay = ldt.getDayOfWeek();
        
        
        /*check to see if today is a special day that requires a different rate
         * make a file where special dates are read or from a calendar object.
        */
        if (ldt.toLocalDate() == LocalDate.of(LocalDate.now().getYear(), 5, 20)){
          //grad date
          specialDate = true;
          
        }
        
        //going to refactor once I make up my mind for a calendar for parking rates
        
        //a weekend day, make the lots rates a little lower
        if ((thisDay.getValue() < 1 || thisDay.getValue() > 5) && !specialDate){
          
          //base rate for weekends is 90%
          lot.setCalcRate((long)(lot.getRate().doubleValue() * 0.9));
          
        } else if(specialDate) {
        
          //increase rates for special date
        	lot.setCalcRate((long)(lot.getRate().doubleValue() * 1.1));
        
        }
            
        //changing to use the factory to get the correct strategy
        ParkingChargeStrategy chgStrategy = new ParkingChargeStrategy(lot, entry, exit, permit);
        this.amount = chgStrategy.getCharge();
        
        /*calculates the charge from the selected stategy including car type
        can uncomment this to switch back to previous week.
        this.amount = lot.getLotStrategy().calculateCharge(lot, entry, exit, permit);
        as time permits, going to look into narrowing the scope to drop msecs. 
        */
        this.incurred = exit;
        
   }
   
   // as a means to correct if a timing issue occurs 
    public void setEntry(Instant instant) {
    	
    	this.entry = instant;
    	
    }
    public Instant getEntry() {
    	
    	return this.entry;
    	
    }
    
    public Permit getPermit(){
        
        return permit;
        
    }
    public ParkingLot getLot(){
        
        return lot;
        
    }
    public Instant getIncurred(){
        
        return incurred;
        
    }
   
   // as a means to correct if a timing issue occurs
   public void setIncurred(Instant instant){
     
        this.incurred = instant;
     
   }
    public Money getAmount(){
        //develop something on the final format of this
        return amount;
    }
    public void setAmount(Money amount){
        
        this.amount = amount;
        
    }
    @Override
    public String toString(){
        
        
        return String.format("Permit ID: %s Lot ID: %s Amount: %s Time: %s",
                permit.getId(), lot.getLotId(), amount.toString(), incurred.toString());
        
    }
    @Override
    public boolean equals(Object obj) {
    	
        if (!(obj instanceof ParkingCharge))
            return false;
        if (obj == this)
            return true;
        
        //for may sanity at the moment
        Money oAmount = ((ParkingCharge) obj).getAmount();
        
        return this.incurred.equals(((ParkingCharge) obj).getIncurred()) 
            && this.amount.getDollars() == oAmount.getDollars()
        		&& this.lot.getLotId().equals(((ParkingCharge) obj).getLot().getLotId()) 
            && this.permit.getId().equals(((ParkingCharge) obj).getPermit().getId());
    }
    @Override
    public int hashCode() {
    	
      
    	return Objects.hash(permit, incurred.toEpochMilli(),lot.getLotId(), amount.getCents());
    }
    
    
    
    
    
}
