package com.example.choudry.electroniceventapproval;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.choudry.electroniceventapproval.api.PostAPIRequest;
import com.example.choudry.electroniceventapproval.data.polls.PollsData;
import com.example.choudry.electroniceventapproval.utils.CommonUtils;

import java.util.HashMap;
import java.util.Map;

public class VoteCastActivity extends AppCompatActivity {


    private Button cast_vote_btn;
    private LinearLayout mainLayout;
    private PollsData data;
    private RadioGroup RadioBtnGroup;
    private String selection_value = "first";
    private RadioButton radioButton;
    private RadioButton radioButton1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_cast);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_vote_cast);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        data = getIntent().getParcelableExtra("data");


        cast_vote_btn = (Button) findViewById(R.id.cast_vote_btn);
        mainLayout = (LinearLayout) findViewById(R.id.mainLayoutVote);
        radioButton = (RadioButton) findViewById(R.id.yesRadioBtn);
        radioButton1 = (RadioButton) findViewById(R.id.noRadioBtn);


        radioButton.setText(data.getSecond_nominee());
        radioButton1.setText(data.getFirst_nominee());

        RadioBtnGroup = (RadioGroup) findViewById(R.id.RadioBtnGroup);
        RadioBtnGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                if (checkedId == R.id.yesRadioBtn)
                    selection_value = "second";
                else selection_value = "first";
            }
        });


        cast_vote_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://s5technology.com/demo/student/api/poll/update";// Api Call....

                Map<String, String> map = new HashMap<String, String>();
                map.put("poll_id", data.getPoll_id());
                map.put("selection", selection_value);


                new PostAPIRequest(VoteCastActivity.this, url, map, new PostAPIRequest.ResponseHandler() {
                    @Override
                    public void onResponse(String response) {
                        CommonUtils.showToast("vote has been casted", VoteCastActivity.this);
                        finish();

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
