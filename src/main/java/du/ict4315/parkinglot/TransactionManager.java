/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du.ict4315.parkinglot;

import du.ict4315.charges.ParkingCharge;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author vocqueb
 */
public class TransactionManager {
    
    private List<ParkingTransaction> transactions = new 
        ArrayList<ParkingTransaction>();
    //for passing transactions to charge factory later
   
    
    
    
    public TransactionManager(){
        
    }
    public List<ParkingTransaction> getTransactions(){
        
        return transactions;
        
    }
    public void addTransaction(ParkingTransaction transaction){
        
        if (!(transactions.contains(transaction))){
            //add a tansaction if not a duplicate
            transactions.add(transaction);
            
        }
        
        
    }
    public void completeTransaction(ParkingTransaction transaction){
        
        //find the transaction  
        
        Iterator<ParkingTransaction> it = transactions.iterator();
        
        //boolean a = it.hasNext();
        while(it.hasNext()){
            ParkingTransaction temp = it.next();
            if ((temp.getLot().getLotId()== transaction.getLot().getLotId())
                    && (temp.getEntry().equals(transaction.getEntry())) &&
                            (temp.getPermit().getId() == 
                            transaction.getPermit().getId())){
                //only two items can be updated.  doing this method for debugging
                int i = transactions.indexOf(temp);
                transactions.get(i).setExit(transaction.getExit());
                transactions.get(i).setEventType(transaction.getEventType());
               
                break;
                
            }
            //if (a){
                //a = it.hasNext();
            //}
            
        }
    }
    
}
