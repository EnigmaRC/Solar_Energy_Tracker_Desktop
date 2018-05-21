package logica;

import java.time.LocalTime;

/**
 *
 * @author Olivier PC
 */
public class ProductieEenheid {

    private LocalTime tijdstip;
    
    private double meetwaarde;

    /**
     * Maakt een nieuwe meting aan waarvan het tijdstip en de meetwaarde
     * wordt bijgehouden.
     * @param tijdstip - tijdstip waarop de meting is gebeurd.
     * @param meetwaarde - de waarde die gemeten werd, uitgedrukt in kW.
     */
    public ProductieEenheid(LocalTime tijdstip, double meetwaarde) {
        this.tijdstip = tijdstip;
        this.meetwaarde = meetwaarde;
    }

    public LocalTime getTijdstip() {
        return this.tijdstip;
    }

    public void setTijdstip(LocalTime tijdstip) {
        this.tijdstip = tijdstip;
    }

    public double getMeetwaarde() {
        return this.meetwaarde;
    }

    public void setMeetwaarde(double meetwaarde) {
        this.meetwaarde = meetwaarde;
    }

}
