package Ex1Testing;

import org.junit.jupiter.api.Test;
import Ex1.Functions_GUI;

class Functions_GUITest {

   @Test
   void testInitFromFile() throws Exception {
       Functions_GUI functions_gui = new Functions_GUI();
       String filePath = "C:\\MyFile.txt";
       functions_gui.initFromFile(filePath);
    }
}