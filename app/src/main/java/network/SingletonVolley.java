package network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by afinocchiaro on 12/02/17.
 *
 * Singleton Class for manage network request
 */

public class SingletonVolley {

    private static SingletonVolley myInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private SingletonVolley(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized SingletonVolley getInstance(Context context) {
        if (myInstance == null) {
            myInstance = new SingletonVolley(context);
        }
        return myInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}