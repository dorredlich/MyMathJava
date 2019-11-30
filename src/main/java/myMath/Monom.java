package myMath;

import java.util.Iterator;

/**
 * This class represents a simple "com.polynom.Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative),
 * see: https://en.wikipedia.org/wiki/Monomial
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply.
 * @author Boaz
 *
 */
public class Monom implements function {

    public static final Monom ZERO = new Monom(0,0);
    public static final Monom MINUS1 = new Monom(-1,0);
    public static final double EPSILON = 0.0000001;
    private double _coefficient; //
    private int _power;

    /**
     * Constructor that gets a and b and turns it into a monom
     * @param a  the coefficient of the com.polynom.Monom
     * @param b  the power of the monom
     */

    public Monom(double a, int b){
        this._coefficient = a;
        this._power = b;
    }

    public Monom(String s) {
        s = s.replaceAll("X", "x");
        s = s.replaceAll("\\*","");
        s = s.replaceAll("\\^","");
        int size = s.length();
        //checks if there is 'x' in the string
        if(!s.contains("x")){
            if(isNumeric(s)){
                double temp = Double.parseDouble(s);
                this._coefficient = temp;
                this._power = 0;
                return;
            }
        }
        String temp = "";
        double coe;
        String pow = "";
        int power;
        String str = "x";
        for(int i = 0 ; i < size ; i++){
            if(s.charAt(i) == 'x'){
                temp = s.substring(0,i);
                if(temp.equals("")) {
                    coe = 1;
                    if(size-1 == i){
                        this._coefficient = coe;
                        this._power = 1;
                        return;
                    }
                    else {
                        pow = s.substring(i+1,size);
                        if(isNumeric(pow)) {
                            power = Integer.parseInt(pow);
                            this._coefficient = coe;
                            this._power = power;
                            return;
                        }
                    }
                }
                if(isNumeric(temp)) {
                    coe = Double.parseDouble(temp);
                    if(size-1 == i){
                        this._coefficient = coe;
                        this._power = 1;
                        return;
                    }
                    else {
                        pow = s.substring(i+1,size);
                        if(isNumeric(pow)) {
                            power = Integer.parseInt(pow);
                            this._coefficient = coe;
                            this._power = power;
                            return;
                        }
                    }
                }
            }
        }
    }
    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    /**
     * Copy constructor that takes other monom and copies its info to the new monom
     * @param ot (Other) monom that gets copied
     */

    public Monom(Monom ot) {
        this(ot.get_coefficient(), ot.get_power());
    }

    /**
     * @return the power value of the monom
     */


    public int get_power() {

        return _power;
    }
    /**
     *
     * @return the coefficient value of the monom
     */
    public double get_coefficient() {

        return _coefficient;
    }
    /**
     * set a to be the coefficient of the monom
     * @param a the value of the new coefficient
     */
    public void set_coefficient(double a){
        this._coefficient = a;
    }
    /**
     * set p to be the new power of the monom
     * @param p the value of the new power
     */

    public void set_power(int p) {
        this._power = p;
    }

    /**
     * @param x the point where we need the value of the monom at
     * @return the value of the monom at the point x
     */
    @Override
    public double f(double x) {
        double ans = 0;
        double p = this.get_power();
        ans = this.get_coefficient() * Math.pow(x, p);
        return ans;
    }

    public boolean isZero() {return this.get_coefficient() == 0;}
    /**
     * possible only if the 2 monoms have the same power
     * add this monom with m monom by adding the coefficients
     * @param m the monom we are adding to ours monom
     */
    public void add (Monom m) {
        if(m.get_power() != this.get_power())
            throw new RuntimeException("Error: can not add two monoms with different coefficients");
        if(m.get_coefficient() + this.get_coefficient() == 0)
            throw new NumberFormatException("the monom is zero");

        this._coefficient += m.get_coefficient();
    }
    /**
     * Mathematical function that returns the derivative of this com.polynom.Monom
     * @return derivatived monom
     */
    public Monom derivative() {
        if(this.get_power() == 0) {return getNewZeroMonom();}
        return new Monom(this.get_coefficient() * this.get_power(), this.get_power()-1);
    }

    /**
     * Mathematical function that multiplies 2 monoms
     * @param m the monom we multiply by
     * @return the result of the multiplication
     */
    public Monom multiply(Monom m) {
        return new Monom(m.get_coefficient() * _coefficient,m._power + this.get_power());
    }

    private static Monom getNewZeroMonom() {return new Monom(ZERO);}

    public String toString() {
        if(this.equals("x")) return "x";
        if(this.get_power() == 0) {
            return "" + this.get_coefficient();
        }
        if(this.get_power() == 1) {
            return "" + this.get_coefficient() + "x";
        }
        if(this.get_coefficient() == 1) {
            return "x^" + this.get_power();
        }
        else
            return "" + this.get_coefficient() + "x^" + this.get_power();
    }
}