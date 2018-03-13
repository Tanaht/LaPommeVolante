package ila.fr.dronemissions.DAO;

import java.util.List;

/**
 * Created by gbrossault on 13/03/18.
 */

public class Report {

    private int id;
    private int missionId;
    private boolean status;
    private String errorMessage;
    private List<Point> photos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMissionId() {
        return missionId;
    }

    public void setMissionId(int missionId) {
        this.missionId = missionId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<Point> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Point> photos) {
        this.photos = photos;
    }
}
