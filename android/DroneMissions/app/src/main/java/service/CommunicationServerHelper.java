package service;

/**
 * Created by gbrossault on 06/02/18.
 */


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import ila.fr.dronemissions.DAO.Mission;
import ila.fr.dronemissions.DAO.Report;


/**
 * response format :
 *
 * {
 *     'type': string,
 *     'data': object
 * }
 *
 * where 'type' in ['mission', 'report', 'gethistory', 'history']
 *
 */

public class CommunicationServerHelper {

    /**
     * throwMissionOrder|getReport|getOperationsHistory|getMissionInfo
     *  -> set request params
     *  -> use executeRequest with these params to get the result
     *  -> use extract response to get needed information
     */

    public final static String SERVER_URL = "192.168.137.45";

    public final static String URL_MISSION_ORDER = "mission/order";
    public final static String URL_MISSION_LIST = "mission/list";
    public final static String URL_REPORT = "report/";

    RequestQueue myRequestQueue;

    public CommunicationServerHelper(Context context) {
        myRequestQueue = Volley.newRequestQueue(context);
    }

    public void throwMissionOrder(Mission mission){
        try {
            JSONObject params = new JSONObject();
            params.put(JsonToolBox.JSON_TYPE, "mission_order");
            params.put(JsonToolBox.JSON_DATA, JsonToolBox.toJson(mission));
            executeRequestPost(this.URL_MISSION_ORDER, params);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getReport(int id) {
        executeRequestGet(this.URL_REPORT + Integer.toString(id));
    }

    public void getOperationsHistory(){
        /*Map<String, String> params = new HashMap<>();
        params.put("type", "gethistory");
        params.put("data", "");
        executeRequestGet("", params);*/
    }

    public void getMissionsInfo(){
        executeRequestGet(this.URL_MISSION_LIST);
    }

    public void executeRequestPost(String requestUrl, JSONObject params){
        String url = "http://"+SERVER_URL+":8080/"+requestUrl;
        System.out.println("Send : " + params.toString());
        JsonObjectRequest myJsonRequest = new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                extractResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //set action on error response
            }
        });

        myRequestQueue.add(myJsonRequest);
    }

    public void executeRequestGet(String requestUrl){
        String url = "http://"+SERVER_URL+":8080/"+requestUrl;
        JsonObjectRequest myJsonRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                extractResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //set action on error response
            }
        });
    }

    public void extractResponse(JSONObject jsonObject){
        System.out.println(jsonObject.toString());
        if(jsonObject.length() != 0) {
            try {
                String strType = jsonObject.getString("type");
                switch (strType) {
                    case "mission":
                        //-> no data in result
                        break;
                    case "report":
                        Report report = JsonToolBox.getReportFromJSON(jsonObject);
                        break;
                    case "gethistory":
                        break;
                    case "history":
                        List<Mission> missions = JsonToolBox.getListMissionFromJson(jsonObject);
                        break;
                    default:
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
