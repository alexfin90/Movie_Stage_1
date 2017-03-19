package models;

import android.content.Context;

import java.util.List;

import io.realm.Realm;

/**
 * Created by afinocchiaro on 04/03/17.
 */

public class MyRepository implements Repository {

    private static MyRepository myInstance;
    private static Context mCtx;


    private MyRepository(Context mCtx) {
        this.mCtx = mCtx;
    }

    public static synchronized MyRepository getInstance(Context context) {
        if (myInstance == null) {
            myInstance = new MyRepository(context);
        }
        return myInstance;
    }

    @Override
    public void insertMovies(final List<Movie> movies) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                  List<Movie> movieList = realm.copyToRealmOrUpdate(movies);
                    movieList.size();
                }
            });
        } finally {
            realm.close();
        }
    }


    @Override
    public Movie getMoviebyTitle(String title) {

        Movie movie = null;
        Realm realm = Realm.getDefaultInstance();

        try {
            realm.beginTransaction();
            movie = realm.where(Movie.class)
                    .equalTo("original_title", title).findFirst();
            realm.commitTransaction();
        } finally {
            //realm.close();
        }
        return movie;

    }
}
