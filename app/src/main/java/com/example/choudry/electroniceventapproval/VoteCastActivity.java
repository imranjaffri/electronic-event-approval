package com.example.choudry.electroniceventapproval;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class VoteCastActivity extends AppCompatActivity {


    private Button cast_vote_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_cast);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_vote_cast);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        cast_vote_btn = (Button) findViewById(R.id.cast_vote_btn);


        cast_vote_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }
}
