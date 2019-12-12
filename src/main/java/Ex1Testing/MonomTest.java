package Ex1Testing;

import Ex1.Monom;
import Ex1.function;
import org.junit.Test;
import static junit.framework.TestCase.*;

/**
 * This class represents a simple (naive) tester for the Monom class,
 * Note: <br>
 * (i) The class is NOT a JUNIT - (i.e., educational reasons) - should be changed to a proper JUnit in Ex1. <br>
 * (ii) This tester should be extend in order to test ALL the methods and functionality of the Monom class.  <br>
 * (iii) Expected output:  <br>
 * *****  Test1:  *****  <br>
 0) 2.0    	isZero: false	 f(0) = 2.0  <br>
 1) -1.0x    	isZero: false	 f(1) = -1.0  <br>
 2) -3.2x^2    	isZero: false	 f(2) = -12.8  <br>
 3) 0    	isZero: true	 f(3) = 0.0  <br>
 *****  Test2:  *****  <br>
 0) 0    	isZero: true  	eq: true  <br>
 1) -1.0    	isZero: false  	eq: true  <br>
 2) -1.3x    	isZero: false  	eq: true  <br>
 3) -2.2x^2    	isZero: false  	eq: true  <br>
 */
public class MonomTest {

    private final static double chk = 0.00001;

    @Test
    public void testMonomcopy() {
        Monom m1 = new Monom(2.17,4);
        Monom m2 = new Monom(m1);
        if(m1.get_coefficient() != (m2.get_coefficient())) {
            fail("Error in copy constructor");
        }
    }

    @Test
    public void testMonomtoString() {
        double kido = -2.21;
        int power = 12;
        Monom m1 = new Monom(kido, power);
        Monom m2 = new Monom(m1);
        String s = m1.toString();

        if(!s.equals(m2.toString())) {
            fail("Error in the toString function");
        }
    }

    @Test
    public void testDerivative() {
        double coef = 5;
        int power = 2;
        Monom m1 = new Monom(coef,power);
        Monom m2 = m1.derivative();
        assertTrue(m2.get_coefficient() == 10);
        assertTrue(m2.get_power() == 1);
    }

    @Test
    public void testAdd() {
        double conf = 3, conf2 = 3;
        int power = 3;
        Monom m1 = new Monom(conf,power);
        Monom m2 = new Monom(conf2,power);
        m1.add(m2);
        assertEquals(m1.get_coefficient(), conf+conf2,chk);
    }

    @Test
    public void testMultiply() {
        double conf = 4, conf2 = 5;
        int pow1 = 5, pow2 = 5;
        Monom m1 = new Monom(conf,pow1);
        Monom m2 = new Monom(conf2,pow2);
        Monom m3 = m1.multiply(m2);
        assertEquals(m3.get_coefficient(), conf * conf2,chk);
        assertEquals(m3.get_power(), pow1 + pow2,chk);
    }

    @Test
    public void testInitFromStringFunction(){
        function m = new Monom("3x^2");
        assertTrue("3.0x^2".equals(m.toString()));
    }

    @Test
    public void testMonomFunctioncopy() {
        Monom m1 = new Monom(2.17,4);
        function m2 = new Monom(m1);
        assertTrue("2.17x^4".equals(m2.toString()));
    }

    @Test
    public void testMonomEqualsOoject() {
        Monom m1 = new Monom(2.17,4);
        Object m2 = new Monom(2.17,4);
        assertTrue(m1.toString().equals(m2.toString()));
    }

}
