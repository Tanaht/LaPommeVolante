package ila.fr.dronemissions.DAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aminesoumiaa on 06/02/18.
 */

public class Trajectory {
    private List<Point> points = new ArrayList<Point>();

    public Trajectory(List<Point> points){
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
