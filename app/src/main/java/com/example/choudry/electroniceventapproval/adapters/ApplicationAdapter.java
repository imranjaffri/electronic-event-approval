package com.example.choudry.electroniceventapproval.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.choudry.electroniceventapproval.R;
import com.example.choudry.electroniceventapproval.ReceivedActivity;
import com.example.choudry.electroniceventapproval.data.application.ApplicationData;
import com.example.choudry.electroniceventapproval.data.polls.PollsList;

import java.util.List;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.MyViewHolder> {

    private List<ApplicationData> applicationList;

    Context context;

    public ApplicationAdapter(FragmentActivity activity, PollsList pollsList) {

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, status;
        LinearLayout liLayout;

        MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            status = (TextView) view.findViewById(R.id.status);
            liLayout = (LinearLayout) view.findViewById(R.id.liLayout_application);


        }
    }


    public ApplicationAdapter(List<ApplicationData> moviesList) {
        this.applicationList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.application_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ApplicationData application = applicationList.get(position);
        holder.title.setText(application.getTitle());
        holder.status.setText(application.getStatus());
        holder.liLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ReceivedActivity.class);
                i.putExtra("application_data", applicationList.get(position));
                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return applicationList.size();
    }
}