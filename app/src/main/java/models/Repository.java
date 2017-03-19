package models;

import java.util.List;

/**
 * Created by afinocchiaro on 04/03/17.
 */

public interface Repository {

      void insertMovies(List<Movie> movies);
      Movie getMoviebyTitle(String title);

}
