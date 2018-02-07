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

    RequestQueue myRequestQueue;

    public CommunicationServerHelper(Context context) {
        myRequestQueue = Volley.newRequestQueue(context);
    }

    public void throwMissionOrder(){
        Map<String, String> params = new HashMap<>();
        params.put("type", "mission");
        params.put("data", "");
        executeRequest(params);
    }

    public void getReport(){
        Map<String, String> params = new HashMap<>();
        params.put("type", "report");
        params.put("data", "");
        executeRequest(params);
    }

    public void getOperationsHistory(){
        Map<String, String> params = new HashMap<>();
        params.put("type", "gethistory");
        params.put("data", "");
        executeRequest(params);
    }

    public void getMissionsInfo(){
        Map<String, String> params = new HashMap<>();
        params.put("type", "history");
        params.put("data", "");
        executeRequest(params);
    }

    public void executeRequest(Map<String, String> params){
        JSONObject jsonObj = new JSONObject(params);

        String url = "http://";
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

    public void extractResponse(JSONObject jsonObject){
        try {
            String strType = jsonObject.getString("type");
            String strData = jsonObject.getString("data");
            switch(strType){
                case "mission":
                    //-> no data in result
                    break;
                case "report":
                    //-> get report element
                    break;
                case "gethistory":
                    //-> extract list of missions
                    break;
                case "history":
                    //-> get mission information
                    break;
                default:
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
