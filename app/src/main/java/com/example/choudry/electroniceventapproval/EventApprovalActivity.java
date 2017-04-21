package com.example.choudry.electroniceventapproval;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.choudry.electroniceventapproval.api.PostAPIRequest;
import com.example.choudry.electroniceventapproval.app.AppController;
import com.example.choudry.electroniceventapproval.utils.CommonUtils;
import com.example.choudry.electroniceventapproval.utils.SharedPreferenceUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EventApprovalActivity extends AppCompatActivity {

    private static int IMG_RESULT = 1;
    String ImageDecode;
    ImageButton btnImage;
    ImageView imageViewLoad;
    Button btn;
    String[] FILE;

    private EditText title;
    private EditText description;
    private Button submit_btn;

    private LinearLayout mainLayout;

    private final int SELECT_FILE = 2;
    private ProgressDialog pDialog;

    private Uri selectedImage;

    private String mimeType = "image/*";
    private String TAG = EventApprovalActivity.class.getSimpleName();

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
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);


        // Getting fonts...
        Typeface bold_font = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Bold.ttf");
        Typeface simple_font = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");

        // Applying fonts...
        btn.setTypeface(simple_font);
        title.setTypeface(simple_font);
        description.setTypeface(simple_font);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (description.getText().toString().equals("") || title.getText().toString().equals("") || selectedImage == null) {
                    AppController.showSnackBar(EventApprovalActivity.this, mainLayout, "Please add values to field");
                } else {
//                    sendForApproval()
                    sendForApproval();

//                    try {
//                        new Task_finder().execute(getFilePath(EventApprovalActivity.this, selectedImage));
//                    } catch (URISyntaxException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        });


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

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_FILE);

            }
        });

    }


    private void sendForApproval() {


        byte[] byteArrayImage = CommonUtils.convertImageToByte(selectedImage, EventApprovalActivity.this);

        Map<String, String> params = new HashMap<>();
        params.put("user_id", SharedPreferenceUtils.getLoggedUser(EventApprovalActivity.this).getUser_id());
        params.put("title", title.getText().toString());
        params.put("description", description.getText().toString());
        params.put("image", Base64.encodeToString(byteArrayImage, Base64.DEFAULT));

        new PostAPIRequest(EventApprovalActivity.this, "http://s5technology.com/demo/student/api/event/saveb", params, new PostAPIRequest.ResponseHandler() {
            @Override
            public void onResponse(String response) {
                Log.v("Response", response);
            }

            @Override
            public void onError(String error) {
                Log.v("Response", error);
            }
        }).initiate();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case SELECT_FILE: {
                if (resultCode == RESULT_OK) {
                    selectedImage = imageReturnedIntent.getData();
                    imageViewLoad.setImageURI(selectedImage);
                }
                break;
            }
        }
    }


    @SuppressLint("NewApi")
    public static String getFilePath(Context context, Uri uri) throws URISyntaxException {
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{
                        split[1]
                };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver()
                        .query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


    public class Task_finder extends AsyncTask<String, Void, String> {


        // can use UI thread here
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... param) {
            // TODO Auto-generated method stub


            //setup params
            Map<String, String> params = new HashMap<String, String>(2);
            params.put("user_id", "12");
            params.put("title", "its mine title");
            params.put("description", "is decription here");

            return multipartRequest("http://s5technology.com/demo/student/api/event/save", params, param[0], "image", "image/png");


//            MultipartUtility multipart = null;
//            try {
//                multipart = new MultipartUtility("http://s5technology.com/demo/student/api/event/save", "UTF-8");
//
//
//                // In your case you are not adding form data so ignore this
//                /*This is to add parameter values */
////                for (int i = 0; i < myFormDataArray.size(); i++) {
////                    multipart.addFormField(myFormDataArray.get(i).getParamName(),
////                            myFormDataArray.get(i).getParamValue());
////                }
//
//                multipart.addFormField("user_id", "12");
//                multipart.addFormField("title", "its mine title");
//                multipart.addFormField("description", "is decription here");
//
//
////add your file here.
//                /*This is to add file content*/
//                multipart.addFilePart("image",
//                        new File(param[0]));
//
//                List<String> response = multipart.finish();
//                Log.e(TAG, "SERVER REPLIED:");
//                for (String line : response) {
//                    Log.e(TAG, "Upload Files Response:::" + line);
//// get your server response here.
//                }
//                Log.e(TAG, "SERVER REPLIED:");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }

        @Override
        protected void onPostExecute(String result) {

            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
        }
    }

    public String multipartRequest(String urlTo, Map<String, String> parmas, String filepath, String filefield, String fileMimeType) {
        HttpURLConnection connection = null;
        DataOutputStream outputStream = null;
        InputStream inputStream = null;

        String twoHyphens = "--";
        String boundary = "*****" + Long.toString(System.currentTimeMillis()) + "*****";
        String lineEnd = "\r\n";

        String result = "";

        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;

        String[] q = filepath.split("/");
        int idx = q.length - 1;

        try {
            File file = new File(filepath);
            FileInputStream fileInputStream = new FileInputStream(file);

            URL url = new URL(urlTo);
            connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("User-Agent", "Android Multipart HTTP Client 1.0");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            connection.setRequestProperty("Accept", "*/*");

            outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"" + filefield + "\"; filename=\"" + q[idx] + "\"" + lineEnd);
            outputStream.writeBytes("Content-Type: " + fileMimeType + lineEnd);
            outputStream.writeBytes("Content-Transfer-Encoding: binary" + lineEnd);

            outputStream.writeBytes(lineEnd);

            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            while (bytesRead > 0) {
                outputStream.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }

            outputStream.writeBytes(lineEnd);

            // Upload POST Data
            Iterator<String> keys = parmas.keySet().iterator();
            while (keys.hasNext()) {
                String key = keys.next();
                String value = parmas.get(key);

                outputStream.writeBytes(twoHyphens + boundary + lineEnd);
                outputStream.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"" + lineEnd);
                outputStream.writeBytes("Content-Type: text/plain" + lineEnd);
                outputStream.writeBytes(lineEnd);
                outputStream.writeBytes(value);
                outputStream.writeBytes(lineEnd);
            }

            outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);


            int statusCode = connection.getResponseCode();


            if (statusCode >= 200 && statusCode < 400) {
                // Create an InputStream in order to extract the response object
                inputStream = connection.getInputStream();
            } else {
                inputStream = connection.getErrorStream();
            }

            result = this.convertStreamToString(inputStream);

            fileInputStream.close();
            inputStream.close();
            outputStream.flush();
            outputStream.close();

            return result;
        } catch (Exception e) {
            Log.v("Error", e.toString());
        }

        return result;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
// see http://www.androidsnippets.com/multipart-http-requests


}


