package com.example.afinocchiaro.popularmovie;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import adapters.GridViewAdapter;
import network.ImplCallBackUI;
import network.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridViewAdapter adapter;
    private List<String> listTitles;
    private Toolbar toolbar;
    private CollapsingToolbarLayout toolbarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.rv_movies);
        recyclerView.setHasFixedSize(true);
        //set GridLayoutManager
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new GridViewAdapter(this);
        recyclerView.setAdapter(adapter);
        loadMostPopular();

    }

    private void loadMostPopular() {


        toolbarLayout.setTitle(getResources().getString(R.string.most_popular));


        NetworkUtils.getMostPopularMovie(getApplicationContext(), new ImplCallBackUI() {

            @Override
            public void onSuccesgetMostPopularMovie(List<String> list, List<String> titles) {
                adapter.setImagesUrl(list);
                listTitles = titles;
            }

            @Override
            public String onErrorgetMostPopularMovie() {
                return null;
            }
        });


    }


    private void loadTopRated() {

        toolbarLayout.setTitle(getResources().getString(R.string.high_rated));

        NetworkUtils.getTopRatedMovie(getApplicationContext(), new ImplCallBackUI() {


            @Override
            public void onSuccesgetTopRatedMovie(List<String> list, List<String> titles) {
                adapter.setImagesUrl(list);
                setTitle("Top Rated");
                listTitles = titles;
            }

            @Override
            public String onErrorgetTopRatedMovie() {
                return super.onErrorgetTopRatedMovie();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {

            case R.id.i_most_popular:
                loadMostPopular();
                break;

            case R.id.i_high_rated:
                loadTopRated();
                break;

            default:

                return true;


        }
        return super.onOptionsItemSelected(item);
    }


    public List<String> getListTitles() {
        return listTitles;
    }

    public void setListTitles(List<String> listTitles) {
        this.listTitles = listTitles;
    }

}
