/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du.ict4315.car;

import java.time.LocalDate;
import java.util.Objects;

//import du.ict4315.charges.Money;
//import du.ict4315.charges.ParkingCharge;
import java.io.Serializable;


/**
 *
 * @author Bobby Vocque
 */
public class Car implements Serializable {
    
    private Permit permit;
    private String license;
    private CarType type;
    private String ownerId;
    
    
    public Car (String ownerId, String license, CarType type){
      
      this.ownerId = ownerId;
      this.license = license;
      this.type = type;
      
    }
    
    public void newPermit(String permit, LocalDate register,
    		LocalDate permitExpiration){
      
      Permit newPermit =  new Permit(permit, this, register, permitExpiration);
      
      this.permit = newPermit;
      
      
    }
    public String getOwnerId(){
      
      return ownerId;
      
    }
    public Permit getPermit(){
      
      return permit;
      
    }
    public String getLicense(){
      
      return license;
      
    }
    public CarType getType(){
      
      return type;
      
    }
    //in case wrong type is entered.
    public void setType(CarType type){
      
      this.type = type;
      
    }
    //user need new license plate
    public void setLicense(String license){
      
      this.license = license;
    }
    
      
     
    @Override
    public String toString() {
      return String.format("%s %s %s", ownerId, license, type.toString());
     }
    @Override
    public boolean equals(Object obj) {
    	
        if (!(obj instanceof Car))
            return false;
        if (obj == this)
            return true;
        
        //for may sanity at the moment
        //Money oAmount = ((ParkingCharge) obj).getAmount();
        
        return this.ownerId == ((Car) obj).getOwnerId()
            && this.license == ((Car)obj).getLicense()
        		&& this.type.equals(((Car) obj).getType()) 
            && this.permit.equals(((Car) obj).permit);
    }
    @Override
    public int hashCode() {
    	
      
    	return Objects.hash(license, ownerId,type.ordinal(), permit.hashCode());
    }
    
}
