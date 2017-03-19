package models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by afinocchiaro on 04/03/17.
 */

public class Movie extends RealmObject {

    public Movie() {
    }

    @PrimaryKey
    private String original_title;

    private String link_image;

    private String overview;

    private float vote_average;

    private String release_date;

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getLink_image() {
        return link_image;
    }

    public void setLink_image(String link_image) {
        this.link_image = link_image;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Movie(String original_title, String link_image, String overview, float vote_averege, String release_date) {
        this.original_title = original_title;
        this.link_image = link_image;
        this.overview = overview;
        this.vote_average = vote_averege;
        this.release_date = release_date;
    }
}


