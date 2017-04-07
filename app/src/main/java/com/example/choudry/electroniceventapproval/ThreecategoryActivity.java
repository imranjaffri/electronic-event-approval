package com.example.choudry.electroniceventapproval;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ThreecategoryActivity extends AppCompatActivity {

    ImageView image1;
    ImageView image2;
    ImageView image3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threecategory);


        image1=(ImageView)findViewById(R.id.eventApproval);
        image2=(ImageView)findViewById(R.id.voteCasting);
        image3=(ImageView)findViewById(R.id.showResult);


        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ThreecategoryActivity.this, EventApprovalActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });




        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ThreecategoryActivity.this, CreatePollActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });








        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ThreecategoryActivity.this, ResultActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });









    }
}
