package ch.bzz.Gokart_Administration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Pattern;
import javax.ws.rs.FormParam;

/**
 * a Gokart in Gokart_Administration
 */

public class Gokart {

    @JsonIgnore
    private Karting_company karting_company;

    @FormParam("gokart_mumber")
    private String gokart_number;
       @Pattern(regexp = "[A-Za-z]{5}[1-9]{1,}[A-Za-z]{2}")

    @FormParam("fuel_typ")
    private String fuel_typ;

    @FormParam("ps")
    private int ps;

    @FormParam("max_speed")
    private int max_speed;

    @FormParam("weight")
    private double weight;

    @FormParam("color")
    private String color;

    @FormParam("brake_typ")
    private String brake_typ;

    public Karting_company getKarting_company() {
        return karting_company;
    }

    public void setKarting_company(Karting_company karting_company) {
        this.karting_company = karting_company;
    }

    public String getGokart_number() {
        return gokart_number;
    }

    public void setGokart_number(String gokart_number) {
        this.gokart_number = gokart_number;
    }

    public String getFuel_typ() {
        return fuel_typ;
    }

    public void setFuel_typ(String fuel_typ) {
        this.fuel_typ = fuel_typ;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getMax_speed() {
        return max_speed;
    }

    public void setMax_speed(int max_speed) {
        this.max_speed = max_speed;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrake_typ() {
        return brake_typ;
    }

    public void setBrake_typ(String brake_typ) {
        this.brake_typ = brake_typ;
    }
}
