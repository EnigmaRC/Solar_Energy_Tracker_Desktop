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
public class DataLaag {

    private Sql2o sql2o;
    private String sql;

    public DataLaag() {
        this.sql2o = new Sql2o("jdbc:mysql://localhost:3306/oliviervermeulen", "root", "Azerty123");
    }

    public List<Installatie> setAantalInstallaties() {
        sql = "SELECT systeemID, merk, type, serienummer, installatiedatum, vermogen FROM installaties";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Installatie.class);
        }
    }

    public Installatie setInstallatieDetails(int systeemID) {
        sql = "SELECT systeemid, merk, type, serienummer, straat, huisnummer,"
                + "postcode, gemeente, naam, voornaam, installatiedatum, vermogen\n"
                + "FROM installaties INNER JOIN\n"
                + "eigenaars ON installaties.eigenaarID = eigenaars.eigenaarID\n"
                + "INNER JOIN\n"
                + "adressen ON installaties.adresID = adressen.adresID\n"
                + "WHERE installaties.systeemID = " + systeemID + "";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetchFirst(Installatie.class);
        }
    }

    public Date setDayProduction(int systeemID) {
        sql = "SELECT datum FROM metingen WHERE systeemID = " + systeemID + "";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetchFirst(Date.class);
        }
    }

    public List<Time> setTimes(int systeemID) {
        sql = "SELECT tijdstip FROM metingen WHERE systeemID = " + systeemID + "";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Time.class);
        }
    }

    public List<Double> setMeasurements(int systeemID) {
        sql = "SELECT meting FROM metingen WHERE systeemID = " + systeemID + "";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Double.class);
        }
    }

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
