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

    User user = new User("Tester","123-456-7890",false);
//    private Job testJob = new Job(user,2,"12:30","123",3);
    public List<Job> jobs = new ArrayList<Job>();
    private Context context;

//    public JobAdapter(final Context context) {
//        this.context=context;
//        Job job1 = new Job(user,2,"12:00","3:00",200);
//        Job job2 = new Job(user,1,"12:00","3:00",100);
//        Job job3 = new Job(user,3,"12:00","3:00",250);
//        jobs.add(job1);
//        jobs.add(job2);
//        jobs.add(job3);
//    }

    public JobAdapter(List<Job>jobs,final Context context) {
        this.jobs = jobs;
        this.context = context;
    }

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
