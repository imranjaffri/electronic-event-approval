package com.example.choudry.electroniceventapproval.adapters;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.choudry.electroniceventapproval.R;
import com.example.choudry.electroniceventapproval.data.polls.PollsData;
import com.example.choudry.electroniceventapproval.data.polls.PollsList;

import java.util.List;

public class MoviesAdapterResult extends RecyclerView.Adapter<MoviesAdapterResult.MyViewHolder> {

    private List<PollsData> moviesList;
    private PollsList pollsList;


    public MoviesAdapterResult(FragmentActivity activity, PollsList pollsList) {

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView singer_1, singer_2, Singer_1_vote, Singer_2_vote;
        LinearLayout liLayout;

        MyViewHolder(View view) {
            super(view);
            singer_1 = (TextView) view.findViewById(R.id.singer_1);
            singer_2 = (TextView) view.findViewById(R.id.singer_2);
            Singer_1_vote = (TextView) view.findViewById(R.id.singer_1_vote);
            Singer_2_vote = (TextView) view.findViewById(R.id.singer_2_vote);
            liLayout = (LinearLayout) view.findViewById(R.id.liLayout);

        }
    }


    public MoviesAdapterResult(List<PollsData> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.polls_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final PollsData movie = moviesList.get(position);
        holder.singer_1.setText(movie.getFirst_nominee());
        holder.singer_2.setText(movie.getSecond_nominee());
        holder.Singer_1_vote.setText(movie.getFirst_nominee_votes());
        holder.Singer_2_vote.setText(movie.getSecond_nominee_votes());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}