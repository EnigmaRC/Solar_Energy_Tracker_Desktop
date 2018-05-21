package data;

import be.odisee.ikdoeict.TextFile;
import java.io.IOException;

/**
 *
 * @author Olivier PC
 */
public class Data_dl2 {

    public static String[][] dataDeel2;

    /**
     * Extracts the data from CSV file "Energie_en_vermogen_Dag_2017-12-10.csv"
     * and parses it into a 2D array.
     * @return 
     */
    public static String[][] parseDataDeel2() {
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
            return files;

        } catch (IOException io) {
            System.out.println("error");
        }
        return null;
    }
}
