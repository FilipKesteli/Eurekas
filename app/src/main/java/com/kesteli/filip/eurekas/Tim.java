package com.kesteli.filip.eurekas;

/**
 * Created by Valemate on 31.8.2017..
 */

public class Tim {

    private String imeTima;
    private String korisnici;
    private int net;
    private int vjera;
    private int ulog;
    private int realno;
    private String tkoUlog;
    private int motivacija;
    private String podrucja;
    private int sansaUlog;
    private int brojClanova;

    public Tim() {
    }

    public Tim(String imeTima, String korisnici, int net, int vjera, int ulog, int realno, String tkoUlog, int motivacija, String podrucja, int sansaUlog, int brojClanova) {
        this.imeTima = imeTima;
        this.korisnici = korisnici;
        this.net = net;
        this.vjera = vjera;
        this.ulog = ulog;
        this.realno = realno;
        this.tkoUlog = tkoUlog;
        this.motivacija = motivacija;
        this.podrucja = podrucja;
        this.sansaUlog = sansaUlog;
        this.brojClanova = brojClanova;
    }

    public String getImeTima() {
        return imeTima;
    }

    public void setImeTima(String imeTima) {
        this.imeTima = imeTima;
    }

    public String getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(String korisnici) {
        this.korisnici = korisnici;
    }

    public int getNet() {
        return net;
    }

    public void setNet(int net) {
        this.net = net;
    }

    public int getVjera() {
        return vjera;
    }

    public void setVjera(int vjera) {
        this.vjera = vjera;
    }

    public int getUlog() {
        return ulog;
    }

    public void setUlog(int ulog) {
        this.ulog = ulog;
    }

    public int getRealno() {
        return realno;
    }

    public void setRealno(int realno) {
        this.realno = realno;
    }

    public String getTkoUlog() {
        return tkoUlog;
    }

    public void setTkoUlog(String tkoUlog) {
        this.tkoUlog = tkoUlog;
    }

    public int getMotivacija() {
        return motivacija;
    }

    public void setMotivacija(int motivacija) {
        this.motivacija = motivacija;
    }

    public String getPodrucja() {
        return podrucja;
    }

    public void setPodrucja(String podrucja) {
        this.podrucja = podrucja;
    }

    public int getSansaUlog() {
        return sansaUlog;
    }

    public void setSansaUlog(int sansaUlog) {
        this.sansaUlog = sansaUlog;
    }

    public int getBrojClanova() {
        return brojClanova;
    }

    public void setBrojClanova(int brojClanova) {
        this.brojClanova = brojClanova;
    }
}