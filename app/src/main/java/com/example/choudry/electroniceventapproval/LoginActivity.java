package com.example.choudry.electroniceventapproval;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.choudry.electroniceventapproval.api.GetAPIRequest;
import com.example.choudry.electroniceventapproval.app.AppController;
import com.example.choudry.electroniceventapproval.data.User;
import com.example.choudry.electroniceventapproval.data.UserLogin;
import com.example.choudry.electroniceventapproval.utils.SharedPreferenceUtils;
import com.google.gson.Gson;

import java.util.ArrayList;

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


    RequestQueue ExampleRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ExampleRequestQueue = Volley.newRequestQueue(this);

        email = (EditText) findViewById(R.id.et_mail_signIn);

        email.setText("admin@admin.com");

        password = (EditText) findViewById(R.id.et_pass_signIn);

        password.setText("abc123");

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
                    AppController.showSnackBar(LoginActivity.this, loginLaout, "Please Enter the Credentials");

                } else if (userPassword.equals("") && !userName.matches(emailPattern)) {

                    AppController.showSnackBar(LoginActivity.this, loginLaout, "Please Enter a Valid Email");

                } else if (!userName.equals("") && userPassword.equals("")) {

                    AppController.showSnackBar(LoginActivity.this, loginLaout, "please Enter the Password");


                } else if (userName.equals("") && !userPassword.equals("")) {

                    AppController.showSnackBar(LoginActivity.this, loginLaout, "please Enter a Email");

                } else if (!userName.matches(emailPattern)) {

                    AppController.showSnackBar(LoginActivity.this, loginLaout, "Not Valid Email");

                } else {

                    // api call...
                    String url = "http://s5technology.com/demo/student/api/user/signin?email=" + userName + "&password=" + userPassword;


                    new GetAPIRequest(LoginActivity.this, url, new GetAPIRequest.ResponseHandler() {
                        @Override
                        public void onResponse(String response) {
                            Log.v("Response", response);

                            UserLogin userHandle = new Gson().fromJson(response, UserLogin.class);

                            if (userHandle == null)
                                return;

                            ArrayList<User> users = userHandle.getData();

                            if (users != null && users.size() > 0) {
                                User user = userHandle.getData().get(0);
                                SharedPreferenceUtils.saveLoggedUser(LoginActivity.this, user);
                                switch (user.getType()) {
                                    case "0":
                                        startActivity(new Intent(LoginActivity.this, VoteCastActivity.class));
                                        finish();
                                        break;
                                    case "1":
                                        startActivity(new Intent(LoginActivity.this, ThreecategoryActivity.class));
                                        finish();
                                        break;
                                    default:
                                        startActivity(new Intent(LoginActivity.this, ReceivedActivity.class));
                                        finish();
                                        break;
                                }
                            }
                        }

                        @Override
                        public void onError(String error) {
                            AppController.showSnackBar(LoginActivity.this, loginLaout, error);
                        }
                    }).initiate();

                }

            }


        });


    }


}
