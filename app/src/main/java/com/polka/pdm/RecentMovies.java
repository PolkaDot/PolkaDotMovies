package com.polka.pdm;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Esha
 * Shows the recent movies that have been released
 */
public class RecentMovies extends NavBar {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_movies);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // deals with the navigation bar
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        drawerToggle = setupDrawerToggle();
        mDrawer.setDrawerListener(drawerToggle);

        // Grab data about user from extras
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                user = null;
            } else {
                user = extras.getParcelable("user");
            }
        } else {
            user = savedInstanceState.getParcelable("user");
        }

        super.setUser(user);

        sendJsonRequest();
    }

    private void sendJsonRequest() {
        String url = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey=yedukp76ffytfuy24zsqk7f5";


        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, (String)null, new Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        parseJSONObject(response);
                    }
                }, new ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                }
                );
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);

    }


    /**
     * parses the JSON call to retrieve the first 10 recent movies
     * from Rotten Tomatoes API
     *
     * @param response retrieved from the JSON call to Rotten Tomatoes
     */
    private void parseJSONObject(JSONObject response) {
        if (response == null || response.length() == 0) {
            return;
        }

        try {
            TextView  movie_names = (TextView)findViewById(R.id.RecentMoviesTextView);
            StringBuilder data = new StringBuilder();
            JSONArray arrayMovies = response.getJSONArray(Keys.KEY_MOVIE);
            for (int i = 0; i < arrayMovies.length() && i < 10; i++) {

                JSONObject currentMovie = arrayMovies.getJSONObject(i);
                String name = currentMovie.getString(Keys.KEY_TITLE);
                int num = i +1;
                data.append(num + " " + name + "\n");
            }
            movie_names.setText(data);
        } catch (JSONException e) {
            System.out.println("JSON Exception Exception");
        }

    }


}
