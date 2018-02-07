package ila.fr.dronemissions.DAO;

import java.io.Serializable;

/**
 * Created by aminesoumiaa on 06/02/18.
 */

public class Point implements Serializable {
    private double longitude;
    private double latitude;
    private boolean picture;

    public Point(double longitude, double latitude,boolean picture){
        this.longitude = longitude;
        this.latitude = latitude;
        this.picture = picture;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public boolean isPicture() {
        return picture;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setPicture(boolean picture) {
        this.picture = picture;
    }
}
