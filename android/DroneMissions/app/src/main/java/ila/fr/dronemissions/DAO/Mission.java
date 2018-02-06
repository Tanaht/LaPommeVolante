package ila.fr.dronemissions.DAO;

import java.util.List;

/**
 * Created by aminesoumiaa on 06/02/18.
 */

public class Mission {

    private String title;
    private String type;
    private List<Point> trajectory;
    // TODO: Add mission report

    public Mission(String title, String type, List<Point> trajectory){
        this.title = title;
        this.type = type;
        this.trajectory = trajectory;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Point> getTrajectory() {
        return trajectory;
    }

    public void setTrajectory(List<Point> trajectory) {
        this.trajectory = trajectory;
    }
}
