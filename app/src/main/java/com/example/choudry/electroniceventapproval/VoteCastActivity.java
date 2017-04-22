package com.example.choudry.electroniceventapproval;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.choudry.electroniceventapproval.api.PostAPIRequest;
import com.example.choudry.electroniceventapproval.utils.CommonUtils;

import java.util.HashMap;
import java.util.Map;

public class VoteCastActivity extends AppCompatActivity {


    private Button cast_vote_btn;
    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_cast);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_vote_cast);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);




        cast_vote_btn = (Button) findViewById(R.id.cast_vote_btn);
        mainLayout = (LinearLayout) findViewById(R.id.mainLayoutVote);


        cast_vote_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://s5technology.com/demo/student/api/poll/update";// Api Call......

                Map<String, String> map = new HashMap<String, String>();
                map.put("poll_id", "");
                map.put("selection", "");


                new PostAPIRequest(VoteCastActivity.this, url, map, new PostAPIRequest.ResponseHandler() {
                    @Override
                    public void onResponse(String response) {
                        CommonUtils.showToast(response, VoteCastActivity.this);

                    }

                    @Override
                    public void onError(String error) {
                        CommonUtils.showToast(error, VoteCastActivity.this);
                    }
                }).initiate();

            }
        });


    }
}
