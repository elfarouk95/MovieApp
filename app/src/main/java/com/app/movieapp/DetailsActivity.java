package com.app.movieapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.app.movieapp.Model.Api;
import com.app.movieapp.VideoModel.Result;
import com.app.movieapp.VideoModel.VideoRsult;
import com.app.movieapp.databinding.ActivityDetailsBinding;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {


    ActivityDetailsBinding binding;

    VidoAdapter adapter;
    LinearLayoutManager lin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_details);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        lin = new LinearLayoutManager(this);
        adapter = new VidoAdapter();
        binding.Videosls.setAdapter(adapter);
        binding.Videosls.setLayoutManager(lin);
        Intent n = getIntent();

        long id = n.getLongExtra("id", 49026);
        binding.overview.setText(n.getStringExtra("overView"));
        binding.MovName.setText(n.getStringExtra("name"));
        binding.RelDate.setText(n.getStringExtra("date"));

        String path = "https://image.tmdb.org/t/p/w780/" + n.getStringExtra("img");

        Glide.with(this).load(path).into(binding.oic);


        Api.getClient().Getvideos(id, "63103295acf92a181b68289f20ca0e98").enqueue(new Callback<VideoRsult>() {
            @Override
            public void onResponse(Call<VideoRsult> call, Response<VideoRsult> response) {
                adapter.setVideos((ArrayList<Result>) response.body().getResults());
            }

            @Override
            public void onFailure(Call<VideoRsult> call, Throwable t) {

            }
        });

    }


}
