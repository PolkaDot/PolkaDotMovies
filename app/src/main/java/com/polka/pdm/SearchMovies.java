/**
 * Search Activity Class
 * for When the user wants to search for movies
 */
package com.polka.pdm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

public class SearchMovies extends AppCompatActivity {
    // Size of response array
    protected String[] mDataset;
    private static final int DATASET_COUNT = 50;

    // Needed for recycler view
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    // search param
    EditText editTextSearchParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // BEGIN_INCLUDE (initializeRecyclerView)
        mRecyclerView = (RecyclerView) findViewById(R.id.moviesRecylerView);

        // improves performance if you know that changes in content do not change the layout size
        // of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // user linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

    }

    /**
     * indicates that the user wants to search the movie typed in the editText
     *
     * @param view of the search Movies activity
     */
    public void onSearchButtonPress(View view) {
        editTextSearchParam = (EditText) findViewById(R.id.editText);
        String searchParam =  editTextSearchParam.getText().toString();
        sendJSONRequest(searchParam);

    }

    /**
     * Sends a JSON request to the Rotten Tomato API using the search parameter
     * specified by the user
     * @param searchParam param used to search for movie
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
                        // notify adapter that data has updated
                        ((MyAdapter)mAdapter).setData(mDataset);
                        mAdapter.notifyDataSetChanged();
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
     * @param response response from the Rotten Tomatoes API
     * @return array of 10 movie titles that match search
     */
    private String[] parseJSONObject(JSONObject response) {
        if (response == null || response.length() == 0) {
            return null;
        }
        try {
            String[] data = new String[DATASET_COUNT];
            JSONArray arrayMovies = response.getJSONArray(Keys.KEY_MOVIE);
            for (int i = 0; i < arrayMovies.length() && i < DATASET_COUNT; i++) {
                JSONObject currentMovie = arrayMovies.getJSONObject(i);
                String name = currentMovie.getString(Keys.KEY_TITLE);
                data[i] = name;
            }
            return data;
        } catch (JSONException e) {
            System.out.println("JSON Exception Exception");
        }
        return null;
    }
}
