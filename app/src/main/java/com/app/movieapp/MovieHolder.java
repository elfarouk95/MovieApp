package com.app.movieapp;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;



public class MovieHolder extends RecyclerView.ViewHolder {


    TextView RelDate;
    TextView Name;
    ImageView pic;
    RatingBar rat;

    public MovieHolder(@NonNull View itemView) {
        super(itemView);

        RelDate = itemView.findViewById(R.id.releasedate);
        Name = itemView.findViewById(R.id.name);
        pic = itemView.findViewById(R.id.Img);
        rat = itemView.findViewById(R.id.rating);
    }

}
