package com.app.movieapp.Model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Api {

    private static Retrofit retrofit = null;

    public static ApiInterface getClient() {

        // change your base URL
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.themoviedb.org/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        //Creating object for our interface
        ApiInterface api = retrofit.create(ApiInterface.class);
        return api; // return the APIInterface object
    }
}
