/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du.ict4315.charges.factory;

import du.ict4315.car.Permit;
import du.ict4315.parkinglot.ParkingLot;
import java.time.Instant;
import du.ict4315.charges.factory.IChargeStrategy;

/**
 *
 * @author vocqueb
 */
public class ParkingChargeFactory {
  
  
  public IChargeStrategy getStrategy(Boolean discount) {
    
   
    
    if (discount) {
      
      return new DiscountCharge();
      
    }
  
    // if discount is false, we return standard charge
    
  return new StandardCharge();
  
  }
  
}
