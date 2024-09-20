/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du.ict4315.customer;

import java.util.List;

import du.ict4315.car.Car;
import du.ict4315.car.CarType;
import java.io.Serializable;
//import du.ict4315.customer.Customer;
import java.util.ArrayList;
import java.util.Iterator;


/**
 *
 * @author Bobby Vocque
 */


public class Customer implements Serializable {
    
    private String id;
    private CustomerName name;
    
    private Address address;
    private String phoneNumber;
    private List<Car> car = new ArrayList<>();
    
    //constructor
    public Customer(String customerId, String fName, String lName ){
      
      this.id = customerId;
      //this.fName = fName;
      //this.lName = lName;
      this.name = new CustomerName(fName, lName);
      
      /*take first thre chars of lastname, see if any matches in customer list
      if soe, check the match customer ID and add one ot numerical value
      else, append -001 and add as id.
      
      if customer list is empty, then just app -001
      */
      
    }
    
    public String getId(){
        
        return id;
    }
    public void setId( String id){
        
        this.id = id;
    }
    //register a car to the user. User may have multiple cars.
    public List<Car> registerCar(String license, CarType type){
      
      Car tmpCar = new Car(this.id, license, type);
      car.add(tmpCar);
      return car;
        
    }
    public CarType getCarType(String license){
        
        return car.get(car.indexOf(license)).getType();
        
    }
    public List<Car> removeCar(String license){
        
      //List<Car> list= new LinkedList<>(); 
      for (int i = 0; i < car.size()-1; i++){
          if (license.equals(car.get(i).getLicense())){
              
              car.remove(i);
          }
      }
      
      return car;
      
    }
    public List<Car> getCars(String id){
    	
    	List<Car> cars = new ArrayList<>();
    	for (Iterator<Car> iterator = this.car.iterator(); iterator.hasNext();) {
			Car auto = iterator.next();
			if (auto.getOwnerId() == id) {
    			
    			cars.add(auto);
    		}
		}
    	return cars;
    	
    }
    public Car modifyLicense(String oldLicense, String newLicense){
      
      for (int i = 0; i <= car.size()-1; i++){
         
          if (oldLicense.equals(car.get(i).getLicense())){
              
              car.get(i).setLicense(newLicense);
              return car.get(i);
          }
      }
      return null;
      
    }

    public void setPhoneNumber(String phoneNumber){
      
       this.phoneNumber = phoneNumber;
      
    }
    public String getPhoneNumber(){
      
       return phoneNumber;
      
    }
    public String getName() {
        
        return name.toString();
        
    }
    public void setName(String fName, String lName){
      
      name.setFirstName(fName);
      name.setLastName(lName);
      
    }
    
    
    
    
    public void setAddress(String streetAddress1, String streetAddress2, String city,
            String state, String zipCode ) {
      
      Address tmpAddress = new Address(streetAddress1, streetAddress2, city, state, zipCode);
      this.address = tmpAddress;
      
    }
    public void setAddress(Address address) {
    	
    	this.address = address;
    }
   
    public Address getAddress(){
        
        return address;
        
    }
    @Override
    public String toString() {
      return String.format("%s  %s %s %s %s", id, name.toString(), address, phoneNumber);
    }
    
    
}
