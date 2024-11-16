/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package du.ict4315.parkinglot;

//import com.google.inject.Inject;
import du.ict4315.car.Car;
import du.ict4315.car.CarType;
import du.ict4315.charges.Money;
import du.ict4315.charges.ParkingCharge;
import du.ict4315.charges.decorator.CompactCarDecorator;
import du.ict4315.charges.decorator.RateCalculator;
import du.ict4315.charges.decorator.FlatRateDecorator;
import du.ict4315.charges.decorator.HourlyRateDecorator;
import du.ict4315.charges.factory.DiscountCharge;
import du.ict4315.customer.Address;
import du.ict4315.charges.factory.StandardCharge;


import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import du.ict4315.charges.factory.IChargeStrategy;
import java.time.Instant;
import java.util.Iterator;


/**
 *
 * @author Bobby Vocque
 */
public class ParkingLot implements Cloneable {
   
    private String lotId;
    private Address address;
    private Long rate;  //match cents in Money
    private Long calcRate;  //this allows discounts to be applied dynamically without changing base rate.
    //max capacity of lot
    private int maxCapacity;
    private int remainingCapacity = 2;
    //tracking current lot occupants, this is to assist in processing timed rates 
    private List<Car> occupants = new ArrayList<>();
    private Boolean isFull;
    private Boolean isDiscounted = false;
    private Boolean isHourlyRate = false;
    private IChargeStrategy lotStrategy;
    private List<ParkingAction> observers = new ArrayList<ParkingAction>();
    private TransactionManager transManager = new TransactionManager();
    private List<ParkingCharge> charges = new ArrayList<>();
    
    
    
    
    //@Inject
    public ParkingLot(String lotId, Address address,Long rate, int capacity, boolean isDiscounted, boolean isHourly){
      
      this.lotId = lotId;
      this.address = address;
      this.maxCapacity = capacity;
      this.rate = rate;
      this.remainingCapacity = capacity;
      this.isFull = false;
      this.isDiscounted = isDiscounted;
      this.isHourlyRate = isHourlyRate;
      setLotStrategy(isDiscounted);
      
      //we'll register these at the moment. 
      ParkingAction observer = new ParkingObserverTransaction(isHourlyRate);
      registerObserver(observer);
      observer = new ParkingObserverCapacity();
      registerObserver(observer);
      
    }
    public Object clone() throws CloneNotSupportedException {
    	
    	return super.clone();
    	
    }
	public void setCharges(ParkingCharge charge){
        
        charges.add(charge);
    }
    public List<ParkingCharge> getCharges(){
        
        return charges;
    }
    
    public void setIsDiscounted(Boolean discounted) {
      
      this.isDiscounted = discounted;
      setLotStrategy(discounted);
      
    }
    public void setAddress(Address address){
      
      this.address = address;
    }
    public TransactionManager getTransManager(){
        
        return transManager;
        
    }
    public void setCapacity(int capacity){
      
      this.maxCapacity = capacity;
      
    }
    
    public List<ParkingAction> getObservers(){
        
        return observers;
        
    }
    public void setCalcRate(Long rate) {
    	
    	this.calcRate = rate;
    }
    
    public Long getCalcRate() {
    	
    	return calcRate;
    	
    }
    
    public Boolean getIsDiscounted(){
      
      return isDiscounted;
      
    }
    public void setLotStrategy(boolean discounted){
      
      if (discounted){
        
        lotStrategy = new DiscountCharge();
      } else {
        
        lotStrategy = new StandardCharge();
      }
    
      
           
        
      }
      
    public void setRemainingCapacity(int remaining){
        
        this.remainingCapacity = remaining;
        
    }
    public int getRemainingCapacity(){
        
        return remainingCapacity;
        
    }
    public IChargeStrategy getLotStrategy(){
      
      return lotStrategy;
      
    }
    public void setIsHourlyRate(Boolean isHourly) {
      
      this.isHourlyRate = isHourly;
      
    }
    
    public Boolean getIsHourlyRate(){
      
      return isHourlyRate;
      
    }
    
    public String getLotId(){
        
        return lotId;
        
    }
    public int getMaxCapacity(){
        
        return maxCapacity;
        
    }
    
    public void setRate(long rate){
      
      this.rate = rate;
      
    }
    public void setID(String id){
      
      this.lotId = id;
      
    }
    //may be reading too much into this, but the parking lot system would be scanning permits
    //also assists if we need to know what cars are in the lots.
    public void entry(Car car){
      
      
      occupants.add(car);
      ParkingEvent event = new ParkingEvent(Instant.now(), 
              ParkingEventType.entry,car.getPermit(), this);
      
      //remainingCapacity = remainingCapacity - 1;
      
      notifyObservers(event);
      
      
    }
    public void exit(Car car){
      
      
      occupants.remove(car);
      ParkingEvent event = new ParkingEvent(Instant.now(), 
              ParkingEventType.exit,car.getPermit(), this);
      //remainingCapacity = remainingCapacity + 1;
      
      notifyObservers(event);
      //test the decorators
      testDecorators();
    }
    
    public void notifyObservers(ParkingEvent parkingEvent){
        
        Iterator<ParkingAction> observer = observers.iterator();
        
        while (observer.hasNext()){
            
            observer.next().update(parkingEvent);
            
        }
        
    }
    public Boolean getIsFull(){
      
      return isFull;
      
    }
    public void setIsFull(boolean isFull){
        
        this.isFull = isFull;
    }
    public Address getAddress() {
    	
    	return address;
    	
    }
   
    public Long getRate() {
    	
    	return rate;
    	
    }
    public boolean registerObserver(ParkingAction observer){
        
        
        this.observers.add(observer);
        if (observers.contains(observer)){
            //successful
            return true;
            
        }else {
            
            return false;
            
        }
        
    }
    public boolean unregisterObserver(ParkingAction observer){
        
        observers.remove(observers.indexOf(observer));
        //privide a mechanism to determine if the methos was successful
        if (observers.contains(observer)){
            //not successful
            return false;
            
        } else {
            
            return true;
            
        }
        
        
    }
    public void removeAllObservers(){
        
        int x = observers.size();
        
        for(int i = 0; i < x; i++){
            
            observers.remove(0);
            
        }
        
        
        
    }
    public Money testDecorators(){
      
      Money charge = new Money(this.rate);
      int i = transManager.getTransactions().size() - 1;
      ParkingTransaction transact = transManager.getTransactions().get(i);
      
      if (this.remainingCapacity < 10){
        
        FlatRateDecorator flat =  new FlatRateDecorator(new RateCalculator());
        charge = flat.getParkingCharge(transact.getEntry(), transact.getExit(), this, 
            transact.getPermit());
        
      } else if ((transact.getPermit().getCar().getType() == CarType.COMPACT) && !isHourlyRate){
        
          CompactCarDecorator compact =  new CompactCarDecorator(new RateCalculator());
          charge = compact.getParkingCharge(transact.getEntry(), transact.getExit(), this, 
            transact.getPermit());
      
      } else {
        
          HourlyRateDecorator hourly =  new HourlyRateDecorator(new RateCalculator());
          charge = hourly.getParkingCharge(transact.getEntry(), transact.getExit(), this, 
            transact.getPermit());
        
      }
      
      return charge;
      
    }
    
    @Override
      public String toString() {
        return String.format("%s %d", lotId, maxCapacity);
     }
    
    
    @Override
    public boolean equals(Object obj) {
    	
        if (!(obj instanceof ParkingLot))
            return false;
        if (obj == this)
            return true;
        
        
        return this.lotId == ((ParkingLot) obj).getLotId()
            && this.address.equals(((ParkingLot)obj).address)
        		&& this.maxCapacity == ((ParkingLot)obj).getMaxCapacity()
            && this.remainingCapacity == ((ParkingLot) obj).getRemainingCapacity()
            && this.isFull == ((ParkingLot)obj).getIsFull()
            && this.rate == ((ParkingLot)obj).getRate();
    }
    @Override
    public int hashCode() {
    	
      
    	return Objects.hash(lotId, address.getAddressInfo(),
    			maxCapacity, remainingCapacity, isFull, rate);
    }
}
    
    
    
    
    

