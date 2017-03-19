package com.example.afinocchiaro.popularmovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URL;

import models.Movie;
import models.MyRepository;
import network.NetworkUtils;

/**
 * Created by afinocchiaro on 04/03/17.
 */

public class DetailsScreenActivity extends AppCompatActivity {


    Movie movie;
    ImageView imageView;
    TextView desc, title_film, vote_average, release_date, vote_text, date_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        imageView = (ImageView) findViewById(R.id.iv_film);
        desc = (TextView) findViewById(R.id.tv_desc);
        title_film = (TextView) findViewById(R.id.title);
        vote_average = (TextView) findViewById(R.id.tv_vote);
        release_date = (TextView) findViewById(R.id.tv_date);
        vote_text = (TextView) findViewById(R.id.tv_vote_desc);
        date_text = (TextView) findViewById(R.id.tv_date_desc);
        String title = (String) getIntent().getSerializableExtra("title");
        movie = MyRepository.getInstance(getApplicationContext()).getMoviebyTitle(title);
        desc.setText(movie.getOverview());
        title_film.setText(title);
        URL url = NetworkUtils.buildUriPosterPath(movie.getLink_image(), true);
        Picasso.with(this).load(url.toString()).into(imageView);
        vote_average.append(String.valueOf("\n" + movie.getVote_average()));
        release_date.append(String.valueOf("\n" + movie.getRelease_date()));
        vote_text.setText(String.valueOf(movie.getVote_average()));
        date_text.setText(String.valueOf(movie.getRelease_date()));

    }
}
