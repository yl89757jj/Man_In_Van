package com.example.luy.maninvan;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Lulalulali on 11/15/17.
 */

public class JobAdapter extends RecyclerView.Adapter<JobViewHolder> {
    public JobAdapter() {

    }

    private List<Job> jobs;
    private Context context;
    public static String departureArea = "";
    public static String arrivalArea = "";
    public static String datePreferred = "";


    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_job, parent, false);
        return new JobViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(JobViewHolder holder, int position) {
        Job job = jobs.get(position);
        holder.bind(job);
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }
}
