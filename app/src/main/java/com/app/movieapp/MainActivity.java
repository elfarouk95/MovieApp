package com.app.movieapp;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.app.movieapp.Model.Api;
import com.app.movieapp.Model.Movie;
import com.app.movieapp.Model.MovieModel;
import com.app.movieapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    GridLayoutManager gridLayoutManager;
    ActivityMainBinding binding;
    Adapter a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        gridLayoutManager = new GridLayoutManager(this, 2);
        a = new Adapter();

        binding.ls.setLayoutManager(gridLayoutManager);
        binding.ls.setAdapter(a);


        binding.ls.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    load();
                }
            }
        });

        load();

    }


    int x = 1;

    void load() {
        Api.getClient().GetMovie("63103295acf92a181b68289f20ca0e98", x).enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                a.setMovies((ArrayList<Movie>) response.body().getResults());
                if (x < response.body().getTotalPages()) {
                    x++;
                }

            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "aa", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
