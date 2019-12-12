package Ex1;

/**
 * Class's parameters of ComplexFunction.
 * @author Dor Redlich
 */
public class ComplexFunction implements complex_function {
    private Operation op;
    private function left;
    private function right;

    public ComplexFunction(String op, function left, function right) {
        try {
            this.left = left;
            this.right = right;
            this.op = Operation.valueOf(op);
        }catch(Exception e) {
            this.op = Operation.Error;
            throw new RuntimeException("invalid complex function");
        }
    }

    public ComplexFunction(function left){
        this.left = left;
        this.right = null;
        this.op = Operation.None;
    }

    /**
     * init the String to ComplexFunction by chatching the operation and the left
     * doing a recursive if we see other ComplexFunction to the right and the left.
     * @param s we init
     * @return the function of ComplexFuncrion
     * @throws Exception by the throws of the init of Polynom.
     */
    @Override
    public function initFromString(String s) throws Exception {
        String temp = "";
        String oper = "";
        String left = "";
        int leftIndex = 0;
        int rightIndex = 0;
        int closeIndex = 0;
        int close = 0;
        int open = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                if (open == 0) {
                    oper = temp;
                    temp = "";
                    leftIndex = i + 1;//move the index of the left start function
                    close++;
                    open++;//count the number of '('
                } else
                    close++;// count the nubmer of ')'
            }
            else if (s.charAt(i) == ',') {
                if (close == 1) {
                    left = temp;
                    temp = "";
                    rightIndex = i + 1;//move the index of the right start function
                }
            } else if (s.charAt(i) == ')') {
                closeIndex = i;
                close--;
            }

            else {
                temp = temp + s.charAt(i);
            }
        }
        if (oper.equals("")) { // then it is only polynom.
            left = temp;
            Polynom l = new Polynom(left);
            return new ComplexFunction(l);
        }
        function ans = new ComplexFunction(oper, initFromString(s.substring(leftIndex, rightIndex - 1)),
                initFromString(s.substring(rightIndex, closeIndex)));
        return ans;
    }

    /**function that pushes the Operation Plus
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

    /**function that pushes the Operation Times
     *
     * @param f1 the complex_function which will be mul to this complex_function.
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

    /**function that pushes the Operation Divid
     *
     * @param f1 the complex_function which will be divid to this complex_function.
     */
    @Override
    public void div(function f1){
        if(this.op == null && this.right == null){
            this.right = f1;
            this.op = Operation.Divid;
        }
        this.left = new ComplexFunction(this.op.toString(), this.left, this.right);
        this.right = f1;
        this.op = Operation.Divid;
    }

    /**function that pushes the Operation Max
     *
     * @param f1 the complex_function which will be compere max with this complex_function.
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

    /**function that pushes the Operation Min
     *
     * @param f1 the complex_function which will be compere min to this complex_function.
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

    /**function that pushes the Operation Min
     *
     * @param f1 the complex_function which will be comp to this complex_function.
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
    @Override
    public function left () {
        return this.left;
    }

    /**
     * @return the right function in the Complex Function
     */
    @Override
    public function right () {
        return this.right;
    }

    /**
     * @return the Operation in the Complex Function
     */
    @Override
    public Operation getOp() {
        return this.op;
    }

    /**
     * printing the the Complex Function.
     */
    @Override
    public String toString() {
        if (this.right == null) { //to avoid from print null if there is
            return this.left.toString();
        }
        if (this.left == null) {
            return this.right.toString();
        }
        return this.op + "(" + this.left.toString() + "," + this.right.toString() + ")";
    }

    /**
     * copy the the Complex Function to other complex function.
     */
    @Override
    public function copy(){
        function f = new ComplexFunction(this.op.toString(), this.left, this.right);
        return f;
    }

    /**
     * @param x use the value of x to calculate the left function whit the right function dependent
     *         of the Operation that we get
     */
    @Override
    public double f(double x) {
        if (this.op.equals(Operation.valueOf("Plus")))
            return left.f(x) + right.f(x);
        else if (this.op.equals(Operation.valueOf("Times")))
            return left.f(x) * right.f(x);
        else if (this.op.equals(Operation.valueOf("Divid")))
            return left.f(x) / right.f(x);
        else if (this.op.equals(Operation.valueOf("Max")))
            return Math.max(left.f(x), right.f(x));
        else if (this.op.equals(Operation.valueOf("Min")))
            return Math.min(left.f(x), right.f(x));
        else
            return (this.left.f(this.right.f(x)));
    }

    /**
     * check if for every point between object to the this ComplexFunction they are equals.
     * this can't do perfect compere for every point.
     * @param obj that we compere with the ComplexFunction
     * @return true if they are equals by the same value.
     */
    @Override
    public boolean equals(Object obj) {
        String s = obj.toString();
        function fun = new ComplexFunction(new Polynom());
        try {
            fun = fun.initFromString(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        double[] yObj = new double[101];
        double[] yThis = new double[101];
        int x = -50; // the value of x for f(x).
        for (int i = 0; i < 101; i++) {
            yObj[i] = fun.f(x); // put the results in arrays  start from -50 to 50
            yThis[i] = this.f(x);
            if (yObj[i] != yThis[i]) {  // compare between the functions
                return false;
            }
            x++;
        }
        return true;
    }
}



