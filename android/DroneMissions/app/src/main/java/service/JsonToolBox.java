package service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ila.fr.dronemissions.DAO.Mission;
import ila.fr.dronemissions.DAO.Point;
import ila.fr.dronemissions.DAO.Report;

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

    public static final String JSON_ID = "id";
    public static final String JSON_MISSION = "mission";
    public static final String JSON_STATUS = "status";
    public static final String JSON_ERROR = "error";
    public static final String JSON_PHOTOS = "photos";
    public static final String JSON_URL = "url";

    public static final String JSON_DATE = "date";

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
            e.printStackTrace();
        }
        return null;
    }

    public static Report getReportFromJSON(JSONObject jsonObject){
        try {
            Report report = new Report();
            JSONObject data = jsonObject.getJSONObject(JSON_DATA);
            int id = data.getInt(JSON_ID);
            int mission = data.getInt(JSON_MISSION);
            boolean status = data.getBoolean(JSON_STATUS);
            String error = data.getString(JSON_ERROR);
            report.setId(id);
            report.setMissionId(mission);
            report.setStatus(status);
            report.setErrorMessage(error);
            List<Point> photos = new ArrayList<>();
            JSONArray jsonPhotos = data.getJSONArray(JSON_PHOTOS);
            for(int i = 0; i<jsonPhotos.length(); i++){
                JSONObject jsonPhoto = jsonPhotos.getJSONObject(i);
                Double lat = jsonPhoto.getDouble(JSON_LATITUDE);
                Double lon = jsonPhoto.getDouble(JSON_LONGITUDE);
                Double alt = jsonPhoto.getDouble(JSON_ALTITUDE);
                String url = jsonPhoto.getString(JSON_URL);
                Point point = new Point(lon, lat, alt, url);
                photos.add(point);
            }
            return report;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Mission> getListMissionFromJson(JSONObject jsonObject){
        try {
            List<Mission> missions = new ArrayList<>();
            JSONArray datas = jsonObject.getJSONArray(JSON_DATA);
            for(int i = 0; i<datas.length(); i++){
                JSONObject jsonMission = datas.getJSONObject(i);
                int id = jsonMission.getInt(JSON_ID);
                String date = jsonMission.getString(JSON_DATE);
                String title = jsonMission.getString(JSON_TITLE);
                List<Point> points = new ArrayList<>();
                JSONArray trajectory = jsonMission.getJSONArray(JSON_TRAJECTORY);
                for(int j = 0; j<trajectory.length(); j++){
                    Point point = new Point();
                    JSONObject jsonPoint = trajectory.getJSONObject(i);
                    Double lat = jsonPoint.getDouble(JSON_LATITUDE);
                    Double lon = jsonPoint.getDouble(JSON_LONGITUDE);
                    Double alt = jsonPoint.getDouble(JSON_ALTITUDE);
                    Boolean url = jsonPoint.getBoolean(JSON_PHOTO);
                    points.add(point);
                }
                String status = jsonMission.getString(JSON_STATUS);
                Mission mission = new Mission(id, title, date, status, points);
                missions.add(mission);
            }
            return missions;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
