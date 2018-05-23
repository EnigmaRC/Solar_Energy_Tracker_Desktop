package logica;

import java.time.LocalTime;

/**
 *
 * @author Olivier PC
 */
public class ProductionUnit {
    
    private LocalTime time;
    private double measurement;

    public ProductionUnit(LocalTime time, double measurement) {
        this.time = time;
        this.measurement = measurement;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getMeasurement() {
        return measurement;
    }

    public void setMeasurement(double measurement) {
        this.measurement = measurement;
    }
    
    
}
