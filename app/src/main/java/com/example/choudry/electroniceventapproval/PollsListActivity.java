package com.example.choudry.electroniceventapproval;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.example.choudry.electroniceventapproval.adapters.MoviesAdapter;
import com.example.choudry.electroniceventapproval.api.GetAPIRequest;
import com.example.choudry.electroniceventapproval.app.AppController;
import com.example.choudry.electroniceventapproval.data.polls.PollsList;
import com.google.gson.Gson;

public class PollsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polls_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        setTitle("Vote Polls");

        ImageButton backBtn = (ImageButton) findViewById(R.id.back_btn_categories);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent backIntent = new Intent(EventApprovalActivity.this, ThreecategoryActivity.class);
//                startActivity(backIntent);
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);

            }
        });


        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        new GetAPIRequest(PollsListActivity.this, "http://s5technology.com/demo/student/api/poll/list", new GetAPIRequest.ResponseHandler() {
            @Override
            public void onResponse(String response) {

                PollsList pollsList = new Gson().fromJson(response, PollsList.class);
                MoviesAdapter mAdapter = new MoviesAdapter(pollsList.getData());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onError(String error) {

                AppController.showSnackBar(PollsListActivity.this, findViewById(R.id.mainLayout), error);
            }
        }).initiate();
    }
}
