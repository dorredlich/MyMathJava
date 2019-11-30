package myMath;

public class PolynomTest {
    public static void main(String[] args) throws Exception {

        boolean test1 = testToStringInit();
        System.out.println("Test polynom equals : " + test1);
        polynomAdd();
        polynomSub();
        boolean test4 = testPolyonomcopy();
        System.out.println("Test polynom copy : " + test4);
        testPolyonomArea();
        boolean test6 = testPolyonomRoot();
        System.out.println("Test Polynom root : " + test6);
        boolean test7 = testPolyonomDerivative();
        System.out.println("Test Polynom derivative : " + test7);
        boolean test8 = testPolyonomMult();
        System.out.println("Test Polynom mult : " + test8);
        boolean test9 = testPolyonomAddMonom();
        System.out.println("Test Polynom add monom : " + test9);
        boolean test10 = testPolyonomAddPolynom();
        System.out.println("Test Polynom add Polynom : " + test10);
        boolean test11 = testPolyonomValueAtX();
        System.out.println("Test f function : " + test11);

    }


    private static boolean testToStringInit() {
        Polynom p1 = new Polynom(), p2 = new Polynom();
        String[] monoms1 = {"5", "1.7x", "3.2x^2", "-3", "-1.5x^2"};
        String[] monoms2 = {"5", "1.7x", "3.2x^2", "-3", "-1.5x^2"};
        for (int i = 0; i < monoms1.length; i++) {
            Monom m = new Monom(monoms1[i]);
            p1.add(m);
        }
        for (int i = 0; i < monoms2.length; i++) {
            Monom m = new Monom(monoms2[i]);
            p2.add(m);
        }
        if (p1.equals(p2))
            return true;
        return false;
    }

    private static void polynomAdd() throws Exception{
        Polynom p1 = new Polynom("-4*x^5+3*x^1");
        Polynom p2 = new Polynom("4*x^5+3*x^3+2");
        p1.add(p2);
        System.out.println(p1);
    }


    private static void polynomSub() throws Exception {
        Polynom mp = new Polynom("2*x^5+3*x^1");
        Polynom bp = new Polynom("4*x^5+3*x^3+2");
        mp.substract(bp);
        System.out.println(mp);
    }

    private static boolean testPolyonomcopy() throws Exception {
        Polynom bp = new Polynom("4*x^2+3*x^3+2");
        Polynom m2 = new Polynom(bp);
        if (!bp.equals(m2))
            return false;
        return true;
    }


    private static void testPolyonomArea() {
        Polynom p1 = new Polynom();
        String[] monoms = {"1", "x", "x^2", "0.5x^2"};
        Monom m = new Monom(monoms[1]);
        p1.add(m);
        double area = p1.area(0, 1, 0.0001);
        System.out.println(area);
    }

    private static boolean testPolyonomRoot() {
        Polynom p = new Polynom();
        String[] monoms1 = {"1", "x", "x^2", "0.5x^2"};
        for (int i = 0; i < monoms1.length; i++) {
            Monom m = new Monom(monoms1[i]);
            p.add(m);
        }
        double res = p.root(-1, 1, 0.01);
        if (res > 0.6 || res < 0.4)
            return false;
        return true;
    }

    private static boolean testPolyonomDerivative()throws Exception  {
        Polynom p1 = new Polynom("x^2-1");
        Polynom p =  new Polynom("2x");
        Polynom_able p2 = p1.derivative();
        if (!p2.equals(p))
            return false;
        return true;
    }

    private static boolean testPolyonomMult() throws Exception {
        Polynom p1 = new Polynom(), p2 = new Polynom();
        String[] monoms1 = {"1", "x^2"};
        String[] monoms2 = {"-1", "x^2"};
        for (int i = 0; i < monoms1.length; i++) {
            Monom m = new Monom(monoms1[i]);
            p1.add(m);
        }
        for (int i = 0; i < monoms2.length; i++) {
            Monom m = new Monom(monoms2[i]);
            p2.add(m);
        }
        Polynom p3 = new Polynom ("2x^4-1.0");
        p1.multiply(p2);
        if(!p1.toString().equals(p3.toString()))
            return false;
        return true;
    }
    private static boolean testPolyonomAddMonom() throws Exception {
        Polynom p1 =new Polynom("x^2-1");
        Monom m =new Monom (1 ,2);
        Polynom p2 = new Polynom("3.0x^2-1.0");
        p1.add(m);
        if(!p1.equals(p2))
            return false;
        return true;
    }

    private static boolean testPolyonomAddPolynom() throws Exception {
        Polynom p1 =new Polynom("x^2-1");
        Polynom_able p2 =new Polynom("x^2-1");
        Polynom p3 = new Polynom("4.0x^2-2.0");
        p1.add(p2);
        if(!p1.equals(p3))
            return false;
        return true;
    }

    private static boolean testPolyonomValueAtX() throws Exception {
        Polynom p = new Polynom("2x-1");
        if(p.f(2) != 3)
            return false;
        return true;
    }
}





