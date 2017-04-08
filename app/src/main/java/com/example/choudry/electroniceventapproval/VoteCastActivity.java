package com.example.choudry.electroniceventapproval;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VoteCastActivity extends AppCompatActivity {


    private Button cast_vote_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_cast);

        cast_vote_btn = (Button) findViewById(R.id.cast_vote_btn);

        cast_vote_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
