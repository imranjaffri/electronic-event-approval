package com.example.choudry.electroniceventapproval.api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.choudry.electroniceventapproval.R;
import com.example.choudry.electroniceventapproval.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by maqsood on 15/04/2017.
 */

public class GetAPIRequest {

    private ProgressDialog pDialog;
    private String url;
    public static final String TAG = PostAPIRequest.class.getSimpleName();
    private ResponseHandler responseHandler;

    public GetAPIRequest(Activity activity, String url, ResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
        this.url = url;
        pDialog = new ProgressDialog(activity, R.style.AppCompatAlertDialogStyle);
        pDialog.setMessage("Loading...");
        pDialog.show();
    }


    public void initiate() {

        StringRequest strreq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String Response) {
                pDialog.dismiss();

                JSONObject reader;
                try {
                    reader = new JSONObject(Response);

                    String status = reader.getString("status");
                    String msg = reader.getString("message");
                    responseHandler.onResponse(Response);
                } catch (JSONException e) {
                    e.printStackTrace();
                    responseHandler.onError("Some Error ");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                e.printStackTrace();
                VolleyLog.d(TAG, "Error: " + e.getMessage());
                Log.d(TAG, "" + e.getMessage() + "," + e.toString());
                responseHandler.onError(e.getMessage() + "," + e.toString());
                pDialog.dismiss();
            }
        });
        AppController.getInstance().addToRequestQueue(strreq);
    }


    public interface ResponseHandler {
        void onResponse(String response);

        void onError(String error);
    }
}
