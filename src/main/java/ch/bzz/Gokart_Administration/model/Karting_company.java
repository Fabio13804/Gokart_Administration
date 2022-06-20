package ch.bzz.Gokart_Administration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

/**
 * a Karting company in Gokart_Administration
 */

public class Karting_company {
    private int karting_companyID;

    @JsonIgnore
    private Gokart gokart;

    @JsonIgnore
    private Circuit circuit;

    @FormParam("name")
    private String name;

    @FormParam("restaurant")
    private boolean restaurant;


    public int getKarting_companyID() {
        return karting_companyID;
    }

    public void setKarting_companyID(int karting_companyID) {
        this.karting_companyID = karting_companyID;
    }

    public Gokart getGokart() {
        return gokart;
    }

    public void setGokart(Gokart gokart) {
        this.gokart = gokart;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(boolean restaurant) {
        this.restaurant = restaurant;
    }

}
