package presentatie.JavaOO_dl1;

import logica.DayPrestation;

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
        DayPrestation vandaag = new DayPrestation();
        vandaag.setMeasurements();
        vandaag.setDayProduction();
        vandaag.setFirstAndLastHour();
        vandaag.setTotalTime();
        vandaag.setHighestProduction();
        System.out.println(vandaag);
    }
}
