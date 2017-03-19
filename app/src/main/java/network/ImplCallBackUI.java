package network;

import java.util.List;

/**
 * Created by afinocchiaro on 19/02/17.
 */

public  class ImplCallBackUI implements CallBackUI {

    @Override
    public void onSuccesgetMostPopularMovie(List<String> list, List<String> titles) {

    }

    @Override
    public String onErrorgetMostPopularMovie() {
        return null;
    }

    @Override
    public void onSuccesgetTopRatedMovie(List<String> list, List<String> titles) {

    }

    @Override
    public String onErrorgetTopRatedMovie() {
        return null;
    }
}
