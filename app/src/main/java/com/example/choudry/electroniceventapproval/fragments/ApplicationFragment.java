package com.example.choudry.electroniceventapproval.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.choudry.electroniceventapproval.R;
import com.example.choudry.electroniceventapproval.adapters.ApplicationAdapterResult;
import com.example.choudry.electroniceventapproval.api.GetAPIRequest;
import com.example.choudry.electroniceventapproval.data.application.AppList;
import com.google.gson.Gson;

public class ApplicationFragment extends Fragment {


    private ResultsLoadListener loadListener;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ApplicationAdapterResult adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vote_polling, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        return rootView;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // api call...
        String url = "http://s5technology.com/demo/student/api/event/list";

        new GetAPIRequest(getActivity(), url, new GetAPIRequest.ResponseHandler() {
            @Override
            public void onResponse(String response) {
                Log.v("Response", response);
                loadListener.onLoadSuccessful(0);

                AppList applicationList = new Gson().fromJson(response, AppList.class);
                adapter = new ApplicationAdapterResult(applicationList.getData());
                layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setReverseLayout(true);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onError(String error) {
                Toast.makeText(getActivity(), "Error Occure while loading", Toast.LENGTH_LONG).show();
                loadListener.onLoadSuccessful(0);
            }
        }).initiate();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        loadListener = (ResultsLoadListener) context;
    }
}