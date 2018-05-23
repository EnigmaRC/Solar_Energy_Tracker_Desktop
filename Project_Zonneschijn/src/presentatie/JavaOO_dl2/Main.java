package presentatie.JavaOO_dl2;

import java.time.LocalDate;
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
        String date = "2018-2-10";
        DayProduction today = new DayProduction(date);
        today.setCustomData();
        System.out.println(today);
        
        LocalDate oldDate = today.getDate();
        LocalDate newDate = oldDate.plusDays(1);
        DayProduction tomorrow = new DayProduction(newDate.toString());
        tomorrow.setCustomData();
        System.out.println(tomorrow);
        
    }
    
}
