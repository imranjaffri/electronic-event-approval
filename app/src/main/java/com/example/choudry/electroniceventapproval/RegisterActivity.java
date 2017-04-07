package com.example.choudry.electroniceventapproval;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.choudry.electroniceventapproval.api.CallPostAPI;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements CallPostAPI.ResponseHandler {

    private EditText email;
    private EditText password;
    private EditText comformPassword;
    private Button register_btn;
    String user_email;
    String user_password;
    String user_comfirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.et_mail);
        password = (EditText) findViewById(R.id.et_pass);
        comformPassword = (EditText) findViewById(R.id.et_cnfrmPass);
        register_btn = (Button) findViewById(R.id.btn_register);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user_email = email.getText().toString();
                user_password = password.getText().toString();
                user_comfirm_password = comformPassword.getText().toString();

                String url = "";
                Map<String, String> map = new HashMap<String, String>();
                map.put("", "");
                map.put("", "");
                map.put("", "");
                map.put("", "");

                CallPostAPI callPostAPI = new CallPostAPI(RegisterActivity.this, url, map);
                callPostAPI.Call();

            }
        });


    }

    @Override
    public void onResponse(String response) {

    }

    @Override
    public void onError(String error) {

    }
}
