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
        /*
        try {
            String file = "C://Users/Olivier PC/Desktop/Legacy_Of_Jerome.txt"; // path goes here (slashes must be forward slahes).
            TextFile.write(file, "test"); // Add test to the file given as parameter.
            
            String allInFile = TextFile.read(file); // Reads everything as one string.
            String[] all = TextFile.readLines(file); // Reads every line individually and adds it to an array of strings.
            System.out.println(file);
        } catch (IOException io) {
            System.out.println("error");
        }
         */
        
        try {
            String bestand = "C:/Users/Olivier PC/Dropbox/2e semester/Java (OOP)/Project/2018_JavaOO_Project_zonneschijn - startpunt-studenten/data/Metingen 2017-12/Energie_en_vermogen_Dag_2017-12-10.csv";            
            String allInFile = TextFile.read(bestand); // Reads everything as one string.
            String[] splitFile = allInFile.split(";");
            for (int i = 0; i < splitFile.length; i++) {
                System.out.println(splitFile[i]);                
            }
        } catch (IOException io) {
            System.out.println("error");
        }
    }

}
