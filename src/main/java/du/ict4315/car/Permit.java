package du.ict4315.car;

import java.time.LocalDate;
import java.util.Objects;
import du.ict4315.charges.Money;
import du.ict4315.charges.ParkingCharge;

public class Permit {
	
	private String id;
	private Car car;
    private LocalDate permitRegistration;
    private LocalDate permitExpiration;
    private boolean expired;
    
    public Permit(String id, Car car, LocalDate permitRegistration, 
    		LocalDate permitExpiration) {
    	
    	this.id = id;
    	this.car = car;
    	this.permitRegistration = permitRegistration;
    	this.permitExpiration = permitExpiration;
    	expired = isExpired();
      
    	
    	
    }
    public String getId(){
    	
    	return id;
    	
    }
    public Car getCar() {
    	
    	return car;
    	
    }
    public void setID(String id) {
    	
    	this.id = id;
    	
    }
    public boolean isExpired() {
    	
    	LocalDate date = LocalDate.now();
        int result = date.compareTo(permitExpiration);
        
        if (result > 0){
            
            return true;
        }else{
            return false;
        }
    	
    }
    public void renewPermit(LocalDate permitRenew, LocalDate permitExpire) {
    	
    	this.permitRegistration = permitRenew;
    	this.permitExpiration = permitExpire;
    	
    }
    
    public LocalDate getRegistration() {
    	
    	return permitRegistration;
    	
    }
    public LocalDate getExpiration() {
    	
    	return permitExpiration;
    	
    }
    @Override
    public boolean equals(Object obj) {
    	
        if (!(obj instanceof Permit))
            return false;
        if (obj == this)
            return true;
        
        
        return this.id.equals(((Permit)obj).getId()) 
            && this.expired == ((Permit)obj).isExpired()
        		&& this.permitRegistration.equals(((Permit) obj).getRegistration()) 
            && this.permitExpiration.equals(((Permit) obj).getExpiration());
    }
    @Override
    public int hashCode() {
    	
      
    	return Objects.hash(id, expired, permitRegistration.toEpochDay(), 
    			permitExpiration.toEpochDay());
    }
    
    

}
