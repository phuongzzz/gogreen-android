package com.simcoder.uber;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListJobActivity extends AppCompatActivity {

    RecyclerView listJob;
    ListJobAdapter adapter;
    List<JobEntity> listJobItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_job);
        listJobItem = new ArrayList<>();

        listJob = (RecyclerView) findViewById(R.id.list_job);
        for (int i = 0; i < 25; i++) {
            JobEntity entity = new JobEntity();
            entity.setName("NAME " + i);
            entity.setDate(DateFormat.format("dd/MM/yy hh:mm:ss", System.currentTimeMillis()).toString());
            entity.setContent("FUCK FUCK FUCK FUCK " + i + i + i + i);

            listJobItem.add(entity);

        }

        adapter = new ListJobAdapter(listJobItem, this);

        listJob.setLayoutManager(new LinearLayoutManager(this));
        listJob.setAdapter(adapter);


    }


    class ListJobAdapter extends RecyclerView.Adapter<JobViewHolder> {

        final List<JobEntity> listItem;
        Context context;

        public ListJobAdapter(List<JobEntity> listItem, Context context) {
            this.listItem = listItem;
            this.context = context;
        }

        @Override
        public JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new JobViewHolder(LayoutInflater.from(context).inflate(R.layout.item_job, parent, false));
        }

        @Override
        public void onBindViewHolder(JobViewHolder holder, int position) {
            holder.item = listItem.get(position);
            holder.name.setText(holder.item.getName());
            holder.date.setText(holder.item.getDate());
            holder.content.setText(holder.item.getContent());

        }

        @Override
        public int getItemCount() {
            return listItem.size();
        }
    }


    class JobViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView date;
        TextView content;

        JobEntity item;

        public JobViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_job_staff_name);
            date = (TextView) itemView.findViewById(R.id.item_job_date);
            content = (TextView) itemView.findViewById(R.id.item_job_content);


        }


    }


}
