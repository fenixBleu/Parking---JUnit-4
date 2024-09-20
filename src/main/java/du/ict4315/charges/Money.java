/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du.ict4315.charges;

import java.io.Serializable;

/**
 *
 * @author Bobby Vocque
 */
public class Money implements Serializable{
    
    private Long cents;
    private double dollars;
    
    public Money(){
    
        setCents(0L);
    
    }
    
    public Money(Long cents){
        
        setCents(cents);
        
    }
    
    public void setCents(Long cents){
        
        // do not want to accent negative numbers from a call
        if( cents > -1 ){
        
          this.cents = cents;
          //do not need a set of dollars, math too simple for method
          this.dollars = (double)cents/100;
        
       }           
    }
   public Long getCents(){
       
       return cents;
       
       
   }
   public double getDollars(){
       
       //could have put the calculation hers as well.
       return dollars;
       
   }
   public void setDollars(double dollars){
       
       //this.cents = new Double(dollars * 100).longValue();
       this.cents = (long)(dollars * 100);
   }
   @Override
   public String toString(){
       
       //want to ensure the string contains 2 decimal places 
       return String.format("%,.2f", dollars);
   }
    
    
}
