/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du.ict4315.charges.decorator;

import du.ict4315.car.Permit;
import du.ict4315.charges.Money;
import du.ict4315.parkinglot.ParkingLot;
import java.time.Instant;

/**
 *
 * @author vocqueb
 */
public class HourlyRateDecorator extends ParkingClassCalculatorDecorator{
  
  public HourlyRateDecorator(ParkingChargeCalculator chargeCalculator)
  {
    super(chargeCalculator);
  }
  
  @Override
    public Money getParkingCharge(Instant entry, Instant exit, 
                                    ParkingLot lot, Permit permit) throws NullPointerException {
      
      Money charge = new Money(0L);
      Long cents;// = 0L;
      try {
        charge = parkingClassCalculatorDecorator.getParkingCharge(entry, exit, lot, permit);
      
        //do this the long way to keep my sanity
        long timeMin = (exit.getEpochSecond() - entry.getEpochSecond())/60L;
        long timeHours = timeMin/60L;
        long adjHours = Math.round(timeHours);
      
        //assuming minimum charge is for 1 hour
        if (adjHours < 1){
        
          adjHours = 1L;
        
        }
       cents = (long)Math.round(charge.getCents() * adjHours);
      
       if (lot.getIsDiscounted()){
        
         cents = Math.round(cents * 0.8);
        
       };
      
       charge.setCents(cents);
       
      }catch(Exception ex){
          
          System.out.println("Excepruib Thrown "  + ex.getMessage());
          return new Money(0L);
          
      }finally {
          System.out.println("Charge Processed: "  + charge.getDollars());
          //return charge;
      }
      return charge;
      
      
    }
  
}
