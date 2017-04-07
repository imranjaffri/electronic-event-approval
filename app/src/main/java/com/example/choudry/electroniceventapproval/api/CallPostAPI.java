package com.example.choudry.electroniceventapproval.api;

import android.app.Activity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.util.Log;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.choudry.electroniceventapproval.R;
import com.example.choudry.electroniceventapproval.app.AppController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maqsood on 08/03/2017.
 */

public class CallPostAPI {


    private Activity activity;
    private ProgressDialog pDialog;
    private String url;
    private Map<String, String> param;
    public static final String TAG = CallPostAPI.class.getSimpleName();
    private ResponseHandler responseHandler;

    public CallPostAPI(Activity activity, String url, Map<String, String> map) {
        this.activity = activity;
        this.responseHandler = (ResponseHandler) activity;
        this.url = url;
        this.param = map;
        pDialog = new ProgressDialog(activity, R.style.AppCompatAlertDialogStyle);
        pDialog.setMessage("Loading...");
        pDialog.show();
    }


    public void Call() {


        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
                responseHandler.onResponse(response);
                pDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseHandler.onError(error.getMessage());
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.d(TAG, "" + error.getMessage() + "," + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return param;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(sr);
    }


    public interface ResponseHandler {

        void onResponse(String response);

        void onError(String error);
    }

}
