package logica;

import java.time.LocalTime;

/**
 *
 * @author Olivier PC
 */
public class ProductionUnit {

    private LocalTime time;
    private double measurement;

    /**
     * Makes a new object which holds the time and the measurement taken
     * at that specific time.
     * @param time - time when the measurement took place.
     * @param measurement - value that was measured expressed in kW.
     */
    public ProductionUnit(LocalTime time, double measurement) {
        this.time = time;
        this.measurement = measurement;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getMeasurement() {
        return this.measurement;
    }

    public void setMeasurement(double measurement) {
        this.measurement = measurement;
    }

}
