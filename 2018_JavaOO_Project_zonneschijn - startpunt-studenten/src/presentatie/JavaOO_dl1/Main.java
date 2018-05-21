package presentatie.JavaOO_dl1;

import logica.DagPrestatie;

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
        double test = 0;
        DagPrestatie vandaag = new DagPrestatie();
        vandaag.setMeetwaarden();
        vandaag.berekenDagproductie();
        vandaag.setEersteEnLaatsteUur();
        vandaag.berekentotaleTijd();
        vandaag.setHoogsteProductie();
        System.out.println(vandaag);
    }
}
