package logica;

import algemeen.Helper;
import data.DataDefault;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Olivier PC
 */
public class DagPrestatie {

    private final double BESPAARDECO2 = 0.7;
    private ArrayList<ProductieEenheid> meetwaarden;
    private LocalDate datum;
    private double dagproductie;
    private LocalTime eersteUur;
    private LocalTime laatsteUur;
    private LocalTime totaleTijd;
    private LocalTime tijdstipHoogsteProductie;
    private double hoogsteProductie;

    public DagPrestatie() {
        this.datum = LocalDate.now();
        this.meetwaarden = new ArrayList<>();
        this.dagproductie = 0;
        this.hoogsteProductie = 0;
    }

    /**
     * Vult de vooraf gedeclareerde ArrayList met meetwaarden van doorheen de
     * dag.
     */
    public void setMeetwaarden() {
        for (String[] DEFAULT_DATA : DataDefault.DEFAULT_DATA) {
            LocalTime tijdstip = LocalTime.parse(DEFAULT_DATA[0]);
            double meetwaarde = Double.parseDouble(DEFAULT_DATA[1]);
            this.meetwaarden.add(new ProductieEenheid(tijdstip, meetwaarde));
        }
    }

    public void setEersteEnLaatsteUur() {
        if (this.meetwaarden != null) {
            this.eersteUur = this.meetwaarden.get(0).getTijdstip();
            this.laatsteUur = this.meetwaarden.get(this.meetwaarden.size() - 1).getTijdstip();
        } else {
            throw new NullPointerException("Meetwaarden nog niet ingelezen.");
        }
    }

    /**
     * Berekent hoeveel uren de installatie gepresteerd heeft.
     */
    public void berekentotaleTijd() {
        int uur = this.laatsteUur.getHour() - this.eersteUur.getHour();
        int minuut = this.laatsteUur.getMinute() - this.eersteUur.getMinute();
        if (minuut < 0) {
            minuut += (-2 * minuut);
            uur--;
        }
        this.totaleTijd = this.totaleTijd.of(uur, minuut);
    }

    /**
     * Loopt de meetwaarden af om de totale dagproductie te weten te komen.
     */
    public void berekenDagproductie() {
        for (ProductieEenheid meting : this.meetwaarden) {
            this.dagproductie += meting.getMeetwaarde();
        }
        this.dagproductie /= 4;
    }

    public void setHoogsteProductie() {
        for (ProductieEenheid meting : this.meetwaarden) {
            if (meting.getMeetwaarde() > this.hoogsteProductie) {
                this.tijdstipHoogsteProductie = meting.getTijdstip();
                this.hoogsteProductie = meting.getMeetwaarde();
            }
        }
    }

    public double berekenCO2Reductie() {
        return Helper.afronden(this.dagproductie * this.BESPAARDECO2, 3);
    }

    public double getDagproductie() {
        return Helper.afronden(this.dagproductie, 3);
    }

    @Override
    public String toString() {
        return "Dagproductie van " + this.datum + " = " + this.getDagproductie()
                + " kW \n \n"
                + this.eersteUur + " - " + this.laatsteUur + " ("
                + this.totaleTijd.getHour() + "." + this.totaleTijd.getMinute() / 6
                + " uren) \n"
                + "MAX om " + this.datum + " " + this.tijdstipHoogsteProductie
                + " => " + this.hoogsteProductie + " kWp \n"
                + this.berekenCO2Reductie() + "kg CO2-reductie";
    }

}
