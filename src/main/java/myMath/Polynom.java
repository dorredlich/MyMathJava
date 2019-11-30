package myMath;

import java.util.ArrayList;
import java.util.Iterator;



public class Polynom implements Polynom_able {

    /**
     * Class's parameters:
     *
     * @param cmp : Object of the Comperator class
     * @param M : The ArrayList that the polynom is stored in
     *
     * @author Dor Redlich
     *
     *
     */
    private ArrayList<Monom> M = new ArrayList<Monom>();
    private final Monom_Comperator cmp = new Monom_Comperator();

    public Polynom(Polynom p) {
        M = new ArrayList<>();
        Iterator<Monom> it = p.iteretor();

        while(it.hasNext()) {
            Monom a = new Monom(it.next());
            add(a);
        }
    }
    /**
     * Constructor which gets String and turn it to Polynom with ArrayList
     * @param: poly: represent the String of Polynom
     * @throws Exception In case the String we've got is Invalid
     */
    public Polynom (String s) throws Exception {
        s = s.replaceAll(" ", "");
        s = s.replaceAll("X", "x");
        s = s.replaceAll("\\*","");
        s = s.replaceAll("\\^","");

        String sMonom;
        int Index_helper = 0;
        int MonomsPower;
        double MonomsCoefficient;
        Monom k;

        for(int i = 0 ;i < s.length(); i++) {
            if((s.charAt(i) == '+' || s.charAt(i) == '-') && (i != 0)) {
                sMonom = s.substring(Index_helper, i);

                if(s.charAt(i) == '-')
                    Index_helper = i ;
                else
                    Index_helper = i+1;

                if(!sMonom.contains("x")) {
                    if(!isNumeric(sMonom)) throw new Exception("Invalid Input of :" + sMonom); //
                    k = new Monom(Double.parseDouble(sMonom),0);
                    add(k);
                }
                else {
                    MonomsPower = getPowerForString(sMonom);
                    MonomsCoefficient = getCoefficientForString(sMonom);
                    k = new Monom(MonomsCoefficient,MonomsPower);
                    add(k);
                }
                //Inside split (getting the substring of the monom and deviding it
                //to coefficient and power )
            }
        }
        sMonom = s.substring(Index_helper, s.length());
        if(!sMonom.contains("x")) {
            if(!isNumeric(sMonom)) throw new Exception("Invalid Input of :" + sMonom); //
            k = new Monom(Double.parseDouble(sMonom),0);
            add(k);
        }
        else {
            MonomsPower = getPowerForString(sMonom);
            MonomsCoefficient = getCoefficientForString(sMonom);
            k = new Monom(MonomsCoefficient,MonomsPower);
            add(k);
        }
    }
    private double getCoefficientForString(String s) throws Exception {
        for(int i = 0;i < s.length(); i++) {
            if(s.charAt(i) == 'x') {
                if(i == 0) {
                    return 1.0;
                }
                else if(s.substring(0, i).equals("-"))
                    return -1;
                else if(!isNumeric(s.substring(0, i))) throw new Exception("Invalid input of :" + s.substring(0, i));
                else return Double.parseDouble(s.substring(0, i));
            }
        }
        return 0;
    }
    private int getPowerForString(String s) throws Exception {
        int xIndex = s.indexOf('x');  //need to be checked
        if (xIndex == s.length()-1) return 1;
        if(!isNumeric(s.substring(xIndex+1, s.length()))) throw new Exception("Invalid input of : " + s.substring(xIndex+1, s.length()));
        return Integer.parseInt(s.substring(xIndex+1, s.length()));
    }


    /**
     * default Constructor
     */
    public Polynom() {
        M = new ArrayList<Monom>();
    }
    /**
     * @param x use the value of x to calculate the polynom's value
     * Calculate the value of a Polynom given for specific X
     */
    @Override
    public double f(double x) {
        double sum = 0;
        Iterator <Monom> itr = iteretor();
        while(itr.hasNext()) {
            Monom m = new Monom(itr.next());
            sum += m.f(x);
        }
        return sum;
    }
    /**
     * @param p1 ArrayList of Monoms
     * adding one Polynom to another
     *
     */
    @Override
    public void add(Polynom_able p1) {
        Iterator <Monom> itr = p1.iteretor();
        while(itr.hasNext()) {
            Monom m = new Monom(itr.next());
            add(m);
        }
    }
    /**
     * @param m1 is Monom which we need to add to the Polynom
     * adding a given Monom to the Polynom
     */
    @Override
    public void add(Monom m1) {
        Iterator <Monom> itr = M.iterator();
        if(m1.get_coefficient() == 0) {
            return;
        }
        if(!itr.hasNext()) {               //if the polynom is empty add m1 directly
            M.add(new Monom(m1));
            return;
        }
        while(itr.hasNext()) {
            Monom m = itr.next();
            if(m.get_power() == m1.get_power()) {
                m.set_coefficient(m1.get_coefficient() + m.get_coefficient());
                if(m.get_coefficient() == 0)
                    itr.remove();                    //if m.coefficient was -m1.coefficient i want to remove
                return;                              // m because it's got reseted , coefficient now's equal to 0
            }
        }
        M.add(new Monom(m1));
        M.sort(cmp);
    }
    /**
     * @p1 The Polynom that we substract from the Polynom that the method was called on
     * Subtracting one Polynom from another
     */
    @Override
    public void substract(Polynom_able p1) {
        Iterator <Monom> itr = p1.iteretor();
        while(itr.hasNext()) {
            Monom m = itr.next();
            m.set_coefficient(-m.get_coefficient());
            add(m);
        }
    }
    /**
     *
     * Multiplying the polynom by the p1 polynom
     *
     * @param p1 the Polynom we multiply by
     */
    @Override
    public void multiply(Polynom_able p1) {
        Iterator<Monom> q1 = this.iteretor();
        Iterator<Monom> q2 = p1.iteretor();
        Polynom temp = new Polynom();
        while(q1.hasNext()) {
            Monom a = new Monom (q1.next());
            while(q2.hasNext()) {
                Monom b = new Monom (q2.next());
                Monom x = new Monom(a.multiply(b));
                temp.add(x);
            }
            q2 = p1.iteretor();
        }
        temp.M.sort(cmp);
        M = temp.M;
    }
    /**
     * Checking if 2 polynoms are equal
     *
     * @param p1 the polynom we compare to
     *
     *
     */
    @Override
    public boolean equals(Polynom_able p1) {
        Iterator <Monom> itr1 = p1.iteretor();
        Iterator <Monom> itr2 = M.iterator();
        while(itr1.hasNext() && itr2.hasNext()) {
            Monom m1 = itr1.next();
            Monom m2 = itr2.next();
            if((m1.get_coefficient() != m2.get_coefficient()) || (m1.get_power() != m2.get_power()))
                return false;
        }
        if((itr1.hasNext() && !itr2.hasNext()) || (!itr1.hasNext() && itr2.hasNext())) //there is still a chance which i finished checking and the
            return false;                                                 //the polynoms are'nt equal (happens when one is prefix of the other
        else                                                             //and the other still has Monoms left)
            return true;
    }
    /**
     *
     * Is the Polynom equals '0'
     *
     * @return the answer: true/false
     */
    @Override
    public boolean isZero() {
        Iterator<Monom> it = M.iterator();
        if(it.hasNext())
            return false;
        else
            return true;
    }
    /**
     * calculates the solution of the (polynom=0)
     * @param x0 starting point
     * @param x1 ending point
     * @param eps step value(size)
     * @return the solution
     */
    @Override
    public double root(double x0, double x1, double eps) {
        double mid = 0;
        double start = x0;
        double end = x1;
        while(end - start >= eps) {
            mid = (start + end)/2;
            if(f(mid) == 0)
                return mid;
            else if(f(mid) * f(end) < 0)
                start = mid;
            else
                end = mid;
        }
        return mid;
    }
    /**
     * fucnction that copies a polynom and returns it
     * @return return new copy of polynom
     */
    @Override
    public Polynom_able copy() {
        Polynom New = new Polynom();
        Iterator<Monom> it = this.iteretor();
        while(it.hasNext()) {
            Monom x = new Monom(it.next());
            New.add(x);
        }
        return New;
    }
    /**
     * @return returning the polynom after the derivative of each Monom
     * derivative of the Monoms
     */
    @Override
    public Polynom_able derivative() {
        Iterator <Monom> it = iteretor();
        Polynom temp = new Polynom();
        while(it.hasNext()) {
            Monom m = new Monom(it.next());
            temp.add(m.derivative());
        }
        return temp;
    }
    /**
     * @param x0 starting point of the integral
     * @param x1 ending point of the integral
     * @param eps representing the size steps
     * @return returning reiman's integral
     */
    @Override
    public double area(double x0, double x1, double eps) {
        double sum = 0;
        double n = ((x1-x0) * (f(x1) - f(x0)))/eps;
        double delta =(x1-x0)/n;
        double newDelta = x0;
        for(int i = 0; i < n; i++) {
            sum += f(newDelta) * delta;
            newDelta += delta;
        }
        return sum;
    }
    /**
     * @return returning iterator pointing to the ArrayList
     */
    @Override
    public Iterator<Monom> iteretor() {
        Iterator <Monom> itr = M.iterator();
        return itr;
    }
    /**
     * printing the array of the Polynom
     */
    public String toString() {
        String ans = "";
        Iterator<Monom> itr = this.iteretor();

        int i = 0;
        boolean isneg = false;
        while(itr.hasNext()) {
            Monom a = new Monom(itr.next());
            if(a.get_coefficient() < 0 || i == 0) {
                isneg = true;
                ans +=  a.toString();
            }
            if(isneg == false && i != 0) {
                ans += "+" + a.toString();
            }
            i++;
            isneg = false;
        }
        return ans;
    }
    /**
     * @param str String that we check if he represent a number
     * @return boolean , if the String is a number return true , else return false
     */
    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
    public double areaUnderX(double x1, double x2) {

        double eps = 0.01;
        double area = 0;
        double m = ((x2-x1) * (f(x2) - f(x1)))/eps;
        double delta = (x2-x1)/m;
        double newDelta = x1;
        for(int i = 0; i < m; i++) {
            if(f(newDelta) < 0) {
                area += f(newDelta) * delta;
            }
            newDelta += delta;
        }
        return Math.abs(area);
    }
}

