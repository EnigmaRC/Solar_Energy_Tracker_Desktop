package presentatie.JavaOO_dl1;

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
        DayProduction today = new DayProduction();
        today.setDefaultData();
        System.out.println(today);
    }
    
}
