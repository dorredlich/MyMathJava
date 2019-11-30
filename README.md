# MyMathJava
myMath Project
by Dor Redlich
There are three Interfaces :
* Polynom_Able
* Function
* Cont_function
There are three main classes :
* Polynom
* Monom
* Monom_Comperator
The Polynom is an Object composed of Monoms in a shape a*x^b .
b MUST be a Natural Number.
a can be a Real Number.
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
There are two tests :
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
# MonomTests:
1. testMonomCopy().
2. testMonomToString().
3. testDerivative().
4. testAdd().
5. testMultiply().