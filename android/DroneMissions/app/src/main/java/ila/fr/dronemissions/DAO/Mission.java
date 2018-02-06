package ila.fr.dronemissions.DAO;

/**
 * Created by aminesoumiaa on 06/02/18.
 */

public class Mission {

    private String title;
    private String type;
    private Trajectory trajectory;


    // TODO: Add mission report

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
}
