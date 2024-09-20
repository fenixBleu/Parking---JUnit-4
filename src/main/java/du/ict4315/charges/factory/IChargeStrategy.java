/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package du.ict4315.charges.factory;
import du.ict4315.parkinglot.ParkingLot;
import du.ict4315.car.Permit;
import du.ict4315.charges.Money;
import java.time.Instant;

/**
 *
 * @author Bobby Vocque
 */

public interface IChargeStrategy {
  
  String getName();
  
  Money calculateCharge(ParkingLot lot, Instant entry, Instant exit, Permit permit);

  
}
