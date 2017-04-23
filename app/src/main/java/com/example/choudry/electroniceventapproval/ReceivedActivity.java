package com.example.choudry.electroniceventapproval;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.choudry.electroniceventapproval.api.PostAPIRequest;
import com.example.choudry.electroniceventapproval.data.application.ApplicationData;
import com.example.choudry.electroniceventapproval.utils.CommonUtils;
import com.example.choudry.electroniceventapproval.utils.SharedPreferenceUtils;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class ReceivedActivity extends AppCompatActivity {

    private Button approveBtn;
    private Button disApproveBtn;
    private EditText discription;
    private String disc_text_received;
    private ApplicationData appData;
    private ImageView image_received;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        approveBtn = (Button) findViewById(R.id.approved);
        disApproveBtn = (Button) findViewById(R.id.disApprove);
        discription = (EditText) findViewById(R.id.discription_received);
        image_received = (ImageView) findViewById(R.id.iv_received);



        appData = getIntent().getParcelableExtra("application_data");
        Picasso.with(ReceivedActivity.this).load(appData.getImage() + ".png").into(image_received);


        // Getting fonts...
        Typeface bold_font = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Bold.ttf");
        Typeface simple_font = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");

        // Applying fonts...
        approveBtn.setTypeface(simple_font);
        disApproveBtn.setTypeface(simple_font);
        discription.setTypeface(simple_font);

        disc_text_received = discription.getText().toString();

        approveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiCallForApproved();
            }
        });

        disApproveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiCallForDisApprove();
            }
        });


    }

    private void apiCallForApproved() {

        String url = "http://s5technology.com/demo/student/api/event/update";// Api Call....

        Map<String, String> map = new HashMap<String, String>();
        map.put("id", appData.getEvent_id());
        map.put("approved_by_user_id", SharedPreferenceUtils.getLoggedUser(ReceivedActivity.this).getUser_id());
        map.put("status", "Approved");
        map.put("description", disc_text_received);


        new PostAPIRequest(ReceivedActivity.this, url, map, new PostAPIRequest.ResponseHandler() {
            @Override
            public void onResponse(String response) {
                CommonUtils.showToast(response, ReceivedActivity.this);
                finish();

            }

            @Override
            public void onError(String error) {
                CommonUtils.showToast(error, ReceivedActivity.this);
            }
        }).initiate();
    }


    private void apiCallForDisApprove() {

        String url = "http://s5technology.com/demo/student/api/event/update";// Api Call....

        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "");
        map.put("approved_by_user_id", "");
        map.put("status", "Not Approved");
        map.put("description", disc_text_received);


        new PostAPIRequest(ReceivedActivity.this, url, map, new PostAPIRequest.ResponseHandler() {
            @Override
            public void onResponse(String response) {
                CommonUtils.showToast(response, ReceivedActivity.this);
                finish();

            }

            @Override
            public void onError(String error) {
                CommonUtils.showToast(error, ReceivedActivity.this);
            }
        }).initiate();
    }


}
