
/**
 * Allows user to submit movie review
 *
 * @author Alisha
 * @version 1.0
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


/**
 * @author Alisha
 *
 * Review page activity so user can review a movie and comment on it
 */
public class ReviewPage extends AppCompatActivity {

    private float ratings;
    private boolean hasRated;
    private Review ratingObj;
    private Movie movie;
    private String movieName;
    private int movieYear ;
    private User user;
    private String major;
    private String username;

    /**
     * Gets an object of the Review class
     * @return Object of review class
     */
    public Review getRatingObj() {
        return ratingObj;
    }

    /**
     * Sets an object of the Review class
     * @param ratingObj of review class
     */
    public void setRatingObj(Review ratingObj) {
        this.ratingObj = ratingObj;
    }

    /**
     * Gets the year the movie was released
     * @return The year the movie was released
     */
    public int getMovieYear() {
        return movieYear;
    }

    /**
     * Sets the year the movie was made.
     * @param movieYear the year the movie was made
     */
    public void setMovieYear(int movieYear) {
        this.movieYear = movieYear;
    }

    /**
     * Gets the name of the movie
     * @return The name of the movie
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * Sets the name of the movie
     * @param movieName The name of the movie
     */
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    /**
     * Gets the major of the user
     * @return The major of the user
     */
    public String getMajor() {
        return major;
    }


    /**
     * Sets the major of the user
     * @param major The major of the user
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * Gets the rated or not rated status of the user
     * @return True if the user has rated the movie and false if they haven't
     */
    public boolean isHasRated() {
        return hasRated;
    }


    /**
     * Sets to true if the user has rated the movie false otherwise
     * @param hasRated True if the user has rated the movie false otherwise
     */
    public void setHasRated(boolean hasRated) {
        this.hasRated = hasRated;
    }

    /**
     * Gets the movie object
     * @return The movie object
     */
    public Movie getMovie() {
        return movie;
    }


    /**
     * Sets the movie object
     * @param movie the movie object
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * Gets the rating of the movie
     * @return the rating of the movie
     */
    public float getRatings() {
        return ratings;
    }


    /**
     * sets the rating of the movie
     * @param ratings of the movie
     */
    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    /**
     * Gets the user object
     * @return The user object
     */
    public User getUser() {
        return user;
    }


    /**
     * sets the user object
     * @param user the user object
     */
    public void setUser(User user) {
        this.user = user;
    }
    /**
     * Gets the username
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * sets the username
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }


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
        username = user.getUsername();
        major = user.getMajor();

    }

    /**
     * When you press submit, it should save the ratings for the movie
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
        long check = ratingRepo.insert(ratingObj);
        if (check == -1) {
            Toast.makeText(this, "Updated your previous review :D", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "Thanks for the rating!", Toast.LENGTH_SHORT).show();

        // Switch to Edit Profile Activity
        Intent intent = new Intent(this, HomeApp.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

}
