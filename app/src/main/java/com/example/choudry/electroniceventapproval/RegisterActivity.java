package com.example.choudry.electroniceventapproval;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.choudry.electroniceventapproval.api.CallPostAPI;
import com.example.choudry.electroniceventapproval.app.AppController;
import com.example.choudry.electroniceventapproval.utils.CommonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements CallPostAPI.ResponseHandler {

    private static final String TAG = RegisterActivity.class.getName();
    private EditText email;
    private EditText password;
    private EditText comformPassword;
    private Button register_btn;
    String user_email;
    String user_password;
    String user_comfirm_password;
    private TextView tite_text;
    private TextView tite_text2;
    private TextView sign_in_again;
    private TextView already_account;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.et_mail);
        password = (EditText) findViewById(R.id.et_pass);
        comformPassword = (EditText) findViewById(R.id.et_cnfrmPass);
        register_btn = (Button) findViewById(R.id.btn_register);
        tite_text = (TextView) findViewById(R.id.electonicTitleRegister);
        tite_text2 = (TextView) findViewById(R.id.approvalTitleRegister);
        sign_in_again = (TextView) findViewById(R.id.tv_signIn);
        already_account = (TextView) findViewById(R.id.alreadyAccount);


        // Getting fonts...
        Typeface bold_font = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Bold.ttf");
        Typeface simple_font = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");

        // Applying fonts...
        email.setTypeface(simple_font);
        password.setTypeface(simple_font);
        comformPassword.setTypeface(simple_font);
        register_btn.setTypeface(simple_font);
        tite_text.setTypeface(simple_font);
        tite_text2.setTypeface(simple_font);
        sign_in_again.setTypeface(bold_font);
        already_account.setTypeface(simple_font);


        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String url = "http://s5technology.com/demo/student/api/user/signup";
                pDialog = new ProgressDialog(RegisterActivity.this, R.style.AppCompatAlertDialogStyle);
                pDialog.setMessage("Signing Up...");
                pDialog.show();

                StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);
                        pDialog.hide();

                        JSONObject reader;

                        try {
                            reader = new JSONObject(response);

                            String status = reader.getString("status");
                            String msg = reader.getString("message");

                            if (status.equals("true")) {
                                CommonUtils.showToast(msg, RegisterActivity.this);


                            } else {
                                CommonUtils.showToast(msg, RegisterActivity.this);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        Log.d(TAG, "" + error.getMessage() + "," + error.toString());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> map = new HashMap<String, String>();


//                        map.put("email", user_email);
//                        map.put("password", user_password);
//                        map.put("type", "0");
//                            map.put("token", "c2VudGluZWwyMDE2");


                        return map;
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/x-www-form-urlencoded");
                        headers.put("abc", "value");
                        return headers;
                    }
                };

                AppController.getInstance().addToRequestQueue(sr);

            }


        });


    }

    @Override
    public void onResponse(String response) {

        Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onError(String error) {

    }
}
