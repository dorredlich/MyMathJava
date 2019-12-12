package Ex1Testing;

import static org.junit.Assert.fail;
import java.io.IOException;
import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Range;
import Ex1.function;
import Ex1.functions;

class Functions_GUITest {

    public void main(String[] a) throws Exception {
        functions data = FunctionsFactory();

        String file = "function_file.txt";
        String file2 = "function_file2.txt";
        try {
            data.saveToFile(file);
            Functions_GUI data2 = new Functions_GUI();
            data2.initFromFile(file);
            data.saveToFile(file2);
        }
        catch(Exception e) {e.printStackTrace();}

        String JSON_param_file = "GUI_params.txt";
        data.drawFunctions(JSON_param_file);
    }

    private functions _data=null;

    @BeforeEach
    void setUp() throws Exception {
        _data = FunctionsFactory();
    }

    @Test
    void testFunctions_GUI() throws Exception{
        try {
            _data.drawFunctions("GUI_params.txt");
        }
        catch(Exception e) {
            e.printStackTrace();
            //fail("file not found");
        }
    }

    @Test
    void testSaveToFile()throws Exception {
        functions data = FunctionsFactory();
        try {
            data.saveToFile("function_file2.txt"); // to write to the second file

        }
        catch(IOException e){
            fail("not found"); // fail if not found
            e.printStackTrace();
            System.err.println("file Not found");
        }
    }

    @Test
    void testInitFromFile() throws Exception{
        functions data = FunctionsFactory();
        try {
            data.initFromFile("function_file2.txt");
        }
        catch (IOException e) {
            e.printStackTrace();
            System.err.println("file Not found");
            fail("not found");
        }
    }

    @Test
    void testDrawFunctions() {
        try {
            _data.drawFunctions("GUI_params.txt");	//use the name of the file in the src folder.
        }
        catch(Exception e) {
            fail("could not read");
            e.printStackTrace();
        }
    }

    @Test
    void testDrawFunctionsIntRangeRangeInt() throws Exception{
        Functions_GUI f = new Functions_GUI();
        f.add(new Polynom("x^2"));
        f.add(new ComplexFunction("Plus", new Polynom("x+7"), new Polynom("x^4+x^3-7x")));
        Range rx = new Range(-5,10);
        Range ry = new Range(-10,10);
        f.drawFunctions(700,1000,rx,ry,100);
    }
    public static functions FunctionsFactory() throws Exception{
        functions ans = new Functions_GUI();
        String s1 = "3.1+2.4x^2-x^4";
        String s2 = "5+2x-3.3x+0.1x^5";
        String[] s3 = {"x+3", "x-2", "x-4"};
        Polynom p1 = new Polynom(s1);
        Polynom p2 = new Polynom(s2);
        Polynom p3 = new Polynom(s3[0]);
        ComplexFunction cf3 = new ComplexFunction(p3);
        for(int i=1;i<s3.length;i++) {
            cf3.mul(new Polynom(s3[i]));
        }

        ComplexFunction cf = new ComplexFunction("Plus", p1,p2);
        ComplexFunction cf4 = new ComplexFunction("Divid", new Polynom("x+1"),cf3);
        cf4.plus(new Monom("2"));
        ans.add(cf.copy());
        ans.add(cf4.copy());
        cf.div(p1);
        ans.add(cf.copy());
        function cf5 = cf4.initFromString(s1);
        function cf6 = cf4.initFromString(s2);
        ans.add(cf5.copy());
        ans.add(cf6.copy());
        Iterator<function> iter = ans.iterator();
        function f = iter.next();
        ComplexFunction max = new ComplexFunction(f);
        ComplexFunction min = new ComplexFunction(f);
        while(iter.hasNext()) {
            f = iter.next();
            max.max(f);
            min.min(f);
        }
        ans.add(max);
        ans.add(min);
        return ans;
    }
}
