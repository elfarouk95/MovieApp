package com.app.movieapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
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
    BottomNavigationView navigation;
    GridLayoutManager gridLayoutManager;
    ActivityMainBinding binding;
    Adapter a;
    DrawerLayout drawer;

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
        initNavigationMenu();
        intitBoottomNavigation();
    }


    int x = 1;

    void loadTop() {
        Api.getClient().GetTopMovie("63103295acf92a181b68289f20ca0e98", x).enqueue(new Callback<MovieModel>() {
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

    void loadPop() {
        Api.getClient().GetPopulearMovie("63103295acf92a181b68289f20ca0e98", x).enqueue(new Callback<MovieModel>() {
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

    void intitBoottomNavigation() {
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                a.movies.clear();
                a.notifyDataSetChanged();
                x = 1;
                if (item.getItemId() == R.id.Youtube) {
                    loadTop();
                } else if (item.getItemId() == R.id.FaceBook) {
                    load();
                } else {
                    loadPop();
                }
                //  Intent n = new Intent(Intent.ACTION_VIEW, Uri.parse(path));
                //   startActivity(n);

                return true;
            }
        });
    }

    private void initNavigationMenu() {
        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {
                a.movies.clear();
                a.notifyDataSetChanged();
                x = 1;
                if (item.getItemId() == R.id.Youtube) {
                    loadTop();
                } else if (item.getItemId() == R.id.FaceBook) {
                    load();
                } else {
                    loadPop();
                }
                drawer.closeDrawers();
                return true;
            }
        });

        // open drawer at start
        //    drawer.openDrawer(Gravity.START);
    }

    public void Clc(View view) {
        drawer.openDrawer(Gravity.START);
    }

    public void Clc2(View view) {
        drawer.closeDrawer(Gravity.START);
    }
}
