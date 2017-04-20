package com.example.choudry.electroniceventapproval;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.choudry.electroniceventapproval.api.PostAPIRequest;
import com.example.choudry.electroniceventapproval.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreatePollActivity extends AppCompatActivity {


    private TextView firstText;
    private TextView secondText;
    private EditText firstSinger;
    private EditText SecondSinger;
    private Button doneBtn;
    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_poll);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_create_poll);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        firstText = (TextView) findViewById(R.id.createPoll);
        secondText = (TextView) findViewById(R.id.betweenSingers);
        firstSinger = (EditText) findViewById(R.id.et_singerfirst);
        SecondSinger = (EditText) findViewById(R.id.et_singersecond);
        doneBtn = (Button) findViewById(R.id.btn_done);


        ImageButton backBtn = (ImageButton) findViewById(R.id.back_btn_create_poll);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });


        // Getting fonts...
        Typeface bold_font = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Bold.ttf");
        Typeface simple_font = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");

        // Applying fonts...
        firstText.setTypeface(simple_font);
        secondText.setTypeface(simple_font);
        firstSinger.setTypeface(simple_font);
        SecondSinger.setTypeface(simple_font);
        doneBtn.setTypeface(simple_font);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = firstSinger.getText().toString();
                String secondName = SecondSinger.getText().toString();

                if (firstName.equals("") || secondName.equals("")) {

                    AppController.showSnackBar(CreatePollActivity.this, mainLayout, "Please Enter the names");

                } else {

                    String url = "http://s5technology.com/demo/student/api/poll/create";// Api Call......

                    Map<String, String> map = new HashMap<String, String>();
                    map.put("poll_name", "Concert");
                    map.put("first_nominee", firstName);
                    map.put("second_nominee", secondName);


                    new PostAPIRequest(CreatePollActivity.this, url, map, new PostAPIRequest.ResponseHandler() {
                        @Override
                        public void onResponse(String response) {

                            //{"status":true,"message":"Saved","data":[]}

                            try {
                                JSONObject obj = new JSONObject(response);

                                if (obj.getString("status").equals("true"))
                                    AppController.showSnackBar(CreatePollActivity.this, mainLayout, "Voting Poll created successfully");
                                else
                                    AppController.showSnackBar(CreatePollActivity.this, mainLayout, "Error occure please try again");


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.v("Response", response);
                        }

                        @Override
                        public void onError(String error) {
                            AppController.showSnackBar(CreatePollActivity.this, mainLayout, error);
                        }
                    }).initiate();

                }

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }
}
