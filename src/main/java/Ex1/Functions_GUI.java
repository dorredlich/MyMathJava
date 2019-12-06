package Ex1;

import java.io.*;

public class Functions_GUI implements functions {

    public void initFromFile(String file) throws IOException {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String str;
            str = br.readLine();
            System.out.println(0 + ") " + str);
            for (int i = 1; str != null; i = i + 1) {
                str = br.readLine();
                if (str != null) {
                    System.out.println(i + ") " + str);
                }
            }
            br.close();
            fr.close();
        } catch (IOException ex) {
            System.out.print("Error reading file\n" + ex);
            System.exit(2);
        }
    }
 }

