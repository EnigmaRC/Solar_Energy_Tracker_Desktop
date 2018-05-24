package logica;

import algemeen.Helper;
import be.odisee.ikdoeict.TextFile;
import data.DataDefault;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 *
 * @author Olivier PC
 */
public class DayProduction {

    private final double CO2REDUCTION = 0.7;
    private LocalDate date;
    private ArrayList<ProductionUnit> measurements;
    private LocalTime firstHour;
    private LocalTime lastHour;
    private double dailyTotal;
    private double dailyMaximum = 0;
    private double reducedCO2;
    private LocalTime dailyTimeOfMaximum;
    private LocalTime totalAmountOfHours;

    public DayProduction() {
        this.date = LocalDate.now();
        this.measurements = new ArrayList<>();
    }

    public DayProduction(String date) {
        String[] dateSplit = date.split("-");
        String year = dateSplit[0];
        String month = dateSplit[1];
        String day = (dateSplit[2]);
        // Allows for input format to be YYYY-M-D in case of single digit months and/or days.
        if (Integer.parseInt(month) <= 9 && Integer.parseInt(month) >= 0) {
            month = "0" + Integer.parseInt(month);
        }
        if (Integer.parseInt(day) <= 9 && Integer.parseInt(day) >= 1) {
            day = "0" + Integer.parseInt(day);
        }
        String correctedDate = year + "-" + month + "-" + day;
        try {
            this.date = LocalDate.parse(correctedDate);
        } catch (DateTimeParseException e) {
            System.out.println("error");
        }
        this.measurements = new ArrayList();
    }

    public DayProduction(Date date) {
        this.date = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.measurements = new ArrayList<>();
    }

    public void setDefaultData() {
        this.parseDefaultData();
        this.calculateStringOutput();
    }

    public void setCustomData() {
        this.parseCustomData(this.fillCustomData());
        this.calculateStringOutput();
    }

    public void calculateStringOutput() {
        this.setFirstHour();
        this.setLastHour();
        this.setDailyTotal();
        this.setDailyMaximum();
        this.setCO2Reduction();
        this.setTotalAmountOfHours();
    }

    /**
     * Fills the measurements array with data from data/DataDefault.java.
     */
    private void parseDefaultData() {
        for (String[] DEFAULT_DATA : DataDefault.DEFAULT_DATA) {
            LocalTime time = LocalTime.parse(DEFAULT_DATA[0]);
            double measurement = Double.parseDouble(DEFAULT_DATA[1]);
            this.measurements.add(new ProductionUnit(time, measurement));
        }
    }

    /**
     * Fills the measurements array with data from a custom date.
     *
     * @param arr String[][] returned from fillCustomData()
     */
    public void parseCustomData(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length >= 2 && Double.parseDouble(arr[i][1]) > 0) {
                String[] hoursAndMinutes = arr[i][0].split(":"); // splits the string into hours and minutes.
                int hours = Integer.parseInt(hoursAndMinutes[0]);
                int minutes = Integer.parseInt(hoursAndMinutes[1]);
                LocalTime time = LocalTime.of(hours, minutes);
                double measurement = Double.parseDouble(arr[i][1]);
                this.measurements.add(new ProductionUnit(time, measurement));
            }
        }
    }

    /**
     * Fills a 2D array with data from a custom date, making use of the
     * CodeLibrary library to find the required CSV-file.
     *
     * @return String[][] filled with times and their measurement.
     */
    public String[][] fillCustomData() {
        String filePath = this.date.toString();
        try {
            String[] file = TextFile.readLines("data/Metingen " + filePath.substring(0, 7) + "/Energie_en_vermogen_Dag_" + filePath + ".csv");
            for (int i = 0; i < file.length; i++) {
                file[i] = file[i].replaceAll(",", ".");
            }
            String[][] files = new String[file.length - 1][];
            for (int i = 1; i < file.length; i++) {
                files[i - 1] = file[i].split(";");
            }
            return files;
        } catch (IOException ex) {
            Logger.getLogger(DayProduction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void setDailyTotal() {
        for (ProductionUnit unit : this.measurements) {
            dailyTotal += unit.getMeasurement();
        }
        dailyTotal = Helper.round(dailyTotal / 4, 3);
    }

    private void setDailyMaximum() {
        for (ProductionUnit unit : this.measurements) {
            if (unit.getMeasurement() > this.dailyMaximum) {
                this.dailyMaximum = Helper.round(unit.getMeasurement(), 3);
                this.dailyTimeOfMaximum = unit.getTime();
            }
        }
    }

    public void setTotalAmountOfHours() {
        int hours = this.lastHour.getHour() - this.firstHour.getHour();
        int minutes = this.lastHour.getMinute() - this.firstHour.getMinute();
        if (minutes < 0) {
            minutes += 60;
            hours--;
        }
        this.totalAmountOfHours = LocalTime.of(hours, minutes);
    }

    private void setCO2Reduction() {
        this.reducedCO2 = Helper.round(this.dailyTotal * this.CO2REDUCTION, 3);
    }

    /**
     * Finds the first time of production.
     */
    private void setFirstHour() {
        for (int i = 0; i < this.measurements.size(); i++) {
            if (this.measurements.get(i).getMeasurement() > 0) {
                firstHour = this.measurements.get(i).getTime();
                return;
            }
        }
    }

    /**
     * Finds the last time of production.
     */
    private void setLastHour() {
        for (int i = 0; i < this.measurements.size(); i++) {
            if (this.measurements.get(i).getMeasurement() > 0) {
                lastHour = this.measurements.get(i).getTime();
            }
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        String minutes = "";
        switch (this.totalAmountOfHours.getMinute()) {
            case 15:
                minutes = ".25";
                break;
            case 30:
                minutes = ".5";
                break;
            case 45:
                minutes = ".75";
                break;
        }
        String output = "Dagproductie van " + this.date + " = "
                + this.dailyTotal + " kW \n \n" + this.firstHour + " - "
                + this.lastHour + " (" + this.totalAmountOfHours.getHour()
                + minutes + " uren)"
                + "\n" + "MAX om " + this.date + " " + this.dailyTimeOfMaximum
                + " => " + this.dailyMaximum + " kWp \n" + this.reducedCO2
                + "kg CO2-reductie";

        return output;
    }

}
