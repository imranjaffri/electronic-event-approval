package com.example.choudry.electroniceventapproval;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.choudry.electroniceventapproval.api.CallPostAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements CallPostAPI.ResponseHandler {

    TextView textSignUp;
    TextView do_u_want;
    Button btnSignIn;
    private EditText email;
    private EditText password;
    private String userName;
    private String userPassword;
    private TextView tite_text;
    private TextView tite_text2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = (EditText) findViewById(R.id.et_mail_signIn);
        password = (EditText) findViewById(R.id.et_pass_signIn);
        textSignUp = (TextView) findViewById(R.id.tv_signUp);
        tite_text = (TextView) findViewById(R.id.title_text);
        tite_text2 = (TextView) findViewById(R.id.title_text2);
        do_u_want = (TextView) findViewById(R.id.doUWant);
        btnSignIn = (Button) findViewById(R.id.btn_SignIn);

        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });


        // Getting fonts...
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Bold.ttf");
        Typeface custom_font_bold = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");

        // Applying fonts...
        email.setTypeface(custom_font);
        password.setTypeface(custom_font);
        textSignUp.setTypeface(custom_font);
        btnSignIn.setTypeface(custom_font);
        do_u_want.setTypeface(custom_font);
        tite_text.setTypeface(custom_font);
        tite_text2.setTypeface(custom_font);




        btnSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                userName = email.getText().toString();
                userPassword = password.getText().toString();


                String url = "http://sgs.sentinelireports.com/webservices-si-app/user_login.php";
                Map<String, String> map = new HashMap<String, String>();
                map.put("request", "ios");
                map.put("email", userName);
                map.put("password", userPassword);
                map.put("type", "guard");

                CallPostAPI callPostAPI = new CallPostAPI(LoginActivity.this, url, map);
                callPostAPI.Call();


            }
        });


    }

    @Override
    public void onResponse(String response) {
        JSONObject reader = null;

        try {
            reader = new JSONObject(response);

            String msg = reader.getString("message");
            String success = reader.getString("success");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(String error) {
        Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
    }
}
