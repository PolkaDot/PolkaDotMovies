package com.polka.pdm;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Request for volley
 * Singleton class to facilitate requests (to Rotten Tomatoes, for instance)
 * Makes a single instance of the RequestQueue that will last the lifetime of the app
 *
 * @author Y. Avila-Stanley
 * @version 1.0
 */
public final class MySingleton {
    /**
     * Used for the single instance of MySingleton
     */
    private static MySingleton mInstance;
    /**
     * Will be used as single request queue for their entire app
     */
    private RequestQueue mRequestQueue;
    /**
     * specifies the context to be used
     */
    private static Context mCtx;

    /**
     * constructor for singleton class using current context
     * @param context context used to create an instance of singleton
     */
    private MySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

    }

    /**
     * Uses the current context to create a MySingleton instance
     * @param context the context to be used to create the singleton instance
     * @return an instance of MySingleton
     */
    public static synchronized MySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    /**
     * Returns the RequestQueue
     * @return the RequestQueue
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
     * Adds a request to the RequestQueue
     * @param req request to be added to the requestQueue
     * @param <T> of type <T>
     */
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
