/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du.ict4315.charges.factory;

import du.ict4315.car.CarType;
import du.ict4315.car.Permit;
import du.ict4315.charges.Money;
import du.ict4315.parkinglot.ParkingLot;
import du.ict4315.charges.ParkingCharge;
import java.time.Instant;
import du.ict4315.charges.factory.IChargeStrategy;

/**
 *
 * @author vocqueb
 */

//this applies a discount to underused lot.  for now, apply for transactions at selected lots 
public class DiscountCharge implements IChargeStrategy {
  
  //default to give 20% discount
  private double rateMult = 0.8;
  
  public void setRateMult(double rateMult){
    
    this.rateMult = rateMult;
    
  }
  public double getRateMult(){
    
    return rateMult;
    
  }
  public String getName(){
    return "Discount Strategy";
  }
  @Override
  public Money calculateCharge(ParkingLot lot, Instant entry, Instant exit, Permit permit){
    
    double cents = lot.getRate();
    
    //just to double check, this gives the ability to mark lots as discounted and remove at will
    if (lot.getIsDiscounted()){
       
      cents = Math.round(cents * rateMult);
      
    }
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
