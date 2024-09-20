/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package du.ict4315.charges;

import du.ict4315.charges.factory.DiscountCharge;
import du.ict4315.charges.factory.ParkingChargeFactory;
import du.ict4315.charges.factory.StandardCharge;
import org.junit.Test;
import static org.junit.Assert.*;
import du.ict4315.charges.factory.IChargeStrategy;

/**
 *
 * @author vocqueb
 */
public class ChargeFactoryTest {
  
  public ChargeFactoryTest() {
  }

  /**
   * Test of getStrategy method, of class ChargeFactory.
   */
  @Test
  public void testGetStrategyDiscount() {
    System.out.println("getStrategy = Discount");
    Boolean discount = true;
    ParkingChargeFactory instance = new ParkingChargeFactory();
    IChargeStrategy expResult = new DiscountCharge();
    IChargeStrategy result = instance.getStrategy(discount);

    assertEquals(expResult.getClass(), result.getClass());
   
  }
  @Test
  public void testGetStrategyStandard() {
    System.out.println("getStrategy = Standard");
    Boolean discount = false;
    ParkingChargeFactory instance = new ParkingChargeFactory();
    IChargeStrategy expResult = new StandardCharge();
    IChargeStrategy result = instance.getStrategy(discount);

    assertEquals(expResult.getClass(), result.getClass());
   
  }
  
}
