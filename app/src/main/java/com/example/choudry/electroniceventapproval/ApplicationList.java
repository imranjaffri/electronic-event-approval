package com.example.choudry.electroniceventapproval;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.choudry.electroniceventapproval.adapters.ApplicationAdapter;
import com.example.choudry.electroniceventapproval.api.GetAPIRequest;
import com.example.choudry.electroniceventapproval.data.application.AppList;
import com.example.choudry.electroniceventapproval.data.application.ApplicationData;
import com.example.choudry.electroniceventapproval.fragments.ResultsLoadListener;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ApplicationList extends AppCompatActivity {

    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    ArrayList<ApplicationData> appData;
    private ResultsLoadListener loadListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_application_list);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_application);


        apiCall();

        ImageButton backBtn = (ImageButton) findViewById(R.id.back_btn_application_list);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        apiCall();
    }

    private void apiCall() {

        // api call...
        String url = "http://s5technology.com/demo/student/api/event/list";

        new GetAPIRequest(ApplicationList.this, url, new GetAPIRequest.ResponseHandler() {
            @Override
            public void onResponse(String response) {
                Log.v("Response", response);




                AppList applist = new Gson().fromJson(response, AppList.class);
                appData = applist.getData();
                ApplicationAdapter adapter = new ApplicationAdapter(appData);
                layoutManager = new LinearLayoutManager(ApplicationList.this);
                layoutManager.setReverseLayout(true);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onError(String error) {



            }
        }).initiate();
    }
}
