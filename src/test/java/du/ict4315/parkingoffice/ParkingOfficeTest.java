/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package du.ict4315.parkingoffice;

import du.ict4315.car.Car;
import du.ict4315.car.CarType;
import du.ict4315.car.Permit;
import du.ict4315.charges.Money;
import du.ict4315.charges.ParkingCharge;
import du.ict4315.customer.Customer;
import du.ict4315.customer.Address;
import du.ict4315.parkinglot.ParkingLot;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Bobby Vocque
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParkingOfficeTest {
    
    
    //using this to test the customer collection at the end
    static ParkingOffice po;
    Car car;
    Permit permit;
    Address  lotAddress;
    ParkingLot lot;
    LocalDateTime ldt;
    DayOfWeek thisDay;
    
   
    public ParkingOfficeTest() {
      
      po = new ParkingOffice();
      car = new Car("BO007","JB-007",CarType.SUV);
      permit = new Permit("ASC123", car, LocalDate.now(), 
      LocalDate.now().plusYears(1L));
      lotAddress = new Address("123 z Street", "", "Acity", "IL","12345");
      lot = new ParkingLot("Lot01",lotAddress, 175L, 50, false, false );
      
    }

    /**
     * Test of setName method, of class ParkingOffice.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Parking Office";
        ParkingOffice instance = new ParkingOffice();
        instance.setName(name);
        assertEquals(name,  instance.getName());
    }

    
    /**
     * Test of getAddress method, of class ParkingOffice.
     */
    @Test
    public void testGetAddress() {
        System.out.println("set/getAddress");
        //ParkingOffice instance = new ParkingOffice();
        Address address = new Address("99 E St", "", "Cold", "AK", "90001");
        po.setAddress(address);
        
        Address result = po.getAddress();
        assertEquals(address, result);
        
    }

    /**
     * Test of register method, of class ParkingOffice.
     */
    @Test
    public void testRegister_3args_1() {
        System.out.println("register");
        String fName = "Willy";    
        String lName = "Wonka";
        Address address = new Address("123 Abc St", "", "Home", "IL", "12345");
        
        String phone = "4445551212";
        
        Customer result = po.register(fName, lName,  address, phone);
        assertEquals("Wi1212", result.getId());
        assertEquals(fName + " " + lName,  result.getName());
        assertEquals(address, result.getAddress());
        assertEquals(phone, result.getPhoneNumber());
        
    }

    /**
     * Test of register method, of class ParkingOffice.
     */
    @Test
    public void testRegister_3args_2() {
        System.out.println("register");
        //ParkingOffice instance = new ParkingOffice();
        String fName = "James";
        String lName = "Kirk";
        Address address = new Address("456 DEF St","", "Home","IL", "12345");
        String phone = "4445551213";
        Customer c = po.register(fName, lName,  address, phone);
        String license = "DEF456";
        //CarType t = CarType.COMPACT;
        Permit result = po.register(c.getId(), license, "COMPACT");
        
        assertEquals("JaDEF456COMPACT", result.getId());  //register generates the id from the three
        //fields
        assertEquals(fName + " " + lName,  c.getName());
        
        
        
    }

    /**
     * Test of addCharge method, of class ParkingOffice.
     */
    @Test
    public void testAddCharge() {
      
        System.out.println("addCharge");
        ChronoUnit unit = ChronoUnit.HOURS;
        
        ParkingCharge charge = new ParkingCharge(Instant.now(), Instant.now().plus(1, unit),
            permit, lot);
        
        Money expResult = charge.getAmount();
        Money result = po.addCharge(charge);
        assertTrue(expResult.equals(result));
        
    }
    @Test
    public void testxGetCustomers() {
        //ParkingOffice instance = new ParkingOffice();
        String fName = "James";
        String lName = "Kirk";
        Address address = new Address("456 DEF St","", "Home","IL", "12349");
        String phone = "4445551213";
    
        po.register(fName, lName, address, phone);
    
       fName = "Willy";    
       lName = "Wonka";
       address = new Address("123 Abc St", "", "Home", "IL", "12345");
       phone = "4445551212";
    
       po.register(fName, lName,  address, phone);
    
       fName = "Indiana";    
       lName =  "Jones";
       address = new Address("789 Z St", "", "Acadamia", "IN", "12346");
       phone = "4995551312";
    
        po.register(fName,lName,  address, phone);
        
        fName = "Willy";    
        lName = "Wonka";
        Customer c = po.getCustomer("Willy Wonka");
        String result = c.getName();
        assertEquals(fName + " " + lName,  result);  
        
    }

 
  
  /**
   * Test of getCustomerIds method, of class ParkingOffice.
   */
  @Test
  public void testGetCustomerIds() {
    System.out.println("getCustomerIds");
    ParkingOffice instance = new ParkingOffice();
    String fName = "James";
    String lName = "Kirk";
    Address address = new Address("456 DEF St","", "Home","IL", "12349");
    String phone = "4445551213";
    
    instance.register(fName, lName, address, phone);
    
    fName = "Willy";    
    lName = "Wonka";
    address = new Address("123 Abc St", "", "Home", "IL", "12345");
    phone = "4445551212";
    
    instance.register(fName, lName,  address, phone);
    
    fName = "Indiana";    
    lName =  "Jones";
    address = new Address("789 Z St", "", "Acadamia", "IN", "12346");
    phone = "4995551312";
    
    instance.register(fName,lName,  address, phone);
    
    List<String> result = instance.getCustomerIds();
    
    assertEquals("Ja1213", result.get(0));
    assertEquals("Wi1212", result.get(1));
    assertEquals("In1312", result.get(2));
    
    
  }

  /**
   * Test of getPermitIds method, of class ParkingOffice.
   */
  @Test
  public void testGetPermitIds_0args() {
    System.out.println("getPermitIds");
    ParkingOffice instance = new ParkingOffice();
    assertEquals(0, instance.getPermitIds().size());
    
    //set up for testing, this method allows single test execution
    String fName = "James";
    String lName = "Kirk";
    Address address = new Address("456 DEF St","", "Home","IL", "12345");
    String phone = "4445551213";
    String license = "ABC123";
    
    Customer c = instance.register(fName, lName, address, phone);
    instance.register(c.getId(), license, "SUV");
    
    fName = "Willy";    lName = "Wonka";
    address = new Address("123 Abc St", "", "Home", "IL", "12345");
    phone = "4445551212";
    license = "DEF456";
    c = instance.register(fName, lName, address, phone);
    instance.register(c.getId(), license, "COMPACT");
    
    fName = "Indiana";    lName =  "Jones";
    address = new Address("789 Z St", "", "Acadamia", "IN", "12346");
    phone = "4995551312";
    license = "GHK789";
    c = instance.register(fName, lName, address, phone);
    instance.register(c.getId(), license,"SUV");
   
   
   List<String> result = instance.getPermitIds();
   
   assertEquals("JaABC123SUV", result.get(0));
   assertEquals("WiDEF456COMPACT", result.get(1));
   assertEquals("InGHK789SUV", result.get(2));
   
  }

  /**
   * Test of getPermitIds method, of class ParkingOffice.
   */
  @Test
  public void testGetPermitIds_String() {
    System.out.println("getPermitIds");
    ParkingOffice instance = new ParkingOffice();
    //set up for testing
    String fName = "James";
    String lName = "Kirk";
    Address address = new Address("456 DEF St","", "Home","IL", "12349");
    String phone = "4445551213";
    String license = "ABC123";
    
    Customer c = instance.register(fName, lName, address, phone);
    instance.register(c.getId(), license, "SUV");
    
    fName = "Willy";
    lName = "Wonka";
    address = new Address("123 Abc St", "", "Home", "IL", "12345");
    phone = "4445551212";
    license = "DEF456";
    c = instance.register(fName, lName, address, phone);
    instance.register(c.getId(), license, "COMPACT");
    instance.register(c.getId(), "XYZ890", "SUV");
    
    fName = "Indiana";
    lName =  "Jones";
    address = new Address("789 Z St", "", "Acadamia", "IN", "12346");
    phone = "4995551312";
    license = "GHK789";
   c = instance.register(fName, lName, address, phone);
   instance.register(c.getId(), license, "SUV");
   
   //test
   List<String> result = instance.getPermitIds("Willy Wonka");
    
    assertEquals("WiDEF456COMPACT", result.get(0));
    assertEquals("WiXYZ890SUV", result.get(1));
    
  }
  
  @Test
  public void xtestParkingCharge(){
    
    //set up for test
    System.out.println("Parking Charges");
    //ParkingOffice instance = new ParkingOffice();
    //set up for testing
    String fName = "James";
    String lName = "Kirk";
    Address address = new Address("456 DEF St","", "Home","IL", "12349");
    String phone = "4445551213";
    String license = "ABC123";
    
    Customer cOne = po.register(fName, lName, address, phone);
    Permit carOne = po.register(cOne.getId(), license, "SUV");
    
    fName = "Willy";
    lName = "Wonka";
    address = new Address("123 Abc St", "", "Home", "IL", "12345");
    phone = "4445551212";
    license = "DEF456";
    Customer cTwo = po.register(fName, lName, address, phone);
    Permit carTwo = po.register(cTwo.getId(), license, "COMPACT");
    Permit carThree = po.register(cTwo.getId(), "XYZ890", "SUV");
    
    //set up a lot
    address = new Address("51 Book St", "", "Home", "IL", "12345");
    String lotName = "Area51";
    po.addLot(lotName, address, 125L, 20, false, false);
    
    
    address = new Address("101 History Bvls", "", "Home", "IL", "12345");
    lotName = "Lewis";
    po.addLot(lotName, address, 100L, 20, false, false);
    
    ChronoUnit unit = ChronoUnit.HOURS;
    
    //add some transactions, alternate customers
    ParkingCharge resultOne = po.park(Instant.now(), Instant.now().plus(1, unit), 
        carTwo, po.getLots().get(1));
    ParkingCharge resultTwo = po.park(Instant.now(), Instant.now().plus(1, unit), 
        carOne, po.getLots().get(1));
    ParkingCharge resultThree = po.park(Instant.now(), Instant.now().plus(1, unit),
        carThree, po.getLots().get(1));
    ParkingCharge resultFour = po.park(Instant.now(), Instant.now().plus(1, unit),
        carOne, po.getLots().get(0));
    ParkingCharge resultFive = po.park(Instant.now(), Instant.now().plus(1, unit),
        carThree, po.getLots().get(0));
    
    ldt = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.systemDefault());
    thisDay = ldt.getDayOfWeek();
    
    double exp1 = 0.80;
    double exp2 = 1.0;
    double exp3 = 1.0;
    double exp4 = 1.25;
    double mon1 = 2.25;
    double mon2 = 0.8;
    
    if (thisDay.getValue()== 0 || thisDay.getValue() == 6){
      
        exp1 = 0.72;
        exp2 = 0.81;
        exp3 = 0.72;
        exp4 = 1.2;
        mon1 = 1.72;
        mon2 = 0.72;
      
    }
    
    System.out.println("Individual Parking Charges");
    //verify the charges (money)
    assertTrue(exp1 == resultOne.getAmount().getDollars());
    assertTrue(exp2 == resultTwo.getAmount().getDollars());
    assertTrue(exp3 == resultThree.getAmount().getDollars());
    assertTrue(exp4 == resultFour.getAmount().getDollars());
    
    System.out.println("Parking Charges by Permit");
    //now get total by permit
    Money moneyOne = po.getParkingCharges(carThree);
    Money moneyTwo = po.getParkingCharges(carTwo);
    
    //total for permit
    assertTrue(mon1 == moneyOne.getDollars());
    assertTrue(mon2 == moneyTwo.getDollars());
    
    System.out.println("Parking Charges by Customer");
    //test by customer
   
  }
  
    
}
