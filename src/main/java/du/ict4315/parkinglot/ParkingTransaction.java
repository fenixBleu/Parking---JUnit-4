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
public class ParkingTransaction {
    
    private boolean isComplete = false;
    private Instant entry;
    private Instant exit;
    private Permit permit;
    private ParkingLot lot;
    private ParkingEventType eventType;
    
    
    public ParkingTransaction(ParkingEvent event){
        
        this.entry = event.getOccurred();
        this.lot = event.getLot();
        this.permit = event.getPermit();
        this.eventType = event.getEventType();
        
        if (!lot.getIsHourlyRate()) {
           
            /*if this is a non-hourly lot, what is needed for charge calculation
            is completed. we just want to track it for other processes.
            */
           isComplete = true;   
           this.exit = entry;
            
        } else {
            
            this.exit = Instant.EPOCH;  //put a date earlier than the entrance.
            
        }
        
        
        
    }
    public Instant getEntry(){
        
        return entry;
        
    }
    public Instant getExit(){
        
        return exit;
        
    }
    public boolean setExit(Instant exit) {
        
        this.exit = exit;
        //if this is an hourly lot, then we need to update the transaction status
        if ((exit.isAfter(entry) || exit.equals(entry)) && (eventType == ParkingEventType.exit)){
            
            isComplete = true;
            
        }
        return isComplete;
        
    }
    public Permit getPermit(){
        
        return permit;
        
    }
    public ParkingLot getLot(){
        
        return lot;
        
    }
    public ParkingEventType getEventType(){
        
        return eventType;
        
    }
    public boolean setEventType(ParkingEventType eventType){
        
        this.eventType = eventType;
        if (exit.isAfter(entry) && (eventType == ParkingEventType.exit)){
            
            isComplete = true;
            
        }
        return isComplete;
        
    }
    public boolean getIsCompleted(){
        
        return isComplete;
        
    }
    
    
    
    
}
