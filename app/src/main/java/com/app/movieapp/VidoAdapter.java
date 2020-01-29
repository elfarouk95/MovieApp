package com.app.movieapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.movieapp.VideoModel.Result;

import java.util.ArrayList;

public class VidoAdapter extends RecyclerView.Adapter<VideoHolder> {

    ArrayList<Result> videos = new ArrayList<>();
    Context context;

    public void setVideos(ArrayList<Result> videos) {
        this.videos = videos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        return new VideoHolder(LayoutInflater.from(context).inflate(R.layout.videoitem, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder v, final  int i) {

        v.t.setText(videos.get(i).getName());

        v.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "https://www.youtube.com/watch?v="+videos.get(i).getKey();
                Intent n = new Intent(Intent.ACTION_VIEW, Uri.parse(path));
                context.startActivity(n);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }
}
