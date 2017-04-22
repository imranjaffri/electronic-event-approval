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
import com.example.choudry.electroniceventapproval.adapters.MoviesAdapter;
import com.example.choudry.electroniceventapproval.api.GetAPIRequest;
import com.example.choudry.electroniceventapproval.data.polls.PollsList;
import com.google.gson.Gson;

public class VotePollingFragment extends Fragment {


    private ResultsLoadListener loadListener;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MoviesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_vote_polling, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);


        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // api call...

        String url = "http://s5technology.com/demo/student/api/poll/list";


        new GetAPIRequest(getActivity(), url, new GetAPIRequest.ResponseHandler() {
            @Override
            public void onResponse(String response) {
                Log.v("Response", response);
                loadListener.onLoadSuccessful(0);


                PollsList pollsList = new Gson().fromJson(response, PollsList.class);
                adapter = new MoviesAdapter(pollsList.getData());
                layoutManager = new LinearLayoutManager(getActivity());
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