package ila.fr.dronemissions.DAO;

import java.io.Serializable;

/**
 * Created by aminesoumiaa on 06/02/18.
 */

public class Point implements Serializable {
    private double longitude;
    private double latitude;
    private double altitude;
    private boolean picture;
    private String url;

    public Point(){
        this.picture = false;
        this.url = "";
    }

    public Point(double longitude, double latitude, double altitude, boolean picture){
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.picture = picture;
        this.url = "";
    }

    public Point(double longitude, double latitude, double altitude, String url){
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.picture = true;
        this.url = url;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public boolean isPicture() {
        return picture;
    }

    public String getUrl(){
        return this.url;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setAltitude(double altitude) { this.altitude = altitude; }

    public void setPicture(boolean picture) {
        this.picture = picture;
    }

    public void setUrl(String url){
        this.url = url;
    }
}