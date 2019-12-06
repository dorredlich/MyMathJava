package Ex1;


import java.util.ArrayList;
import java.util.Iterator;


public class ComplexFunction implements complex_function {
    private Operation op;
    private function left;
    private function right;



    public ComplexFunction(String op, function left, function right) {
        this.op = Operation.valueOf(op);
        this.left = left;
        this.right = right;
    }

    public ComplexFunction(function left){
        this.left = left;

    }


    //@Override
    public function initFromString(String s) throws Exception{
        function ans = null;
        String st1 = null;
        function f = null;
        String s1 = "Plus(";
        String s2 = "Times(";
        String s3 = "Divid(";
        String s4 = "Max(";
        String s5 = "Min(";
        String s6 = "Comp(";

        if (s.indexOf(s1) >= 0  && s.endsWith(")")) {
            st1 = s.substring(s.indexOf(s1) + s1.length(), s.length() - 1);
            f = initFromString(st1);
            ans = new ComplexFunction(f);
        }

        return ans;
    }

    /**function that fushes the Operation Plus
     *
     * @param f1 the complex_function which will be added to this complex_function.
     */
    @Override
    public void plus(function f1) {
        if(this.op == null && this.right == null){//this 'if' check if we init the complex function in operation and
            this.right = f1;                      //right function to null and we have only left function then i
            this.op = Operation.Plus;             //init the right and the operation.
        }
        this.left = new ComplexFunction(this.op.toString(), this.left, this.right);
        this.right = f1;
        this.op = Operation.Plus;
    }

    /**function that fushes the Operation Times
     *
     * @param f1 the complex_function which will be added to this complex_function.
     */
    @Override
    public void mul(function f1) {
        if(this.op == null && this.right == null){
            this.right = f1;
            this.op = Operation.Times;
        }
        this.left = new ComplexFunction(this.op.toString(), this.left, this.right);
        this.right = f1;
        this.op = Operation.Times;
    }
    @Override

    /**function that fushes the Operation Divid
     *
     * @param f1 the complex_function which will be added to this complex_function.
     */
    public void div(function f1){
        if(this.op == null && this.right == null){
            this.right = f1;
            this.op = Operation.Divid;
        }
        this.left = new ComplexFunction(this.op.toString(), this.left, this.right);
        this.right = f1;
        this.op = Operation.Divid;
    }

    /**function that fushes the Operation Max
     *
     * @param f1 the complex_function which will be added to this complex_function.
     */
    @Override
    public void max(function f1){
        if(this.op == null && this.right == null){
            this.right = f1;
            this.op = Operation.Max;
        }
        this.left = new ComplexFunction(this.op.toString(), this.left, this.right);
        this.right = f1;
        this.op = Operation.Max;
    }

    /**function that fushes the Operation Min
     *
     * @param f1 the complex_function which will be added to this complex_function.
     */
    @Override
    public void min(function f1){
        if(this.op == null && this.right == null){
            this.right = f1;
            this.op = Operation.Min;
        }
        this.left = new ComplexFunction(this.op.toString(), this.left, this.right);
        this.right = f1;
        this.op = Operation.Min;
    }

    /**function that fushes the Operation Min
     *
     * @param f1 the complex_function which will be added to this complex_function.
     */
    @Override
    public void comp(function f1){
        if(this.op == null && this.right == null){
            this.right = f1;
            this.op = Operation.Comp;
        }
        this.left = new ComplexFunction(this.op.toString(), this.left, this.right);
        this.right = f1;
        this.op = Operation.Comp;
    }


    /**
     * @return the left function in the Complex Function
     */
    public function left () {
        return this.left;
    }

    /**
     * @return the right function in the Complex Function
     */
    public function right () {
        return this.right;
    }

    /**
     * @return the Operation in the Complex Function
     */
    public Operation getOp() {
        return this.op;
    }

    /**
     * printing the the Complex Function.
     */
    public String toString(){
        if(this.left instanceof Polynom && this.right == null && this.op == null)
            return this.left.toString();//check if the left function is kind of Polyom and there isn't left function and Operantion.
        return this.op + "(" + this.left.toString() + "," + this.right.toString() + ")";

    }

    /**
     * copy the the Complex Function to other complex function.
     */
    public function copy(){
        function f = new ComplexFunction(this.op.toString(), this.left, this.right);
        return f;
    }

    /**
     * @param x use the value of x to calculate the left function whit the right function dependent
     *         of the Operation that we get
     */
    @Override
    public double f(double x){
            if (this.op.equals(Operation.valueOf("Plus")))
            return  left.f(x)+right.f(x);
           else if (this.op.equals(Operation.valueOf("Times")))
                return  left.f(x)*right.f(x);
            else if (this.op.equals(Operation.valueOf("Divid")))
                return  left.f(x)/right.f(x);
            else if (this.op.equals(Operation.valueOf("Max")))
                return  Math.max(left.f(x),right.f(x));
            else if (this.op.equals(Operation.valueOf("Min")))
                return  Math.min(left.f(x),right.f(x));
            else
                return  (this.left.f(this.right.f(x)));
        }


//
//    public boolean equals(Object obj){
//        if(obj != null && obj instanceof ComplexFunction){
//            ComplexFunction cf = (ComplexFunction)obj;
//            if(ComplexFunction.this.f() == cf.f())
//        }
//    }

}



