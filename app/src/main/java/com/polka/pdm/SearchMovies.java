/**
 * Search Activity Class
 * for When the user wants to search for movies
 */
package com.polka.pdm;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class SearchMovies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    /**
     * indicates that the user wants to search the movie typed in the editText
     *
     * @param view of the search Movies activity
     */
    public void onSearchButtonPress(View view) {
        Log.d("SearchMovies", "search button pressed");


    }

}
