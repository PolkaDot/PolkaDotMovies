package com.polka.pdm;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class RatingsPage extends AppCompatActivity {

    float ratings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(RatingsPage.this, String.valueOf(rating), Toast.LENGTH_SHORT).show();
                ratings = rating;

            }
        });

    }

    /**
     * when you press submit, it should save the ratings for the movie
     * @param view of the viewAProfile Activity
     */
    public void onSubmitPress(View view) {
        Log.d("RatingsPage", "Submit Button Pressed");

        float rating  = ratings;

        // Insert user in database
        UserRepo repo = new UserRepo(this);

        // insert updated user info into database TODO: Check if user is already in database


        // Back to movie activity
        Intent intent = new Intent(this, Movie.class);
        startActivity(intent);
    }

}
