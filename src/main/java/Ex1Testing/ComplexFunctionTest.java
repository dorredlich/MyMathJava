package Ex1Testing;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import Ex1.ComplexFunction;
import Ex1.Polynom;
import Ex1.function;


public class ComplexFunctionTest {

    @Test
    void testFComplexFunction()throws Exception {
        ComplexFunction cf = new ComplexFunction("Plus",new Polynom("x^2+3+15x"), new Polynom("7"));
        double expected = 64;
        double actual = cf.f(3);
        assertEquals(expected, actual,"test if the result is what we expect with x = 3");

        ComplexFunction cf2 = new ComplexFunction("Times",new Polynom("x^2+3.5x-9"), cf);

        double expected2 = 1806;
        double actual2 = cf2.f(4);

        assertEquals(expected2, actual2, "test f for  x = 4");
    }

    @Test
    void testCopyComplexFunction()throws Exception {
        ComplexFunction cf = new ComplexFunction("Plus",new Polynom("x^2+3+15x"), new Polynom("7"));
        function cf2 =  cf.copy();

        assertEquals(cf, cf2, "we expected true beacase the two functions represent the same functions");
    }

    @Test
    void testInitFromString() throws Exception{
        String[] s3 = {"2.0x^3+3", "3.0x^2+5.0x-2", "x -4"};
        Polynom p3 = new Polynom(s3[0]);
        ComplexFunction cf = new ComplexFunction(p3);
        for(int i = 1;i < s3.length;i++) {
            cf.mul(new Polynom(s3[i]));
        }
        System.out.println(cf);
        assertTrue("Times(Times(2.0x^3+3.0,3.0x^2+5.0x-2.0),1.0x-4.0)".equals(cf.toString()));



    }

    @Test
    void testEqualsComplexFunction()throws Exception {
        ComplexFunction cf = new ComplexFunction("Plus",new Polynom("x^2+3+15x"), new Polynom("7"));
        ComplexFunction cf2 = new ComplexFunction(new Polynom("7"));
        ComplexFunction cf3 = new ComplexFunction("Plus",new Polynom("x^2+3+15x"), cf2);

        assertEquals(cf, cf3,"test to check if cf and cf3 are equals");
    }

    @Test
    void testPlusComplexFunction() throws Exception{
        ComplexFunction first = new ComplexFunction("Plus",new Polynom("x^2+3+15x"), new Polynom("2.0x^6-5x"));
        double expected = 8251.0;
        double actual = first.f(4);
        assertEquals(expected, actual,"cf2 is what we expexted to be after operation Plus");

    }

    @Test
    void testMulComplexFunction()throws Exception {
        ComplexFunction first = new ComplexFunction("Times",new Polynom("x^2+3+15x"), new Polynom("1.0x^3+2.3x^2"));
        double expected = 7963.2;
        double actual = first.f(4);

        assertEquals(expected, actual,"cf2 is what we expexted to be after operation Times");
    }

    @Test
    void testDivComplexFunction()throws Exception {
        ComplexFunction cf = new ComplexFunction("Divid",new Polynom("x^2+3+15x"), new Polynom("7x"));
        double expected = 2.8214285714285716;
        double actual = cf.f(4);

        assertEquals(expected, actual,"test for operation div ");
    }

    @Test
    void testMaxComplexFunction()throws Exception {
        ComplexFunction cf = new ComplexFunction("Max",new Polynom("x^2+3+15x"), new Polynom("x^3-x^2+5"));

        double expected = Math.max(cf.left().f(3.3), cf.right().f(3.3));
        double actual = cf.f(3.3);

        assertEquals(expected, actual,"test the operation Max with x = 3.3");
    }

    @Test
    void testMinComplexFunction()throws Exception {
        ComplexFunction cf = new ComplexFunction("Min",new Polynom("x^2+3+x"), new Polynom("x^4-x^2+5"));

        double expected = Math.min(cf.left().f(3.3), cf.right().f(3.3));
        double actual = cf.f(3.3);

        assertEquals(expected, actual,"test the operation Min with x = 3.3");
    }

    @Test
    void testCompComplexFunction()throws Exception {
        ComplexFunction cf = new ComplexFunction("Comp",new Polynom("x^2+3+x"), new Polynom("x^2"));

        double expected = 132.48209999999997;
        double actual = cf.f(3.3);

        assertEquals(expected, actual,"test the operation Comp with x = 3.3");

    }
}

