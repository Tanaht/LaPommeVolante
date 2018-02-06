package ila.fr.dronemissions.DAO;

/**
 * Created by aminesoumiaa on 06/02/18.
 */

public class Point {
    private int longitude;
    private int latitude;
    private boolean picture;

    public Point(int longitude, int latitude,boolean picture){
        this.longitude = longitude;
        this.latitude = latitude;
        this.picture = picture;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public boolean isPicture() {
        return picture;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public void setPicture(boolean picture) {
        this.picture = picture;
    }
}
