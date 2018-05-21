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
            String filePath = "C:/Users/Olivier PC/Dropbox/2e semester/Java (OOP)/Project/2018_JavaOO_Project_zonneschijn - startpunt-studenten/data/Metingen 2017-12/Energie_en_vermogen_Dag_2017-12-10.csv";
            String[] file = TextFile.readLines(filePath);
            for (int i = 0; i < file.length; i++) {
                file[i] = file[i].replaceAll(",", ".");
                
            }
            String[][] files = new String[file.length][];
            for (int i = 0; i < file.length; i++) {
                files[i] = file[i].split(";");
            }

            for (int i = 1; i < files.length; i++) {
                if (files[i].length == 2 && Double.parseDouble(files[i][1]) > 0) {
                    System.out.print(files[i][0] + " - ");
                    System.out.println(files[i][1]);
                }
            }

        } catch (IOException io) {
            System.out.println("error");
        }
    }

}
