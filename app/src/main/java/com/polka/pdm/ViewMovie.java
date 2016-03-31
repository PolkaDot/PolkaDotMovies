package com.polka.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewMovie extends NavBar {
    private Movie movie;

    private ImageView poster;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_movie);

        // Grab saved data about Movie
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                movie = null;
            } else {
                movie = extras.getParcelable("movie");
            }
        } else {
            movie = savedInstanceState.getParcelable("movie");
        }

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

        // Get TextViews on view profile page
        TextView movieNameTextView = (TextView) findViewById(R.id.MovieName);
        TextView movieYearTextView = (TextView) findViewById(R.id.MovieYear);
        TextView movieSynopsisTextView = (TextView) findViewById(R.id.MovieSynopsis);
        movieSynopsisTextView.setMovementMethod(new ScrollingMovementMethod());


         //Put user information in TextView boxes
        movieNameTextView.setText(movie.getTitle());
        movieYearTextView.setText(Integer.toString(movie.getYear()));
        movieSynopsisTextView.setText(movie.getSynopsis());


    }


    public void onRateMoviePress(View view) {
        Log.d("Rate Movie", "Rate Movie Button Pressed");
        Intent intent = new Intent(this, ReviewPage.class);
        intent.putExtra("movie", movie);
        intent.putExtra("user", user);

        startActivity(intent);
    }
}

