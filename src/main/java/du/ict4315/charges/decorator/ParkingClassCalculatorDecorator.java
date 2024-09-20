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
public abstract class ParkingClassCalculatorDecorator extends ParkingChargeCalculator{
    
    
    protected ParkingChargeCalculator parkingClassCalculatorDecorator;
    
    public ParkingClassCalculatorDecorator(ParkingChargeCalculator chargeCalculator){
        
        this.parkingClassCalculatorDecorator = chargeCalculator;
        
    }
    @Override
    public Money getParkingCharge(Instant entry, Instant exit, ParkingLot lot, Permit permit){
      
      return parkingClassCalculatorDecorator.getParkingCharge(entry, exit, lot, permit);
      
      
    }
    
    
    
    
    
    
    
}
