package com.example.choudry.electroniceventapproval;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class EventApprovalActivity extends AppCompatActivity {

    private static int IMG_RESULT = 1;
    String ImageDecode;
    ImageButton btnImage;
    ImageView imageViewLoad;
    Button btn;
    Intent intent;
    String[] FILE;

    private EditText title;
    private EditText description;
    private Button submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_approval);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_event_approval);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        btnImage = (ImageButton) findViewById(R.id.btn_uploadImage);
        imageViewLoad = (ImageView) findViewById(R.id.iv_event);
        btn = (Button) findViewById(R.id.btn_submit);
        title = (EditText) findViewById(R.id.et_title);
        description = (EditText) findViewById(R.id.description);


        // Getting fonts...
        Typeface bold_font = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Bold.ttf");
        Typeface simple_font = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");

        // Applying fonts...
        btn.setTypeface(simple_font);
        title.setTypeface(simple_font);
        description.setTypeface(simple_font);


        ImageButton backBtn = (ImageButton) findViewById(R.id.back_btn_event_approval);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent backIntent = new Intent(EventApprovalActivity.this, ThreecategoryActivity.class);
//                startActivity(backIntent);
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);

            }
        });


        btnImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, IMG_RESULT);

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == IMG_RESULT && resultCode == RESULT_OK
                    && null != data) {


                Uri URI = data.getData();
                String[] FILE = {MediaStore.Images.Media.DATA};


                Cursor cursor = getContentResolver().query(URI,
                        FILE, null, null, null);

                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(FILE[0]);
                ImageDecode = cursor.getString(columnIndex);
                cursor.close();

                imageViewLoad.setImageBitmap(BitmapFactory
                        .decodeFile(ImageDecode));

            }
        } catch (Exception e) {
            Toast.makeText(this, "Please try again", Toast.LENGTH_LONG)
                    .show();
        }


    }
}

