package com.polka.pdm;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Request for volley
 * @author Y. Avila-Stanley
 * @version 1.0
 */
public class MySingleton {
    private static MySingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    /**
     *
     * Constructor.
     * @param context ??????
     *
     */
    private MySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

    }

    /**
     *
     * ????????
     * @param context ??????
     *
     */
    public static synchronized MySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    /**
     *
     * ?????
     *
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    /**
     *
     * ??????
     * @param req ??????
     *
     */
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
