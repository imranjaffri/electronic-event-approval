package com.example.choudry.electroniceventapproval;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreatePollActivity extends AppCompatActivity {


    private TextView firstText;
    private TextView secondText;
    private EditText firstSinger;
    private EditText SecondSinger;
    private Button doneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_poll);

        firstText = (TextView) findViewById(R.id.createPoll);
        secondText = (TextView) findViewById(R.id.betweenSingers);
        firstSinger = (EditText) findViewById(R.id.et_singerfirst);
        SecondSinger = (EditText) findViewById(R.id.et_singersecond);
        doneBtn = (Button) findViewById(R.id.btn_done);


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

            }
        });


    }
}
