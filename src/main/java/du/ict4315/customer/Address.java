/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du.ict4315.customer;

//import com.google.inject.Inject;
import java.io.Serializable;

/**
 *
 * @author Bobby Vocque
 */
public class Address implements Serializable{
    
    private final String streetAddress1;
    private final String streetAddress2;
    private final String city;
    private final String state;
    private final String zipCode;
    
    //@Inject
    public Address(String streetAddress1, String streetAddress2, String city,
            String state, String zipCode){
        
        this.streetAddress1 = streetAddress1;
        this.streetAddress2 = streetAddress2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    
    
    public String getStreetAddress1(){
        
        return streetAddress1;
        
    }
     
    public String getStreetAddress2(){
        
        return streetAddress2;
        
    }
    public String getCity(){
        
        return city;
        
    }
    public String getState(){
        
        return state;
        
    }
    public String getZipcode(){
        
        return zipCode;
        
    }
    
    public String getAddressInfo() {
        
        //simple return sring
        return String.format("%s %s %s %s %s", streetAddress1,
                streetAddress2, city, state, zipCode);
    }

    
}
