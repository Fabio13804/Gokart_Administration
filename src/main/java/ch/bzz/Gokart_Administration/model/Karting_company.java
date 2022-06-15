package ch.bzz.Gokart_Administration.model;

import ch.bzz.Gokart_Administration.model.Gokart;

import java.util.List;

/**
 * a Karting company in Gokart_Administration
 */

public class Karting_company {
    private int karting_companyID;
    private List<Gokart> gokartList;
    private List<Circuit> circuitList;
    private String name;
    private boolean restaurant;


    public int getKarting_companyID() {
        return karting_companyID;
    }

    public void setKarting_companyID(int karting_companyID) {
        this.karting_companyID = karting_companyID;
    }

    public List<Gokart> getGokartList() {
        return gokartList;
    }

    public void setGokartList(List<Gokart> gokartList) {
        this.gokartList = gokartList;
    }

    public List<Circuit> getCircuitList() {
        return circuitList;
    }

    public void setCircuitList(List<Circuit> circuitList) {
        this.circuitList = circuitList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRestaurant() {
        return restaurant;
    }

    public void setRestaurant(boolean restaurant) {
        this.restaurant = restaurant;
    }

}
