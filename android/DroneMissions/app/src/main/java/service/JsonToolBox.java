package service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ila.fr.dronemissions.DAO.Mission;
import ila.fr.dronemissions.DAO.Point;

/**
 * Created by gbrossault on 13/03/18.
 */

public class JsonToolBox {

    public static final String JSON_TYPE = "type";
    public static final String JSON_DATA = "data";

    public static final String JSON_TITLE = "title";
    public static final String JSON_TRAJECTORY = "trajectory";
    public static final String JSON_LATITUDE = "lat";
    public static final String JSON_LONGITUDE = "lon";
    public static final String JSON_ALTITUDE = "alt";
    public static final String JSON_PHOTO = "photo";

    public static JSONObject toJson(Mission mission) {
        try {
            JSONObject jsonMission = new JSONObject();
            jsonMission.put(JSON_TITLE, mission.getTitle());
            JSONArray jsonPoints = new JSONArray();
            for (Point point : mission.getTrajectory()) {
                JSONObject jsonPoint = new JSONObject();
                jsonPoint.put(JSON_LATITUDE, point.getLatitude());
                jsonPoint.put(JSON_LONGITUDE, point.getLongitude());
                jsonPoint.put(JSON_ALTITUDE, point.getAltitude());
                jsonPoint.put(JSON_PHOTO, point.isPicture());
                jsonPoints.put(jsonPoint);
            }
            jsonMission.put(JSON_TRAJECTORY, jsonPoints);
            return jsonMission;
        } catch (JSONException e) {
            //
        }
        return null;
    }
}
