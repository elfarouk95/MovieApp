package com.app.movieapp.Model;

import com.app.movieapp.VideoModel.VideoRsult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/3/discover/movie")
    Call<MovieModel> GetMovie(@Query("api_key") String api_key, @Query("page") int page);

    @GET("/3/movie/{id}/videos")
    Call<VideoRsult> Getvideos(@Path("id") long id, @Query("api_key") String api_key);

}
