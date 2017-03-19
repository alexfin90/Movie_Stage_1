package network;

import java.util.List;

/**
 * Created by afinocchiaro on 13/02/17.
 */

public interface CallBackUI {

    public void onSuccesgetMostPopularMovie(List<String> list,List<String> titles);

    public String onErrorgetMostPopularMovie();

    public void onSuccesgetTopRatedMovie(List<String> list, List<String> listTitles);

    public String onErrorgetTopRatedMovie();


}
