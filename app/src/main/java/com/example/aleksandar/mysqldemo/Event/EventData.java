package com.example.aleksandar.mysqldemo.Event;

/**
 * Created by Aleksandar on 2/16/2017.
 */

public class EventData {

    int rezervacije_id;
    String event;
    String ime;
    String grad;
    String lokal;
    String naziv_benda;
    String datum;

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getNaziv_benda() {
        return naziv_benda;
    }

    public void setNaziv_benda(String naziv_benda) {
        this.naziv_benda = naziv_benda;
    }



    public int getRezervacije_id() {
        return rezervacije_id;
    }

    public void setRezervacije_id(int rezervacije_id) {
        this.rezervacije_id = rezervacije_id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getLokal() {
        return lokal;
    }

    public void setLokal(String lokal) {
        this.lokal = lokal;
    }
}
