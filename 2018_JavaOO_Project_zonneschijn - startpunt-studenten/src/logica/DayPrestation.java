package logica;

import algemeen.Helper;
import data.DataDefault;
import data.Data_dl2;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Olivier PC
 */
public class DayPrestation {

    private final double SAVEDCO2 = 0.7;
    private ArrayList<ProductionUnit> measurements;
    private double highestProduction;
    private double dayProduction;
    private double co2reduction;
    private LocalDate date;
    private LocalTime firstHour;
    private LocalTime lastHour;
    private LocalTime totalTime;
    private LocalTime timeHighestProduction;

    public DayPrestation() {
        this.date = LocalDate.now();
        this.measurements = new ArrayList<>();
    }

    public DayPrestation(String date) {
        this.date = LocalDate.parse(date);
        this.measurements = new ArrayList<>();
    }

    /**
     * Fills the ArrayList with measurements from throughout the day.
     */
    public void setMeasurements(String origin) {
        switch (origin) {
            case "dl1":
                for (String[] DEFAULT_DATA : DataDefault.DEFAULT_DATA) {
                    LocalTime time = LocalTime.parse(DEFAULT_DATA[0]);
                    double measurement = Double.parseDouble(DEFAULT_DATA[1]);
                    this.measurements.add(new ProductionUnit(time, measurement));
                }
                break;
            case "dl2": {
                Data_dl2.dataDeel2 = Data_dl2.parseDataDeel2();
                for (int i = 1; i < Data_dl2.dataDeel2.length; i++) {
                    if (Data_dl2.dataDeel2[i].length == 2 && Double.parseDouble(Data_dl2.dataDeel2[i][1]) > 0) {
                        LocalTime time = LocalTime.parse(Data_dl2.dataDeel2[i][0]);
                        double measurement = Double.parseDouble(Data_dl2.dataDeel2[i][1]);
                        this.measurements.add(new ProductionUnit(time, measurement));
                    }
                }
            }
            break;
        }
    }

    /**
     * Gets the hours at which production starts and ends from the ArrayList.
     */
    public void setFirstAndLastHour() {
        if (this.measurements != null) {
            this.firstHour = this.measurements.get(0).getTime();
            this.lastHour = this.measurements.get(this.measurements.size() - 1).getTime();
        } else {
            throw new NullPointerException("Meetwaarden nog niet ingelezen.");
        }
    }

    /**
     * Calculates how many hours the installation was active that day.
     */
    public void setTotalTime() {
        int hour = this.lastHour.getHour() - this.firstHour.getHour();
        int minute = this.lastHour.getMinute() - this.firstHour.getMinute();
        if (minute < 0) {
            minute += (-2 * minute);
            hour--;
        }
        this.totalTime = this.totalTime.of(hour, minute);
    }

    /**
     * Goes through all the measurements to calculate the total production of
     * that day.
     */
    public void setDayProduction() {
        for (ProductionUnit measurement : this.measurements) {
            this.dayProduction += measurement.getMeasurement();
        }
        this.dayProduction = Helper.round(this.dayProduction / 4, 3);
    }

    /**
     * Goes through all the measurements and figures out at what time the
     * installation was most effective.
     */
    public void setHighestProduction() {
        for (ProductionUnit measurement : this.measurements) {
            if (measurement.getMeasurement() > this.highestProduction) {
                this.timeHighestProduction = measurement.getTime();
                this.highestProduction = measurement.getMeasurement();
            }
        }
    }

    /**
     * Calculates the total amount of CO2 reduction for that day.
     */
    public void setCO2Reduction() {
        this.co2reduction = Helper.round(this.dayProduction * this.SAVEDCO2, 3);
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Dagproductie van " + this.date + " = " + this.dayProduction
                + " kW \n \n"
                + this.firstHour + " - " + this.lastHour + " ("
                + this.totalTime.getHour() + "." + this.totalTime.getMinute() / 6
                + " uren) \n"
                + "MAX om " + this.date + " " + this.timeHighestProduction
                + " => " + this.highestProduction + " kWp \n"
                + this.co2reduction + "kg CO2-reductie";
    }

}
