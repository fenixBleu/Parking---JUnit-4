/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du.ict4315.customer;

import java.io.Serializable;

/**
 *
 * @author vocqueb
 */
public class CustomerName implements Serializable{
    
    private String firstName;
    private String lastName;
    private String mInitial;
    
    public CustomerName(String fName, String lName){
        
        this.lastName = lName;
        this.firstName = fName;
        // ass middle intitial
    }
    
    public String getFirstName(){
        
        return firstName;
    }
    
    public String getLastName(){
        
        return lastName;
        
    }
    
    public void setFirstName(String fName){
        
        this.firstName = fName;
        
    }
    public void setLastName(String lName) {
        
        this.lastName = lName;
        
    }
    
    @Override
    public String toString(){
        
        return String.format("%s %s", firstName, lastName);
        
    }
    
    
    
}
