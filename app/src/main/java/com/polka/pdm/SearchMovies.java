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
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class SearchMovies extends AppCompatActivity {
    // to test response
    private String response;
    // Needed for recycler view
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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

        // specify an adapter

    }

    /**
     * indicates that the user wants to search the movie typed in the editText
     *
     * @param view of the search Movies activity
     */
    public void onSearchButtonPress(View view) {
        Log.d("SearchMovies", "search button pressed");

        String apiKey = "yedukp76ffytfuy24zsqk7f5";
        String baseUrl = "http://api.rottentomatoes.com/api/public/v1.0/";
        String searchUrl = "movies.json?apikey=" + apiKey + "&q=Jack&page_limit=1";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, baseUrl + searchUrl, (String)null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject resp) {
                        //handle a valid response coming back.  Getting this string mainly for debug
                        response = resp.toString();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        response = "JSon Request Failed!!";
                    }
                });

        // Access the RequestQueue through your singleton class.
        Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }

}
