package com.example.choudry.electroniceventapproval;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    private EditText confirmPassword;
    private Button register_btn;
    String user_email;
    String user_password;
    String user_comfirm_password;
    private TextView tite_text;
    private TextView tite_text2;
    private TextView sign_in_again;
    private TextView already_account;
    private ProgressDialog pDialog;
    private RelativeLayout signUpLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.et_mail);
        password = (EditText) findViewById(R.id.et_pass);
        confirmPassword = (EditText) findViewById(R.id.et_cnfrmPass);
        register_btn = (Button) findViewById(R.id.btn_register);
        tite_text = (TextView) findViewById(R.id.electonicTitleRegister);
        tite_text2 = (TextView) findViewById(R.id.approvalTitleRegister);
        sign_in_again = (TextView) findViewById(R.id.tv_signIn);
        already_account = (TextView) findViewById(R.id.alreadyAccount);
        signUpLayout = (RelativeLayout) findViewById(R.id.signUpLayout);


        // Getting fonts...
        Typeface bold_font = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Bold.ttf");
        Typeface simple_font = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");

        // Applying fonts...
        email.setTypeface(simple_font);
        password.setTypeface(simple_font);
        confirmPassword.setTypeface(simple_font);
        register_btn.setTypeface(simple_font);
        tite_text.setTypeface(simple_font);
        tite_text2.setTypeface(simple_font);
        sign_in_again.setTypeface(bold_font);
        already_account.setTypeface(simple_font);

        sign_in_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInAgaintIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(signInAgaintIntent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                finish();

            }
        });


        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user_email = email.getText().toString();
                user_password = password.getText().toString();
                user_comfirm_password = confirmPassword.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


                if (user_email.equals("") && user_password.equals("") && user_comfirm_password.equals("")) {

                    Snackbar snackbar = Snackbar.make(signUpLayout, "Please Enter the Credentials", Snackbar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setPadding(20, 0, 0, 0);
                    snackbar.show();


                } else if (user_password.equals("") && user_comfirm_password.equals("") && !user_email.matches(emailPattern)) {

                    Snackbar snackbar = Snackbar.make(signUpLayout, "Please Enter a Valid Email", Snackbar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setPadding(20, 0, 0, 0);
                    snackbar.show();

                } else if (!user_email.equals("") && user_comfirm_password.equals("") && user_password.equals("")) {

                    Snackbar snackbar = Snackbar.make(signUpLayout, "please Enter the Password", Snackbar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setPadding(20, 0, 0, 0);
                    snackbar.show();


                } else if (!user_email.equals("") && !user_comfirm_password.equals("") && user_password.equals("")) {

                    Snackbar snackbar = Snackbar.make(signUpLayout, "please Enter the Password", Snackbar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setPadding(20, 0, 0, 0);
                    snackbar.show();


                } else if (user_email.equals("") && !user_password.equals("") && !user_comfirm_password.equals("")) {

                    Snackbar snackbar = Snackbar.make(signUpLayout, "please Enter a Email", Snackbar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setPadding(20, 0, 0, 0);
                    snackbar.show();

                } else if (!user_email.equals("") && !user_password.equals("") && user_comfirm_password.equals("")) {

                    Snackbar snackbar = Snackbar.make(signUpLayout, "please Enter Confirm Password", Snackbar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setPadding(20, 0, 0, 0);
                    snackbar.show();

                } else if (!user_email.equals("") && !user_password.equals("") && !user_comfirm_password.matches(user_password)) {

                    Snackbar snackbar = Snackbar.make(signUpLayout, "Password does not matches", Snackbar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setPadding(20, 0, 0, 0);
                    snackbar.show();

                } else if (!user_email.matches(emailPattern)) {

                    Snackbar snackbar = Snackbar.make(signUpLayout, "Not Valid Email", Snackbar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setPadding(20, 0, 0, 0);
                    snackbar.show();
                } else {

                    // Api Call.......

                    String url = "http://s5technology.com/demo/student/api/user/signup";
                    pDialog = new ProgressDialog(RegisterActivity.this, R.style.AppCompatAlertDialogStyle);
                    pDialog.setMessage("Signing Up..");
                    pDialog.show();

                    StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, response);
                            pDialog.hide();

                            JSONObject reader=null;

                            try {
                                reader = new JSONObject(response);

                                String status = reader.getString("status");
                                String message = reader.getString("message");

                                if (status.equals("true")) {
                                    CommonUtils.showToast(message, RegisterActivity.this);


                                } else if (status.equals("false")) {
                                    CommonUtils.showToast(message, RegisterActivity.this);
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

                            CommonUtils.showToast(error.toString(), RegisterActivity.this);
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> map = new HashMap<String, String>();


                            map.put("email", user_email);
                            map.put("password", user_password);
                            map.put("type", "0");


                            return map;
                        }

                    };

                    AppController.getInstance().addToRequestQueue(sr);

                }


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
