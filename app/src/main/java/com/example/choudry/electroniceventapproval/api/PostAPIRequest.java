package com.example.choudry.electroniceventapproval.api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.choudry.electroniceventapproval.R;
import com.example.choudry.electroniceventapproval.app.AppController;

import java.util.Map;

/**
 * Created by maqsood on 08/03/2017.
 */

public class PostAPIRequest {


    private Activity activity;
    private ProgressDialog pDialog;
    private String url;
    private Map<String, String> param;
    public static final String TAG = PostAPIRequest.class.getSimpleName();
    private ResponseHandler responseHandler;

    public PostAPIRequest(Activity activity, String url, Map<String, String> map, ResponseHandler responseHandler) {
        this.activity = activity;
        this.responseHandler = responseHandler;
        this.url = url;
        this.param = map;
        pDialog = new ProgressDialog(activity, R.style.AppCompatAlertDialogStyle);
        pDialog.setMessage("Loading...");
        pDialog.show();
    }


    public void initiate() {


        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
                responseHandler.onResponse(response);
                pDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseHandler.onError(error.getMessage());
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.d(TAG, "" + error.getMessage() + "," + error.toString());
                pDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return param;
            }
        };
        sr.setRetryPolicy(new DefaultRetryPolicy(100000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(sr);
    }


    public interface ResponseHandler {

        void onResponse(String response);

        void onError(String error);
    }

}
