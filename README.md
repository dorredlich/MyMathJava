# MyMathJava
Ex1 Project
by Dor Redlich
There are eight Interfaces :
* Polynom_Able.
* Function.
* Cont_function.
* Monom_Comperator
* functions.
* Operation.
* complex_function.
* Range

There are five main classes :
* Polynom
* Monom
The Polynom is an Object composed of Monoms in a shape a*x^b .
b MUST be a Natural Number.
a can be a Real Number.
* ComplexFunction.
* Function_GUI.

One help class:
* GUI_params.

# Monom
Constructors :
1. Monom(double a, int b) – creates new Monom where a is the coefficient and b is the power.
2. Monom(Monom other) – copy constructor creates new Monom with same coefficient and power as the Monom other.

# Methods
1. Void add(Monom m) – add the Monom m to current Monom.
2. Monom derivative() – derivative Monom and return new Monom.
3. double f(x) – return the result of f(x) in Monom.
4. double get_coefficient() – return the coefficient Monom.
5. int get_power() – return the power of Monom.
6. String toString() – return String a representation of this Monom in the shape ax^b.
7. Monom(String s) – turning the string to a monom.
8. Boolean isNumeric(String str) – checking if the string is a number.
9. public function initFromString(String s) - init the string to function.
10. public function copy() - copy the Monom to other function.
11. public boolean equals(Object obj) - check if object is kind of monom and check with other monom if is equal.

# Polynom
Constructors :
1. Polynom()– Creates default Polynom with no Monoms in it.
2. Polynom(String s) – Creates a Polynom according to a given String .
3. Polynom(Polynom_able p) - Creates a deep copy of the given Polynom_able (assuming Polynom able is from type Polynom).

# Methods:
1. void add(Monom m) – Adding a Monom to the Polynom.
2. void add(Polynom_able p) – Adding a given Polynom to "our" Polynom.
3. void subtract(Polynom_able p) – Subtracting a given Polynom to "our" Polynom.
4. void multiply(Polynom_able p) – multiplying between the two polynoms.
5. boolean Equals(Polynom p)- Checks if the two Polynoms are equals . isZero()- checking if the polynom has no Monoms.
6. boolean isZero - Is the Polynom equals '0'?
7. double area(double x0 ,double x1 ,eps) - calculating Riemann_integral
.the are above x axis.
8. double areaUnderX – calculating area below X axis .
9. double Root()- assuming there is at least one solution to the Polynom f(x)=0 ,returning the one value of x answering this requirement.
10. double f(double x) – returning the value of the polynom for a given 'x'.
11. Polynom_able derivative()- Returning Polynom_able/Polynom after derivative.
12. Polynom_able copy() - return a copy of the polynom
13. Iterator<Monom> iteretor() – return an Iterator of Monoms over this Polynom(has the method hasNext(), next() and remove()).
14. String toString()- Printing the Polynom.
15.Polynom(String s) – turning string to a polynom with ArrayList.
16. Double getCoefficientForSrting(String s) – check if the number before "x" and the Coefficient to an double number.
17. Int getPowerForSrting(String s) – check if there is a number power and turn the char to a int number.
18. Boolean isNumeric(String str) – checking if the string is a number.
19. public function initFromString(String s) - init the string to function.
20. public boolean equals(Object obj) - check if object is kind of polynom and check with other polynom if is equal.

# ComplexFunction
Constructors :
1. public ComplexFunction(String op, function left, function right) - create a ComplexFunction.
2. public ComplexFunction(function left) - create CompexFunction only with left function. 

# Methods:
1. public function initFromString(String s) - init the String to ComplexFunction.
2. public void plus(function f1) - push the Plus Operation.
3. public void mul(function f1) - push the Times Operation.
4. public void div(function f1) - push the Divid Operation.
5. public void max(function f1) - push the Max Operation.
6. public void min(function f1) - push the Min Operation.
7. public void comp(function f1) - push the Comp Operation.
8. public function left () - return the left function.
9. public function right () - return the right function.
10. public Operation getOp() - return the Operation.
11. public String toString() - print the ComplexFunction.
12. public function copy() - copy the the Complex Function to other complex function.
13. public double f(double x) - use the value of x to calculate the left function whit the right function dependent
                                of the Operation that we get.
14. public boolean equals(Object obj) - return true if the Object equals the this ComplexFunction by value of X.
                                        this can't do perfect compere for every point.                                
                                
#Fuction_GUI

#Methods:
1. public void initFromFile(String file) - read file and init the string from the to a functions.
2. public void saveToFile(String file) - save collections of functions to a file.
3. public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) - Draws all the functions in the collection in a GUI window 
4. public void drawFunctions(String json_file) - Draws all the functions in the collection in a GUI window using the given JSON file
                                
There are four tests :
* PolynomTests
* MonomTests

# PolynomTests:
1. testToStringInit().
2. PolynomAdd().
3. PolynomSub().
4. testPolynomCopy().
5. testPolynomArea().
6. testPolynomRoot().
7. testPolynomDerivative().
8. testPolynomMult().
9. testPolynomAddMonom().
10. testPolynomAddPolynom().
11. testPolynomValueAtX().
12. testPolyonomMultMonom()

# MonomTests:
1. testMonomCopy().
2. testMonomToString().
3. testDerivative().
4. testAdd().
5. testMultiply().

#ComplexFunctionTests:
1. testFComplexFunction().
2. testCopyComplexFunction().
3. testInitFromString().
4. testEqualsComplexFunction().
5. testPlusComplexFunction().
6. testMulComplexFunction().
7. testDivComplexFunction().
8. testMaxComplexFunction().
9. testMinComplexFunction().
10. testCompComplexFunction().

#Function_GHIT:
1. setUp().
2. testFunctions_GUI().
3. testSaveToFile().
4. testInitFromFile().
5. testDrawFunctions().
6. testDrawFunctionsIntRangeRangeInt().
7. FunctionsFactory().


