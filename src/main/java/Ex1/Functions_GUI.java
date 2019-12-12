package Ex1;

import Ex1.StdDraw;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import com.google.gson.Gson;

/**
 * Class's parameters of Function_GUI.
 * @author Dor Redlich
 */
public class Functions_GUI implements functions {

    private ArrayList<function> arr = new ArrayList<function>();

    /**init the file of sting by the help of the initFromstring function
     * add the functions we read to the list by ArrayList method
     * @param file - the file name
     * @throws IOException
     */
    @Override
    public void initFromFile(String file) throws IOException {
        ArrayList<function> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            function f1 = new Polynom("0");
            function f = new ComplexFunction(f1);
            while (str != null) {
                f = f.initFromString(str);
                list.add(f);
                str = br.readLine();
            }
            br.close();
            fr.close();
            System.out.println(list.toString());
        } catch (IOException ex) {
            System.out.print("Error reading file\n" + ex);
            System.exit(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * save the string to a file by over all the list
     * @param file - the file name
     * @throws IOException if their is problem with the file.
     */
    @Override
    public void saveToFile(String file) throws IOException{
        try {
            FileWriter fw = new FileWriter(file);
            PrintWriter outs = new PrintWriter(fw);
            Iterator<function> it = this.iterator();
            while (it.hasNext()) {
                outs.println(it.next().toString());
            }
            outs.close();
            fw.close();
        }catch(IOException ex) {
            System.out.print("Error writing file\n" + ex);
        }
    }

    @Override
    public int size() {
        return this.arr.size();
    }

    @Override
    public boolean isEmpty() {
        return this.arr.size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return  this.arr.contains(o);
    }

    @Override
    public Iterator<function> iterator() {
        return this.arr.iterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.arr.toArray(a);
    }

    @Override
    public boolean add(function function) {
        return this.arr.add(function);
    }

    @Override
    public boolean remove(Object o) {
        return this.arr.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.arr.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends function> c) {
        return this.arr.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.arr.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.arr.retainAll(c);
    }

    @Override
    public void clear() {
        this.arr.clear();
    }

    /**
     * drawing all the function that gets.
     * @param width - the width of the window - in pixels
     * @param height - the height of the window - in pixels
     * @param rx - the range of the horizontal axis
     * @param ry - the range of the vertical axis
     * @param resolution - the number of samples with in rx: the X_step = rx/resulution
     */
    @Override
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(rx.get_min(), rx.get_max());
        StdDraw.setYscale(ry.get_min(), ry.get_max());
        double[] x = new double[resolution + 1];
        double[] y = new double[resolution + 1];
        //vertical lines
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        for (int i = (int) rx.get_min(); i <= ry.get_max(); i = i + 1) {
            StdDraw.line(rx.get_min(), i, rx.get_max(), i);
        }
        // horizontal  lines
        for (double i = rx.get_min(); i <= rx.get_max(); i = i + 1) {
            StdDraw.line(i, ry.get_min(),i , ry.get_max());
        }
        // x axis
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(rx.get_min(), 0,rx.get_max() , 0);
        StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
        for (int i = (int)rx.get_min(); i <= rx.get_max(); i=i+1) {
            StdDraw.text(i, 0-0.3, Integer.toString((int)i));
        }
        // y axis
        StdDraw.line(0, ry.get_min(), 0, ry.get_max());
        for (double i = ry.get_min(); i <= ry.get_max(); i=i+1) {
            StdDraw.text(0-0.3, i, Integer.toString((int)i));
        }
        for(int j = 0; j < arr.size(); j++) {
            for (int i = 0; i <= resolution; i++) {
                x[i] = rx.get_min() + i * (rx.get_max() - rx.get_min()) / resolution;
                y[i] = this.arr.get(j).f(x[i]);
            }
            // random color
            int R = (int) (Math.random() * 256);
            int G = (int) (Math.random() * 256);
            int B = (int) (Math.random() * 256);
            Color randomColor = new Color(R, G, B);
            StdDraw.setPenColor(randomColor);
            // plot the approximation to the function
            for(int i = 0; i < resolution; i++){
                StdDraw.line(x[i],y[i],x[i+1],y[i+1]);
            }
        }
    }

    /**init the String from json file by open new ranges of x and y on the file.
     *
     * @param json_file the name file that we check.
     */
    @Override
    public void drawFunctions(String json_file) {
        Gson gson = new Gson();
        try {
            FileReader fr = new FileReader(json_file);
            GUI_params gp = gson.fromJson(fr, GUI_params.class);
            Range rx = new Range(gp.Range_X[0], gp.Range_X[1]);
            Range ry = new Range(gp.Range_Y[0], gp.Range_Y[1]);
            drawFunctions(gp.Width, gp.Height, rx, ry, gp.Resolution);
        } catch (FileNotFoundException e) {
            drawFunctions(1000, 600, new Range(-10, 10), new Range(-5, 15), 200);// the default if not succeed to read
            e.printStackTrace();
        }
    }
}


