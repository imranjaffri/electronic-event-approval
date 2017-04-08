package com.example.choudry.electroniceventapproval;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView singer_first_result;
    private TextView singer_second_result;
    private TextView vote_singer_first;
    private TextView vote_singer_sencond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_result);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        singer_first_result = (TextView) findViewById(R.id.singer_first_result);
        singer_second_result = (TextView) findViewById(R.id.singer_second_result);
        vote_singer_first = (TextView) findViewById(R.id.vote_singer_first);
        vote_singer_sencond = (TextView) findViewById(R.id.vote_singer_second);

        // Getting fonts...
        Typeface bold_font = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Bold.ttf");
        Typeface simple_font = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");

        // Applying fonts...
        singer_first_result.setTypeface(simple_font);
        singer_second_result.setTypeface(simple_font);
        vote_singer_first.setTypeface(simple_font);
        vote_singer_sencond.setTypeface(simple_font);


        ImageButton backBtn = (ImageButton) findViewById(R.id.back_btn_result);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent backIntent = new Intent(ResultActivity.this, ThreecategoryActivity.class);
//                startActivity(backIntent);
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);

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
