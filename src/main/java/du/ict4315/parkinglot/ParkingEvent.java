/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du.ict4315.parkinglot;

import du.ict4315.car.Permit;
import java.time.Instant;

/**
 *
 * @author vocqueb
 */
public class ParkingEvent {
    
   private final Instant occured;
   private final ParkingEventType eventType;
   private final Permit permit;
   private final ParkingLot lot;
   
   public ParkingEvent(Instant occured, ParkingEventType eventType,
           Permit permit, ParkingLot lot){
       
       this.occured = occured;
       this.eventType = eventType;
       this.permit = permit;
       this.lot = lot;
       
   }
   public Instant getOccurred(){
       
       return occured;
       
   }
   public ParkingEventType getEventType(){
       
       return eventType;
       
   }
   public Permit getPermit(){
       
       return permit;
       
   }
   public ParkingLot getLot(){
       
       return lot;
   }
   
    
}
