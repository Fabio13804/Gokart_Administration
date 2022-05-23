package ch.bzz.Gokart_Administration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * a Circuit in Gokart_Administration
 */

public class Circuit {

    @JsonIgnore
    private Karting_company karting_company;

    private int circuitID;
    private String track_typ;
    private double distance;
    private String name;
    private int number_of_curves;
    private int number_of_straights;

    public Karting_company getKarting_company() {
        return karting_company;
    }

    public void setKarting_company(Karting_company karting_company) {
        this.karting_company = karting_company;
    }

    public int getCircuitID() {
        return circuitID;
    }

    public void setCircuitID(int circuitID) {
        this.circuitID = circuitID;
    }

    public String getTrack_typ() {
        return track_typ;
    }

    public void setTrack_typ(String track_typ) {
        this.track_typ = track_typ;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber_of_curves() {
        return number_of_curves;
    }

    public void setNumber_of_curves(int number_of_curves) {
        this.number_of_curves = number_of_curves;
    }

    public int getNumber_of_straights() {
        return number_of_straights;
    }

    public void setNumber_of_straights(int number_of_straights) {
        this.number_of_straights = number_of_straights;
    }
}
