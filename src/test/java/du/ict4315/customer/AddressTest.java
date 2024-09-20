/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package du.ict4315.customer;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Bobby Vocque
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddressTest {
    
    public static Address testAddress;
    
    public AddressTest() {
        
        testAddress = new Address("123 A St", "Apt 7", "Gnome",
        "Somewhere", "00001");
        
    }

    /**
     * Test of getStreetAddress1 method, of class Address.
     */
    @Test
    public void testGetStreetAddress1() {
        System.out.println("getStreetAddress1");
        
        String expResult = "123 A St";
        String result = testAddress.getStreetAddress1();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getStreetAddress2 method, of class Address.
     */
    @Test
    public void testGetStreetAddress2() {
        System.out.println("getStreetAddress2");
        
        String expResult = "Apt 7";
        String result = testAddress.getStreetAddress2();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCity method, of class Address.
     */
    @Test
    public void testGetCity() {
        System.out.println("getCity");
        String expResult = "Gnome";
        String result = testAddress.getCity();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getState method, of class Address.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        
        String expResult = "Somewhere";
        String result = testAddress.getState();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getZipcode method, of class Address.
     */
    @Test
    public void testGetZipcode() {
        System.out.println("getZipcode");
        
        String expResult = "00001";
        String result = testAddress.getZipcode();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getAddressInfo method, of class Address.
     */
    @Test
    public void testGetAddressInfo() {
        System.out.println("getAddressInfo");
       
        String expResult = "123 A St Apt 7 Gnome Somewhere 00001";
        String result = testAddress.getAddressInfo();
        assertEquals(expResult, result);
        
    }
    
    
}
