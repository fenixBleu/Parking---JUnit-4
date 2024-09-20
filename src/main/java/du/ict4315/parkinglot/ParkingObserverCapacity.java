/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du.ict4315.parkinglot;

/**
 *
 * @author vocqueb
 */
public class ParkingObserverCapacity implements ParkingAction {
    
    
    
    @Override
    public void update(ParkingEvent parkingEvent){
        
        // as the name implies, monitor capacities to see if lot is full
        ParkingLot lot = parkingEvent.getLot();
        String carType = parkingEvent.getEventType().name();
        switch (carType){
            
            
            case "entry":
                
                lot.setRemainingCapacity(lot.getRemainingCapacity()-1);
                if (lot.getRemainingCapacity() < 0){
                    
                    lot.setRemainingCapacity(0);
                    
                }
                if (lot.getRemainingCapacity() == 0){
                    
                    lot.setIsFull(true);
                    
                } else {
                    
                    lot.setIsFull(false);
                    
                }
                break;
                
                
            case "exit":
                
                lot.setRemainingCapacity((lot.getRemainingCapacity()+1));
                if (lot.getRemainingCapacity() > 0){
                    
                    lot.setIsFull(false);
                    
                } else {
                    
                    lot.setIsFull(true);
                    
                }
                if (lot.getRemainingCapacity() > lot.getMaxCapacity()){
                    
                    lot.setRemainingCapacity(lot.getMaxCapacity());
                    
                }
                break;
                
        }
        
        
        
    }
    
}
