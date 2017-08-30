package com.kesteli.filip.eurekas;

/**
 * Created by Valemate on 30.8.2017..
 */


public class Clan {

    private String ime;
    private String prezime;
    private String godine;
    private String tehnoIskustvo;
    private String iskustvo;
    private String obrazovanje;
    private String znanje;
    private String dani;
    private String tehnoDani;

    public Clan() {
    }

    public Clan(String ime, String prezime, String godine, String tehnoIskustvo, String iskustvo, String obrazovanje, String znanje, String dani, String tehnoDani) {
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

    public String getGodine() {
        return godine;
    }

    public void setGodine(String godine) {
        this.godine = godine;
    }

    public String getTehnoIskustvo() {
        return tehnoIskustvo;
    }

    public void setTehnoIskustvo(String tehnoIskustvo) {
        this.tehnoIskustvo = tehnoIskustvo;
    }

    public String getIskustvo() {
        return iskustvo;
    }

    public void setIskustvo(String iskustvo) {
        this.iskustvo = iskustvo;
    }

    public String getObrazovanje() {
        return obrazovanje;
    }

    public void setObrazovanje(String obrazovanje) {
        this.obrazovanje = obrazovanje;
    }

    public String getZnanje() {
        return znanje;
    }

    public void setZnanje(String znanje) {
        this.znanje = znanje;
    }

    public String getDani() {
        return dani;
    }

    public void setDani(String dani) {
        this.dani = dani;
    }

    public String getTehnoDani() {
        return tehnoDani;
    }

    public void setTehnoDani(String tehnoDani) {
        this.tehnoDani = tehnoDani;
    }
}



