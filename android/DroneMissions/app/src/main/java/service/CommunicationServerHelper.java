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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


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

    public final static String URL_MISSION_ORDER = "mission/order";
    public final static String URL_MISSION_LIST = "mission/list";
    public final static String URL_REPORT = "report/";

    RequestQueue myRequestQueue;

    public CommunicationServerHelper(Context context) {
        myRequestQueue = Volley.newRequestQueue(context);
    }

    public void throwMissionOrder(){
        Map<String, String> params = new HashMap<>();
        params.put("type", "mission");
        params.put("data", "");
        executeRequestPost(this.URL_MISSION_ORDER, params);
    }

    public void getReport(int id) {
        Map<String, String> params = new HashMap<>();
        params.put("type", "report");
        params.put("data", "");
        executeRequestPost(this.URL_REPORT + Integer.toString(id), params);
    }

    public void getOperationsHistory(){
        /*Map<String, String> params = new HashMap<>();
        params.put("type", "gethistory");
        params.put("data", "");
        executeRequestGet("", params);*/
    }

    public void getMissionsInfo(){
        Map<String, String> params = new HashMap<>();
        params.put("type", "history");
        params.put("data", "");
        executeRequestPost(this.URL_MISSION_LIST, params);
    }

    public void executeRequestPost(String requestUrl, Map<String, String> params){
        JSONObject jsonObj = new JSONObject(params);

        String url = "http://"+requestUrl;
        JsonObjectRequest myJsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>() {
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

    public void executeRequestGet(String requestUrl, Map<String, String> params){
        String url = "http://";
        JsonObjectRequest myJsonRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //set action on error response
            }
        });
    }

    public void extractResponse(JSONObject jsonObject){
        try {
            String strType = jsonObject.getString("type");
            String strData = jsonObject.getString("data");

            JSONObject data = new JSONObject(strData);
            JSONArray array = new JSONArray(data);
            switch (strType) {
                case "mission":
                    //-> no data in result
                    break;
                case "report":
                    for(int i = 0; i<array.length(); i++) {
                        JSONObject report = new JSONObject(array.getString(i));
                        //create a new report
                    }
                    break;
                case "gethistory":
                    for(int i = 0; i<array.length(); i++) {
                        JSONObject mission = new JSONObject(array.getString(i));
                        //create a new mission
                    }
                    break;
                case "history":
                    for(int i = 0; i<array.length(); i++) {
                        JSONObject mission = new JSONObject(array.getString(i));
                        //get information
                    }
                    break;
                default:
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
