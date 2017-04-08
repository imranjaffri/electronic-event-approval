package com.example.choudry.electroniceventapproval;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ReceivedActivity extends AppCompatActivity {

    private Button approveBtn;
    private Button disApproveBtn;
    private EditText discription;
    private String disc_text_received;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received);

        approveBtn = (Button) findViewById(R.id.approved);
        disApproveBtn = (Button) findViewById(R.id.disApprove);
        discription = (EditText) findViewById(R.id.discription_received);

        // Getting fonts...
        Typeface bold_font = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Bold.ttf");
        Typeface simple_font = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");

        // Applying fonts...
        approveBtn.setTypeface(simple_font);
        disApproveBtn.setTypeface(simple_font);
        discription.setTypeface(simple_font);

        disc_text_received = discription.getText().toString();


    }
}
