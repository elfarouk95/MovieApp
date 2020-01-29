package com.app.movieapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class VideoHolder extends RecyclerView.ViewHolder {
    TextView t;
    public VideoHolder(@NonNull View itemView) {
        super(itemView);
        t = itemView.findViewById(R.id.VName);
    }
}
