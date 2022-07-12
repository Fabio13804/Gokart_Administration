package ch.bzz.Gokart_Administration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.*;
import javax.ws.rs.FormParam;

/**
 * a Circuit in Gokart_Administration
 */

public class Circuit {

    @JsonIgnore
    private Karting_company karting_company;

    @FormParam("circuitID")
    @NotNull
    private int circuitID;

    @FormParam("track_typ")
    @NotEmpty
    @Size(min = 4, max = 50)
    @Pattern(regexp = "^(indoor|outdoor|Indoor|Outdoor)$")
    private String track_typ;

    @FormParam("distance")
    @NotNull
    private double distance;

    @FormParam("name")
    @NotEmpty
    private String name;

    @FormParam("number_of_curves")
    @NotNull
    @Min(value = 4)
    @Max(value = 50)
    private int number_of_curves;

    @FormParam("number_of_straights")
    @NotNull
    @Min(value = 2)
    @Max(value = 50)
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
