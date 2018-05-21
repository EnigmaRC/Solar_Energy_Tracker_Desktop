package data;

import be.odisee.ikdoeict.TextFile;
import java.io.IOException;

/**
 *
 * @author Olivier PC
 */
public class Data_dl2 {

    public static String[][] dataDeel2;

    public static String[][] setDataDeel2() {
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
            /*
            for (int i = 1; i < files.length; i++) {
                if (files[i].length == 2 && Double.parseDouble(files[i][1]) > 0) {
                    System.out.print(files[i][0] + " - ");
                    System.out.println(files[i][1]);
                }
            }
             */

        } catch (IOException io) {
            System.out.println("error");
        }
        return null;
    }
}
