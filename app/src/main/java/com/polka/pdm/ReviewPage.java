/**
 * Review page
 * basically rating
 * but review is the right name
 */
package com.polka.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class ReviewPage extends AppCompatActivity {

    float ratings;
    boolean hasRated;
    Review ratingObj;
    Movie movie;
    String movieName;
    int movieYear ;
    User user;
    String major;
    String username;


//    public float getRatings() {
//        return this.ratings;
//    }
//
//    public boolean getHasRated() {
//        return this.hasRated;
//    }
//
//    public Review getRatingObj() {
//        return this.ratingObj;
//    }
//
//    public Movie getMovie() {
//        return this.movie;
//    }
//
//    public String getMovieName() {
//        return this.movieName;
//    }
//
//    public int getMovieYear() {
//        return this.movieYear;
//    }
//
//    public User getUser() {
//        return this.user;
//    }
//
//    public String getUsername() {
//        return this.username;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(ReviewPage.this, String.valueOf(rating), Toast.LENGTH_SHORT).show();
                ratings = rating;
                hasRated = true;
            }
        });

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

        movieName = movie.getTitle();
        movieYear = movie.getYear();
        TextView ratingTextViews = (TextView) findViewById(R.id.ratingTextView);
        ratingTextViews.setText(movieName);
        username = user.username;

    }

    /**
     * when you press submit, it should save the ratings for the movie
     * @param view of the viewAProfile Activity
     */
    public void onSubmitPress(View view) {
        EditText comment = (EditText) findViewById(R.id.reviewEditText);
        String comments = comment.getText().toString();

        if (ratings == 0 && comment.length() == 0) {
            Toast.makeText(this, "Please enter either a review or rating before submitting!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insert user in database
        ReviewRepo ratingRepo = new ReviewRepo(this);
        if (hasRated){
            ratingObj = new Review(username, major, movieName, movieYear, ratings, comments);

        } else {
            ratingObj = new Review(username, major, movieName, movieYear, comments);
        }


        // insert user already rated the movie
        if (true) {
            long check = ratingRepo.insert(ratingObj);
            if (check == -1) {
                Toast.makeText(this, "Updated your previous review :D", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Thanks for the rating!", Toast.LENGTH_SHORT).show();
            // Switch to Edit Profile Activity
            Intent intent = new Intent(this, HomeApp.class);
            intent.putExtra("user", user);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Already rated.", Toast.LENGTH_SHORT).show();
        }
    }

}
