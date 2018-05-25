package logica;

import java.util.Date;

/**
 *
 * @author Olivier PC
 */
public class Installation {

    private int systeemID;
    private String merk;
    private String type;
    private int serienummer;
    private String straat;
    private int huisnummer;
    private int postcode;
    private String gemeente;
    private String naam;
    private String voornaam;
    private Date installatiedatum;
    private double vermogen;

    /**
     * Constructor for creating an installation, based on the database design.
     *
     * @param systeemID
     * @param merk
     * @param type
     * @param serienummer
     * @param straat
     * @param huisnummer
     * @param postcode
     * @param gemeente
     * @param naam
     * @param voornaam
     * @param installatiedatum
     * @param vermogen
     */
    public Installation(int systeemID, String merk, String type, int serienummer, String straat, int huisnummer, int postcode, String gemeente, String naam, String voornaam, Date installatiedatum, double vermogen) {
        this.systeemID = systeemID;
        this.merk = merk;
        this.type = type;
        this.serienummer = serienummer;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
        this.gemeente = gemeente;
        this.naam = naam;
        this.voornaam = voornaam;
        this.installatiedatum = installatiedatum;
        this.vermogen = vermogen;
    }

    public int getSysteemID() {
        return systeemID;
    }

    public void setSysteemID(int systeemID) {
        this.systeemID = systeemID;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSerienummer() {
        return serienummer;
    }

    public void setSerienummer(int serienummer) {
        this.serienummer = serienummer;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public Date getInstallatiedatum() {
        return installatiedatum;
    }

    public void setInstallatiedatum(Date installatiedatum) {
        this.installatiedatum = installatiedatum;
    }

    public double getVermogen() {
        return vermogen;
    }

    public void setVermogen(double vermogen) {
        this.vermogen = vermogen;
    }

    public String toString() {
        return "-   Merk: " + this.merk
                + "\n-   Type: " + this.type
                + "\n-   Serienummer: " + this.serienummer
                + "\n-   Installatieadres: " + this.straat + " " + this.huisnummer
                + ", " + this.postcode + " " + this.gemeente
                + "\n-   Eigenaar: " + this.voornaam + " " + this.naam
                + "\n-   Installatiedatum: " + this.installatiedatum
                + "\n-   Installatievermogen: " + this.vermogen + " Wp";
    }
}
