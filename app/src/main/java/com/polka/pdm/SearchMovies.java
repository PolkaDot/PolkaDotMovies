/**
 * Search Activity Class
 * for When the user wants to search for movies
 *
 * @author Yamilex
 * @version 1.0
 */
package com.polka.pdm;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SearchMovies extends NavBar {
    // Size of response array
    protected Movie[] mDataset;
    private static final int DATASET_COUNT = 30;

    // Needed for recycler view
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    // search param
    EditText editTextSearchParam;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        setToolbar((Toolbar) findViewById(R.id.toolbar));

        setSupportActionBar(getToolbar());

        setMDrawer((DrawerLayout) findViewById(R.id.drawer_layout));
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        setDrawerToggle(setupDrawerToggle());
        getMDrawer().setDrawerListener(getDrawerToggle());

        // BEGIN_INCLUDE (initializeRecyclerView)
        mRecyclerView = (RecyclerView) findViewById(R.id.moviesRecylerView);
        //
//        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
//        setupDrawerContent(nvDrawer);

        // improves performance if you know that changes in content do not change the layout size
        // of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // user linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

//        //toggle for nav bar
//        drawerToggle = setupDrawerToggle();
//        mDrawer.setDrawerListener(drawerToggle);

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
    }

    /**
     * Indicates that the user wants to search the movie typed in the editText
     *
     * @param view of the search Movies activity
     */
    public void onSearchButtonPress(View view) {
        editTextSearchParam = (EditText) findViewById(R.id.searchMovie);
        String searchParam =  editTextSearchParam.getText().toString();
        sendJSONRequest(searchParam);

    }

    /**
     * Sends a JSON request to the Rotten Tomato API using the search parameter
     * specified by the user
     *
     * @param searchParam The parameter inputed by user to search for a movie
     */
    private void sendJSONRequest(String searchParam) {
        String apiKey = "yedukp76ffytfuy24zsqk7f5";
        String baseUrl = "http://api.rottentomatoes.com/api/public/v1.0/";
        String searchUrl = null;
        try {
            searchUrl = "movies.json?apikey=" + apiKey + "&q=" + URLEncoder.encode(searchParam, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // UTF-8 should always be supported; can safely ignore
            e.printStackTrace();
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, baseUrl + searchUrl, (String)null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject resp) {
                        //handle a valid response coming back.  Getting this string mainly for debug
                        mDataset = parseJSONObject(resp);
                        // update data in the adapter
                        ((MyAdapter)mAdapter).setData(mDataset);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // err
                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }


    /**
     * Parses the JSON object into movie titles. Returns an array of the
     * top 10 movie titles matching the search query.
     * @param response Response from the Rotten Tomatoes API
     * @return Array of 10 movie titles that match search
     */
    private Movie[] parseJSONObject(JSONObject response) {
        if (response == null || response.length() == 0) {
            return null;
        }
        try {
            Movie[] data = new Movie[DATASET_COUNT];
            JSONArray arrayMovies = response.getJSONArray("movies");
            for (int i = 0; i < arrayMovies.length() && i < DATASET_COUNT; i++) {
                JSONObject currentMovie = arrayMovies.getJSONObject(i);
                String title = currentMovie.getString(Keys.KEY_TITLE);
                int year = currentMovie.getInt("year");
                String synopsis = currentMovie.getString("synopsis");
                JSONObject posters = currentMovie.getJSONObject("posters");
                String poster;
                if (posters.isNull("thumbnail")) {
                    poster = null;
                } else {
                    poster = posters.getString("thumbnail");
                }
                data[i] = new Movie(title, year, synopsis, poster);
                Log.d("LLL", "Synopsis " + synopsis);

            }
            return data;
        } catch (JSONException e) {
            System.out.println("JSON Exception Exception");
        }
        return null;
    }

    /**
     * Returns the user from this activity. To be used in MyAdapter.
     * @return the user object
     */
    public User getUser() {
        return user;
    }
}
