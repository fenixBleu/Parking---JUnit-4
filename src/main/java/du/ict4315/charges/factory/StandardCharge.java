/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du.ict4315.charges.factory;

import du.ict4315.car.CarType;
import du.ict4315.car.Permit;
import du.ict4315.charges.Money;
import du.ict4315.parkinglot.ParkingLot;
import java.time.Instant;
import du.ict4315.charges.factory.IChargeStrategy;

/**
 *
 * @author vocqueb
 */
public class StandardCharge implements IChargeStrategy{
  
  public String getName(){
    
    return "Standard Strategy";
    
  }
  
  @Override
  public Money calculateCharge(ParkingLot lot, Instant entry, Instant exit, Permit permit){
    
    //right now, only diff is checking whether the lot is set for discount
    
    double cents = lot.getRate();
    
    
    //need to ensure an hourly rate and exit time is after entry or just charge the rate
    if (lot.getIsHourlyRate() && exit.isAfter(entry)){
      
      //do this the long way to keep my sanity
      long timeMin = (exit.getEpochSecond() - entry.getEpochSecond())/60L;
      long timeHours = timeMin/60L;
      long adjHours = Math.round(timeHours);
      
      //assuming minimum charge is for 1 hour
      if (adjHours < 1){
        
        adjHours = 1L;
        
      }
      
      cents = Math.round(cents * adjHours);
         
      
    }
    if (permit.getCar().getType() == CarType.COMPACT){
      
      //compact car discount is 20%
      cents = Math.round(cents * 0.8);
      
    }
    Money charge = new Money((long) cents);
    
    return charge;
  }
    
    
  
  
}
