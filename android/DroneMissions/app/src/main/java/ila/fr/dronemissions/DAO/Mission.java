package ila.fr.dronemissions.DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aminesoumiaa on 06/02/18.
 */

public class Mission implements Serializable {

    private int id;
    private String title;
    private String date;
    private String status;
    private List<Point> trajectory;

    public Mission(String title, String status, List<Point> trajectory){
        this.title = title;
        this.status = status;
        this.trajectory = trajectory;
    }

    public Mission(int id, String title, String date, String status, List<Point> trajectory){
        this.id = id;
        this.title = title;
        this.date = date;
        this.status = status;
        this.trajectory = trajectory;
    }

    public int getId(){ return id; };

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id){ this.id = id; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Point> getTrajectory() {
        return trajectory;
    }

    public void setTrajectory(List<Point> trajectory) {
        this.trajectory = trajectory;
    }
}
