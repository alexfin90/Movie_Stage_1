package network;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.afinocchiaro.popularmovie.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import models.MyRepository;

/**
 * Created by afinocchiaro on 12/02/17.
 */

public final class NetworkUtils {

    private static final String BASE_URL_IMAGE = "http://image.tmdb.org/t/p/";

    private static final String BASE_URL_MOVIE = "http://api.themoviedb.org/3/";


    private static final String MOVIE = "movie";

    private static final String POPULAR = "popular";

    private static final String RATED = "top_rated";

    private static final String keyParam = "api_key";

    private static final String SIZE = "w342";

    private static final String SIZE_DETAIL = "w154";

    public static URL buildUriPosterPath(String poster_path, boolean size) {

        String x = BASE_URL_IMAGE;
        Uri builtUri;
        if (!size)
            builtUri = Uri.parse(BASE_URL_IMAGE).buildUpon().appendPath(SIZE).build();
        else
            builtUri = Uri.parse(BASE_URL_IMAGE).buildUpon().appendPath(SIZE_DETAIL).build();

        URL url = null;

        try {

            url = new URL(builtUri.toString() + poster_path);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }


    private static URL buildUriMostPopular(Context context) {

        String apiKey = context.getResources().getString(R.string.api_key_moviedb);

        Uri builtUri = Uri.parse(BASE_URL_MOVIE).buildUpon().appendPath(MOVIE).appendPath(POPULAR).appendQueryParameter(keyParam, apiKey).build();

        URL url = null;

        try {

            url = new URL(builtUri.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;


    }


    private static URL buidlUriTopRated(Context context) {

        String apiKey = context.getResources().getString(R.string.api_key_moviedb);

        Uri builtUri = Uri.parse(BASE_URL_MOVIE).buildUpon().appendPath(MOVIE).appendPath(RATED).appendQueryParameter(keyParam, apiKey).build();

        URL url = null;

        try {

            url = new URL(builtUri.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;

    }


    public static void getMostPopularMovie(final Context context, final CallBackUI callback) {

        String url = buildUriMostPopular(context).toString();
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                List<String> listPaths = new ArrayList<>();
                List<models.Movie> movies = new ArrayList<>();

                try {

                    JSONObject jsonObject = new JSONObject(response.toString());
                    List<String> titles = new ArrayList<>();
                    JSONArray array = jsonObject.getJSONArray("results");

                    for (int i = 0; i < array.length(); i++) {
                        listPaths.add(i, array.getJSONObject(i).getString("poster_path"));
                        titles.add(i,array.getJSONObject(i).getString("original_title"));
                        models.Movie movie = new models.Movie(array.getJSONObject(i).getString("original_title"),
                                array.getJSONObject(i).getString("poster_path"), array.getJSONObject(i).getString("overview"),
                                Float.valueOf(array.getJSONObject(i).getString("vote_average")),
                                array.getJSONObject(i).getString("release_date"));
                        movies.add(i, movie);

                    }

                    MyRepository.getInstance(context).insertMovies(movies);
                    callback.onSuccesgetMostPopularMovie(listPaths,titles);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();

            }
        });

        SingletonVolley.getInstance(context).addToRequestQueue(stringRequest);

    }


    public static void getTopRatedMovie(final Context context, final CallBackUI callback) {

        String url = buidlUriTopRated(context).toString();
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                List<String> listPaths = new ArrayList<>();
                List<String> titles = new ArrayList<>();
                List<models.Movie> movies = new ArrayList<>();

                try {

                    JSONObject jsonObject = new JSONObject(response.toString());

                    JSONArray array = jsonObject.getJSONArray("results");

                    for (int i = 0; i < array.length(); i++) {

                        listPaths.add(i, array.getJSONObject(i).getString("poster_path"));
                        titles.add(i,array.getJSONObject(i).getString("original_title"));

                        models.Movie movie = new models.Movie(array.getJSONObject(i).getString("original_title"),
                                array.getJSONObject(i).getString("poster_path"), array.getJSONObject(i).getString("overview"),
                                Float.valueOf(array.getJSONObject(i).getString("vote_average")),
                                array.getJSONObject(i).getString("release_date"));
                        movies.add(i, movie);

                        MyRepository.getInstance(context).insertMovies(movies);
                        callback.onSuccesgetTopRatedMovie(listPaths,titles);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

        SingletonVolley.getInstance(context).addToRequestQueue(stringRequest);
    }


}
