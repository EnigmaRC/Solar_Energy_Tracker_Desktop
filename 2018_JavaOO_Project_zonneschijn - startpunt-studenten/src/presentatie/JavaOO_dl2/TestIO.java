package presentatie.JavaOO_dl2;

import be.odisee.ikdoeict.TextFile;
import java.io.IOException;

/**
 *
 * @author Olivier PC
 */
public class TestIO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        TestIO testIO = new TestIO();
        testIO.start();
    }

    private void start() {
        try {
            String bestand = "C://Users/Olivier PC/Desktop/Legacy_Of_Jerome.txt";
            //String bestand = "C://Users/Olivier PC/Dropbox/2e semester/Java (OOP)/Project/2018_JavaOO_Project_zonneschijn - startpunt-studenten/data/Metingen 2017-12/Energie_en_vermogen_Dag_2017-12-10";
            
            String[] alles = TextFile.readLines("C://Users/Olivier PC/Desktop/Legacy_Of_Jerome.txt");
            System.out.println(alles);
        } catch (IOException io) {
            System.out.println("error");
        }
    }
    
}
