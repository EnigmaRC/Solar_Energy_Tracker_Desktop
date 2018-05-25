package logica;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

/**
 *
 * @author Olivier PC
 */
public class DataLayer {

    private Sql2o sql2o;
    private String sql;

    /**
     * Establishes the connection with the database.
     */
    public DataLayer() {
        this.sql2o = new Sql2o("jdbc:mysql://localhost:3306/oliviervermeulen", "root", "Azerty123");
    }

    /**
     * Retrieves information from all installations in the databases.
     *
     * @return A List with objects of Installation, containing its info.
     */
    public List<Installation> retrieveAllInstallations() {
        sql = "SELECT systeemID, merk, type, serienummer, installatiedatum, vermogen FROM installaties";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Installation.class);
        }
    }

    /**
     * Makes an Installation object depending on what ID was passed as a
     * parameter to be displayed on the Frame.
     *
     * @param systeemID
     * @return An Installaion with all parameters filled in.
     */
    public Installation retrieveInstallationDetails(int systeemID) {
        sql = "SELECT systeemid, merk, type, serienummer, straat, huisnummer,"
                + "postcode, gemeente, naam, voornaam, installatiedatum, vermogen\n"
                + "FROM installaties INNER JOIN\n"
                + "eigenaars ON installaties.eigenaarID = eigenaars.eigenaarID\n"
                + "INNER JOIN\n"
                + "adressen ON installaties.adresID = adressen.adresID\n"
                + "WHERE installaties.systeemID = " + systeemID + "";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetchFirst(Installation.class);
        }
    }

    /**
     * Makes a new DayProduction object based on the date of the given
     * Installation ID.
     *
     * @param systeemID
     * @return A DayProduction with the date set.
     */
    public Date setDayProduction(int systeemID) {
        sql = "SELECT datum FROM metingen WHERE systeemID = " + systeemID + "";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetchFirst(Date.class);
        }
    }

    /**
     * Gets all the times from a certain date, based on the Installation ID that
     * was passed.
     *
     * @param systeemID
     * @return A List<Time> with all times of a certain date.
     */
    public List<Time> setTimes(int systeemID) {
        sql = "SELECT tijdstip FROM metingen WHERE systeemID = " + systeemID + "";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Time.class);
        }
    }

    /**
     * Gets all the measurements from a certain date, based on the Installation
     * ID that was passed.
     *
     * @param systeemID
     * @return A List<Double> with all measurements of a certain date.
     */
    public List<Double> setMeasurements(int systeemID) {
        sql = "SELECT meting FROM metingen WHERE systeemID = " + systeemID + "";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Double.class);
        }
    }

    /**
     * Combines the times and measurements into a 2D array of Strings, suited
     * for the parse function in DayProduction.java
     *
     * @param times A List<Time> with all times of a certain date.
     * @param values A List<Double> with all measurements of a certain date.
     * @return A String[][] array with the times and values combined.
     */
    public String[][] combineTimesAndMeasurements(List<Time> times, List<Double> values) {
        String[][] combinedValues = new String[times.size()][2];
        LocalTime time;
        for (int i = 0; i < times.size(); i++) {
            combinedValues[i][0] = times.get(i).toLocalTime().toString();
            if (values.get(i) != null) {
                combinedValues[i][1] = values.get(i).toString();
            } else {
                combinedValues[i][1] = "0.0";
            }
        }
        return combinedValues;
    }

}
