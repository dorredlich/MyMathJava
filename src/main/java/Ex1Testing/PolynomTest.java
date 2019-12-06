package Ex1Testing;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class PolynomTest {

    @Test
    public void testToStringInit() throws Exception {
        Polynom mp = new Polynom ("2*x^5+3*x^1");
        assertTrue("2.0x^5+3.0x".equals(mp.toString()));
    }

    @Test
    public void polynomSub() throws Exception {
        Polynom mp = new Polynom ("2*x^5+3*x^1");
        Polynom bp = new Polynom ("4*x^2+3*x^3+2");
        mp.substract(bp);
        assertTrue("2.0x^5-3.0x^3-4.0x^2+3.0x-2.0".equals(mp.toString()));
    }

    @Test
    public void testPolyonomcopy() throws Exception {
        Polynom bp = new Polynom ("4*x^2+3*x^3+2");
        Polynom m2 = new Polynom(bp);
        if(!bp.equals(m2))
            fail("Error in copy constructor or in equals");
    }

    @Test
    public void testPolyonomString() throws Exception {
        Polynom p = new Polynom ("3x^2-2x+5*x^5");
        String s= "5.0x^5+3.0x^2-2.0x";
        if(!s.equals(p.toString()))
            fail("Error");
    }

    @Test
    public void testPolyonomArea() throws Exception {
        Polynom p1 = new Polynom();
        String[] monoms = {"1", "x", "x^2", "0.5x^2"};
        Monom m = new Monom(monoms[1]);
        p1.add(m);
        double area = p1.area(0, 1, 0.0001);
        if(area < 0.4 || area > 0.6)
            fail("Error in the area function");
    }

    @Test
    public void testPolyonomRoot() throws Exception {
        Polynom p =new Polynom("2x-1");
        double res = p.root(-1, 1, 0.01);
        if(res>0.6||res<0.4)
            fail("Error in the root function");
    }

    @Test
    public void testPolyonomDerivative() throws Exception{
        Polynom p1 = new Polynom ("x^2-1");
        Polynom_able p2 = p1.derivative();
        if(!p2.toString().equals("2.0x"))
            fail("Error in the derivative function");
    }

    @Test
    public void testPolyonomMultMonom() throws Exception {
        Polynom p1 = new Polynom ("2x^2-1");
        double kido = 2;
        int power = 3;
        Monom m1 = new Monom(kido, power);
        p1.multiply(m1);
        if(!p1.toString().equals("4.0x^5-2.0x^3"))
            fail("Error in the mult function");
    }

    @Test
    public void testPolyonomMult() throws Exception {
        Polynom p1 = new Polynom ("x^2-1");
        Polynom p2 = new Polynom ("x^2+1");
        p1.multiply(p2);
        if(!p1.toString().equals("x^4-1.0"))
            fail("Error in the mult function");
    }

    @Test
    public void testPolyonomAddMonom() throws Exception {
        Polynom p1 = new Polynom("x^2-1");
        Monom m = new Monom (1 ,2);
        p1.add(m);
        if(!p1.toString().equals("2.0x^2-1.0"))
            fail("Error in the addMonom function");
    }

    @Test
    public void testPolyonomAddPolynom() throws Exception {
        Polynom p1 = new Polynom("x^2-1");
        Polynom_able p2 = new Polynom("x^2-1");
        p1.add(p2);
        if(!p1.toString().equals("2.0x^2-2.0"))
            fail("Error in the add Polynom to polynom function");
    }

    @Test
    public void testPolyonomValueAtX() throws Exception {
        Polynom p =new Polynom("2x");
        if(p.f(2)!=4)
            fail("Error in the Value at x function");
    }
}





