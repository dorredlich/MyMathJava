package myMath;


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



    public static void main(String[] args) {


        boolean test1 = testMonomcopy();
        System.out.println("Test Monom copy : " + test1);
        testMonomString();
        boolean test2 = testDerivative();
        System.out.println("Test Monom derivative : "+ test2);
        testAdd();
        testMultiply();

    }

    public static boolean testMonomcopy() {
        Monom m1 = new Monom(2.17,4);
        Monom m2 = new Monom(m1);
        if(m1.get_coefficient() != (m2.get_coefficient())) {
            return false;
        }
        return true;
    }

    public static void testMonomString(){
        Monom m1 = new Monom("x");
        System.out.println(m1.toString());
    }


    public static boolean testDerivative() {
        double coef = 5;
        int power = 2;
        Monom m1 = new Monom(coef,power);
        Monom m2 = m1.derivative();
        if(m2.get_coefficient() == 10 && m2.get_power() == 1){
            return true;
        }
        return false;
    }

    public static void testAdd() {
        double conf = -5, conf2 = 5;
        int pow1 = 5, pow2 = 5;
        Monom m1 = new Monom(conf, pow1);
        Monom m2 = new Monom(conf2,pow2 );
        try{
            m1.add(m2);
            System.out.println(m1);
        }catch (Exception e){
            if(m1.get_power() != m2.get_power())
                System.out.println("The power not equals");
            if(m1.get_coefficient() + m2.get_coefficient() == 0)
                System.out.println("0");
        }
    }

    public static void testMultiply() {
        double conf = 4, conf2 = 5;
        int pow1 = 5, pow2 = 5;
        Monom m1 = new Monom(conf,pow1);
        Monom m2 = new Monom(conf2,pow2);
        Monom m3 = new Monom(m1.multiply(m2));
        String result = m3.toString();
        System.out.println(result);
    }
}
