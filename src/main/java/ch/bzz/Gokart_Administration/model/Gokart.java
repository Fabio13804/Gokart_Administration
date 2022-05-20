package ch.bzz.Gokart_Administration.model;

/**
 * a Gokart in Gokart_Administration
 */

public class Gokart {
    private String gokart_number;
    private String fuel_typ;
    private int ps;
    private int max_speed;
    private double weight;
    private String color;
    private String brake_typ;

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
