package presentatie.JavaOO_dl2;

import be.odisee.ikdoeict.TextFile;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.DayProduction;

/**
 *
 * @author Olivier PC
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DayProduction today = new DayProduction("2018-02-10");
        today.setCustomData();
        System.out.println(today);
        
    }
    
}
