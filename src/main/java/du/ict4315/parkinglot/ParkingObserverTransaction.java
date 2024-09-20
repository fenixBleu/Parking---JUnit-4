/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du.ict4315.parkinglot;

import du.ict4315.charges.Money;
import du.ict4315.charges.ParkingCharge;
import java.time.Instant;
import java.util.Iterator;

/**
 *
 * @author vocqueb
 */
public class ParkingObserverTransaction implements ParkingAction {
    
    private TransactionManager transactions = new TransactionManager();
    private boolean handlesHourly = false;
    
    
    public ParkingObserverTransaction(boolean handlesHourly){
        
        //have an observer that will process hourly transactions or single rate
        this.handlesHourly = handlesHourly;
        
        
    }
    
    public TransactionManager getTransactions(){
        
        return transactions;
        
    }
    
    @Override
    public void update(ParkingEvent parkingEvent){
        
        int i;
        ParkingTransaction transaction = new ParkingTransaction(parkingEvent);
        
        if(parkingEvent.getLot().getTransManager().getTransactions().isEmpty()){
                
                parkingEvent.getLot().getTransManager().addTransaction(transaction);
                
        } else {
         
             //we need to ensure the parkingEvent is not duplicate
             
             
             for(ParkingTransaction obj : parkingEvent.getLot().getTransManager().getTransactions()){
                 
                 boolean lot = obj.getLot().getLotId().equals(transaction.getLot().getLotId());
                 boolean permit = obj.getPermit().getId().equals(transaction.getPermit().getId());
                 
                 
                 if (lot && permit){
                     
                     if (parkingEvent.getEventType()==ParkingEventType.exit){
                         
                         //we want to update the corresponding transaction for an exit event
                         obj.setEventType(parkingEvent.getEventType());
                         obj.setExit(parkingEvent.getOccurred());
                         
                         
                     
                     }  
                     
                     if (obj.getIsCompleted()){
                         
                         ParkingCharge charge = new ParkingCharge(obj.getEntry(),
                                 obj.getExit(), obj.getPermit(), obj.getLot());
                         
                         Money amount = obj.getLot().getLotStrategy().
                                 calculateCharge(obj.getLot(), obj.getEntry(),
                                         obj.getExit(), obj.getPermit());
                                 
                         charge.setAmount(amount);
                         
                         obj.getLot().setCharges(charge);
                                 
                                 
                                 
                     }
                     
                     break;
            
                } else if (!(lot && permit)) {
                     
                     //list was not empty and match not found
                      parkingEvent.getLot().getTransManager().addTransaction(transaction);
                     break;
                 }
             }
        }
    }
            
                
        
            
            
                 
                
                
   
        
            
            
        
        
        
        
   
    
    
    
}
