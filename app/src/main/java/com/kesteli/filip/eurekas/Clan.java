package com.kesteli.filip.eurekas;

/**
 * Created by Valemate on 30.8.2017..
 */


public class Clan {

    private String ime;
    private String prezime;
    private int godine;
    private int tehnoIskustvo;
    private int iskustvo;
    private int obrazovanje;
    private int znanje;
    private int dani;
    private int tehnoDani;

    public Clan() {
    }

    public Clan(String ime, String prezime, int godine, int tehnoIskustvo, int iskustvo, int obrazovanje, int znanje, int dani, int tehnoDani) {
        this.ime = ime;
        this.prezime = prezime;
        this.godine = godine;
        this.tehnoIskustvo = tehnoIskustvo;
        this.iskustvo = iskustvo;
        this.obrazovanje = obrazovanje;
        this.znanje = znanje;
        this.dani = dani;
        this.tehnoDani = tehnoDani;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getGodine() {
        return godine;
    }

    public void setGodine(int godine) {
        this.godine = godine;
    }

    public int getTehnoIskustvo() {
        return tehnoIskustvo;
    }

    public void setTehnoIskustvo(int tehnoIskustvo) {
        this.tehnoIskustvo = tehnoIskustvo;
    }

    public int getIskustvo() {
        return iskustvo;
    }

    public void setIskustvo(int iskustvo) {
        this.iskustvo = iskustvo;
    }

    public int getObrazovanje() {
        return obrazovanje;
    }

    public void setObrazovanje(int obrazovanje) {
        this.obrazovanje = obrazovanje;
    }

    public int getZnanje() {
        return znanje;
    }

    public void setZnanje(int znanje) {
        this.znanje = znanje;
    }

    public int getDani() {
        return dani;
    }

    public void setDani(int dani) {
        this.dani = dani;
    }

    public int getTehnoDani() {
        return tehnoDani;
    }

    public void setTehnoDani(int tehnoDani) {
        this.tehnoDani = tehnoDani;
    }
}