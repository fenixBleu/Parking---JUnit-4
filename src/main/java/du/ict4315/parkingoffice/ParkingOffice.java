/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du.ict4315.parkingoffice;

import du.ict4315.car.Car;
import du.ict4315.car.CarType;
import du.ict4315.car.Permit;
import du.ict4315.charges.Money;
import du.ict4315.charges.ParkingCharge;
import du.ict4315.customer.Address;
import du.ict4315.customer.Customer;
import du.ict4315.parkinglot.ParkingLot;
import du.ict4315.parkinglot.TransactionManager;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Bobby Vocqueb
 */
public class ParkingOffice {
    
    private String name;
    private Address address;
    private List<Customer> customers = new ArrayList<Customer>();
    private List<Car> cars = new ArrayList<>();
    private List<ParkingLot> lots = new ArrayList<>();
    private List<ParkingCharge> charges = new ArrayList<>();
    
    public ParkingOffice(String name, Address address){
      
      this.name = name;
      this.address = address;
      
    }
    public ParkingOffice(){
      
    }
    
    public void setName(String name){
        
        this.name = name;
        
    }
    public String getName(){
        
        return name;
        
    }
    public void setAddress(Address address){
        
        this.address = address;
        
    }
    public Address getAddress(){
        
        return address;
    }
    public void addLot(String id, Address address, Long rate, int capacity, boolean discounted,
                                                                            boolean hourly) {
    	
    	ParkingLot newLot = new ParkingLot(id, address, rate, capacity, discounted, hourly);
    	lots.add(newLot);
    	
    }
    public void addLot(ParkingLot lot){
      
      lots.add(lot);
      
    }
    public List<ParkingLot> getLots(){
      
      return lots;
      
    }
    
    public Customer register(String fName, String lName, Address address, String phone){
        
        
      
      /*though about several different methods to fake a customer id, just
        to use first two char of name plus first four digits of phone*/
        String customerId = fName.substring(0, 2) + 
                phone.substring(phone.length()- 4);
        
        
        Customer newCustomer = new Customer(customerId, fName,lName);
        
        newCustomer.setAddress(address);
        newCustomer.setPhoneNumber(phone);
        customers.add(newCustomer);
        
        return newCustomer;
    }
    
    public Permit register (String customerId, String license, String carType){
        
        Car newCar = new Car(customerId, license, CarType.valueOf(carType));
        for (int i = 0; i < customers.size(); i++){
          
          if (customerId.equals(customers.get(i).getId())){
            
            String permitId = customers.get(i).getName().substring(0, 2) + license + carType;
        //add the permit to the car during registration
        
            newCar.newPermit(permitId, LocalDate.now(), LocalDate.now().plusYears(1L));
        
            cars.add(newCar);
            customers.get(i).registerCar(license, CarType.valueOf(carType));
          }
          
        }
        
        
        return newCar.getPermit();
         
        
    }
    
    public Money addCharge (ParkingCharge charge){
        
        charges.add(charge);
        return charge.getAmount();
        
    }
    public Customer getCustomer(String name){
        
        int x = 0;
        for (int i = 0; i < customers.size(); i++) {
            
            if (customers.get(i).getName().equals(name)){
                x = i;
                break;
            }
        }
        return customers.get(x);
    }
    public boolean removeCustomer(String name){
      
      while(customers.iterator().hasNext()){
        Customer tempCustomer = customers.iterator().next();
        //getName() return a concat fName + " " + lName
        if(tempCustomer.getName().equals(name)){
          
          return customers.remove(tempCustomer); //remove the record
          
        }
        
      }
      return false; //if we are here, we did not find a match.
    }
    public List<String> getCustomerIds(){
    	
    	
    	List<String> ids = new ArrayList<String>();
    	//Iterator<Customer> iterator = customers.listIterator();
      Iterator<Customer> it = customers.iterator();
    	
    	//could use a for loop.
    	while (it.hasNext()) {
    
    		ids.add(it.next().getId());
    	}
    	
    	return ids;
    		
    }
    public List<String> getPermitIds(){
    	
    	
    	List<String> ids = new ArrayList<String>();
    	Iterator<Car> iterator = cars.listIterator();
    	
    	while (iterator.hasNext()) {
        	
    		ids.add(iterator.next().getPermit().getId());
    		
    	}
    	
    	return ids;
    		
    }
    public List<String> getPermitIds(String name){
    	
    	
    	List<String> ids = new ArrayList<String>();
    	//Iterator<Car> iterator = cars.listIterator();
    	
      for(int i = 0; i < customers.size(); i++)
    	{
    	  if (customers.get(i).getName().equals(name)){
          
           String id = customers.get(i).getId();
           for (int x = 0; x < cars.size(); x++){ 
           
    		     if (cars.get(x).getOwnerId().equals(id) ) {
    			
    			      ids.add(cars.get(x).getPermit().getId());
    			
    		   }
        }
        }
    	}
    	
    	return ids;
    		
    }
    public ParkingCharge park(Instant entry, Instant exit, Permit permit, ParkingLot lot) {
    	
    	ParkingCharge newCharge = new ParkingCharge(entry, exit, permit,
    			lot);
    	charges.add(newCharge);
    	return newCharge;
    	
    }
    public Money getParkingCharges(Permit permit) {
    	
    	Long x = 0l;
    	for (int i = 0; i < charges.size(); i++) {
    		
    		if (permit.getId() == charges.get(i).getPermit().getId()) {
    			
    			x += charges.get(i).getAmount().getCents();
    			
    		}
    		
    	}
    	return new Money(x);
    	
    }
    public Money getParkingCharges(Customer c) {
    	
    	Long x = 0L;
        	
          
        	for (int z = 0; z < charges.size(); z++) {
        		
        		if (c.getName().equals(charges.get(z).getPermit().getCar().getOwnerId())) {
        			
        			x += charges.get(z).getAmount().getCents();
        			
        		}
        	}
        	
        
    	
    	return new Money(x);
    }
        
    
        
    
}
