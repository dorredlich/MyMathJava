package Ex1Testing;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Range;
import Ex1.function;


public class ComplexFunctionTest {


     public static void main(String[] args) throws Exception{
        test1();


    }

    public static void test1()throws Exception{
        String s1 = "3.1 +2.4x^2 -x^4";
        String s2 = "5 +2x -3.3x +0.1x^5";
        String[] s3 = {"x +3","x -2", "x -4"};
        String s4 = "Times(Times(Times(1.0x+3.0,1.0x-2.0),1.0x-2.0),1.0x-4.0)";
        Polynom p1 = new Polynom(s1);
        Polynom p2 = new Polynom(s2);
        Polynom p3 = new Polynom(s3[0]);
        ComplexFunction cf4 = new ComplexFunction(p3);
        ComplexFunction cf3 = new ComplexFunction(p3);
        System.out.println("cf3: " + cf3);
       for(int i=1;i<s3.length;i++) {
            cf3.mul(new Polynom(s3[i]));
       }
        System.out.println("cf3: " + cf3);
        System.out.println("ans = " + cf3.f(3));
        function cf5 = cf3.initFromString(s1);
        System.out.println(cf4);
    }
}
