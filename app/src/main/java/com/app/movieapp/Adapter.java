package com.app.movieapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.movieapp.Model.Movie;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<MovieHolder> {

    ArrayList<Movie> movies = new ArrayList<>();
    Context context;

    public void setMovies(ArrayList<Movie> movies) {
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        return new MovieHolder(LayoutInflater.from(context).inflate(R.layout.movieitem, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder m, int i) {

        final Movie n = movies.get(i);
        m.Name.setText(n.getOriginalTitle());
        m.rat.setRating((float) n.getVoteAverage() / 2);
        m.RelDate.setText(n.getReleaseDate());
        String imgPath = "https://image.tmdb.org/t/p/w500/" + n.getPosterPath();

        Glide.with(context).load(imgPath).into(m.pic);

        m.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, DetailsActivity.class);
                in.putExtra("name", n.getTitle());
                in.putExtra("id", n.getId());
                in.putExtra("date", n.getReleaseDate());
                in.putExtra("img", n.getBackdropPath());
                in.putExtra("overView", n.getOverview());
                context.startActivity(in);
            }
        });
//https://image.tmdb.org/t/p/w500/
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
