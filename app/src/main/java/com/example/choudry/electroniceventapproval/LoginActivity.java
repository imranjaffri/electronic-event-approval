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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.choudry.electroniceventapproval.api.CallPostAPI;
import com.example.choudry.electroniceventapproval.app.AppController;
import com.example.choudry.electroniceventapproval.utils.CommonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getName();
    private TextView textSignUp;
    private TextView do_u_want;
    private Button btnSignIn;
    private EditText email;
    private EditText password;
    private String userName;
    private String userPassword;
    private TextView tite_text;
    private TextView tite_text2;
    private RelativeLayout loginLaout;
    private ProgressDialog pDialog;


    RequestQueue ExampleRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ExampleRequestQueue = Volley.newRequestQueue(this);

        email = (EditText) findViewById(R.id.et_mail_signIn);
        password = (EditText) findViewById(R.id.et_pass_signIn);
        textSignUp = (TextView) findViewById(R.id.tv_signUp);
        tite_text = (TextView) findViewById(R.id.title_text);
        tite_text2 = (TextView) findViewById(R.id.title_text2);
        do_u_want = (TextView) findViewById(R.id.doUWant);
        btnSignIn = (Button) findViewById(R.id.btn_SignIn);
        loginLaout = (RelativeLayout) findViewById(R.id.loginLayout);

        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);


            }
        });


        // Getting fonts...
        Typeface bold_font = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Bold.ttf");
        Typeface simple_font = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");

        // Applying fonts...
        email.setTypeface(simple_font);
        password.setTypeface(simple_font);
        textSignUp.setTypeface(bold_font);
        btnSignIn.setTypeface(simple_font);
        do_u_want.setTypeface(simple_font);
        tite_text.setTypeface(simple_font);
        tite_text2.setTypeface(simple_font);


        btnSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                userName = email.getText().toString();
                userPassword = password.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                // checks....


                if (userName.equals("") && userPassword.equals("")) {

                    Snackbar snackbar = Snackbar.make(loginLaout, "Please Enter the Credentials", Snackbar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setPadding(20, 0, 0, 0);
                    snackbar.show();


                } else if (userPassword.equals("") && !userName.matches(emailPattern)) {

                    Snackbar snackbar = Snackbar.make(loginLaout, "Please Enter a Valid Email", Snackbar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setPadding(20, 0, 0, 0);
                    snackbar.show();

                } else if (!userName.equals("") && userPassword.equals("")) {

                    Snackbar snackbar = Snackbar.make(loginLaout, "please Enter the Password", Snackbar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setPadding(20, 0, 0, 0);
                    snackbar.show();


                } else if (userName.equals("") && !userPassword.equals("")) {

                    Snackbar snackbar = Snackbar.make(loginLaout, "please Enter a Email", Snackbar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setPadding(20, 0, 0, 0);
                    snackbar.show();

                } else if (!userName.matches(emailPattern)) {

                    Snackbar snackbar = Snackbar.make(loginLaout, "Not Valid Email", Snackbar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setPadding(20, 0, 0, 0);
                    snackbar.show();

                } else {

                    // api call...
                    String url = "http://s5technology.com/demo/student/api/user/signin?email=" + userName + "&password=" + userPassword;
                    pDialog = new ProgressDialog(LoginActivity.this, R.style.AppCompatAlertDialogStyle);
                    pDialog.setMessage("Logging In...");
                    pDialog.show();

                    StringRequest strreq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String Response) {
                            pDialog.hide();
                            pDialog.hide();

                            JSONObject reader;

                            try {
                                reader = new JSONObject(Response);

                                String status = reader.getString("status");
                                String msg = reader.getString("message");

                                if (status.equals("true")) {
                                    CommonUtils.showToast(msg, LoginActivity.this);

                                    Intent intent = new Intent(LoginActivity.this, ThreecategoryActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                                    finish();


                                } else {
                                    CommonUtils.showToast(msg, LoginActivity.this);
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError e) {
                            e.printStackTrace();

                            VolleyLog.d(TAG, "Error: " + e.getMessage());
                            Log.d(TAG, "" + e.getMessage() + "," + e.toString());
                            CommonUtils.showToast(e.toString(), LoginActivity.this);
                        }
                    });
                    AppController.getInstance().addToRequestQueue(strreq);
                }

            }


        });


    }


}
