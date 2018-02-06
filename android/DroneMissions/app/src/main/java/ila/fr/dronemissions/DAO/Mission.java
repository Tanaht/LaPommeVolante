package ila.fr.dronemissions.DAO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by aminesoumiaa on 06/02/18.
 */

public class Mission implements Serializable {

    private String title;
    private String status;
    private List<Point> trajectory;
    // TODO: Add mission report

    public Mission(String title, String status, List<Point> trajectory){
        this.title = title;
        this.status = status;
        this.trajectory = trajectory;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

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
