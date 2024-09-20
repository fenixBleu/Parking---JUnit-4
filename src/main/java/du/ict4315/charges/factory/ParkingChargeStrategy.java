/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du.ict4315.charges.factory;

import du.ict4315.charges.factory.ParkingChargeFactory;
import du.ict4315.car.Permit;
import du.ict4315.charges.Money;
import du.ict4315.parkinglot.ParkingLot;
import java.time.Instant;
import du.ict4315.charges.factory.IChargeStrategy;

/**
 *
 * @author vocqueb
 */
public class ParkingChargeStrategy {
  
  Money charge;
 
  
  public ParkingChargeStrategy(ParkingLot lot, Instant entry, Instant exit, Permit permit){
    //create a factory instance
    ParkingChargeFactory chgFactory = new ParkingChargeFactory();
    //instantiate a strategy based upon whether the lot is set for discounted rate.
    IChargeStrategy chgStrategy = chgFactory.getStrategy(lot.getIsDiscounted());
    //calculate the charge
    charge = chgStrategy.calculateCharge(lot, entry, exit, permit);
    
    
    
  }
  
  public Money getCharge(){
    
    return charge;
    
  }
  
}
