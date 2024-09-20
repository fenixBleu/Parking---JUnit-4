/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package du.ict4315.charges;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bobby Vocque
 */
public class MoneyTest {
    
    public MoneyTest() {
    }
    
    

    /**
     * Test of setCents method, of class Money.
     */
    @Test
    public void testSetCents() {
        System.out.println("setCents unsing default construtor");
        Long cents = 123456L;
        Money instance = new Money();
        instance.setCents(cents);
        assertEquals(cents, instance.getCents());
        assertEquals(1234,56, instance.getDollars());
        
    }

    /**
     * Test of getCents method, of class Money.
     */
    @Test
    public void testGetCents() {
        System.out.println("getCents using override constructor");
        
        Long expResult = 156L;
        Money instance = new Money(expResult);
        Long result = instance.getCents();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getDollars method, of class Money.
     */
    @Test
    public void testGetDollars() {
        System.out.println("getDollars with default constructor");
        Money instance = new Money();
        double expResult = 0.0;
        double result = instance.getDollars();
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of toString method, of class Money.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Money instance = new Money();
        instance.setCents(123450L);
        String expResult = "1,234.50";
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }
    
}
