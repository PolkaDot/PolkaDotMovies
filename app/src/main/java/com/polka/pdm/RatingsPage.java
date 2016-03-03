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
    boolean hasRated;
    ReviewAndRate ratingObj;

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
                hasRated = true;
            }
        });
    }

    /**
     * when you press submit, it should save the ratings for the movie
     * @param view of the viewAProfile Activity
     */
    public void onSubmitPress(View view) {
        EditText comment = (EditText) findViewById(R.id.reviewEditText);
        String comments = comment.getText().toString();

        Movie movie;
        int movieYear;
        User user;

        if (ratings == 0 && comment.length() == 0) {
            Toast.makeText(this, "Please enter either a review or rating before submitting!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insert user in database
        RatingRepo ratingRepo = new RatingRepo(this);
        if (hasRated){
            ratingObj = new ReviewAndRate("user", "movie", 11, ratings, comments);

        }else {
            ratingObj = new ReviewAndRate("user", "movie", 11, comments);
        }


        // insert user into database TODO: Check if user is already in database
        if (true) {//check if rating already exists: ratingRepo.getRatingsByMovie(ratingObj.movie).movie == null
            ratingRepo.insert(ratingObj);
            Toast.makeText(this, "Thanks for the rating!", Toast.LENGTH_SHORT).show();
            // Switch to Edit Profile Activity
            Intent intent = new Intent(this, HomeApp.class);
            // intent.putExtra("user", ratingObj);//not sure what this does
            startActivity(intent);
        } else {
            Toast.makeText(this, "Already rated.", Toast.LENGTH_SHORT).show();
        }
    }

}
