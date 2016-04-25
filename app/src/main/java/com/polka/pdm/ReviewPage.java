
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

    /**
     * ratings
     */
    private float ratings;
    /**
     * has rated boolean
     */
    private boolean hasRated;
    /**
     * rating object
     */
    private Review ratingObj;
    /**
     * movie
     */
    private Movie movie;
    /**
     * movie's name
     */
    private String movieName;
    /**
     * year movie was made
     */
    private int movieYear;
    /**
     * user using app
     */
    private User user;
    /**
     * user's major
     */
    private String major;
    /**
     * user's username
     */
    private String username;

    /**
     * Gets an object of the Review class
     *
     * @return Object of review class
     */
    public Review getRatingObj() { //want to keep getter in case we use it in the future
        return ratingObj;
    }

    /**
     * Sets an object of the Review class
     *
     * @param ratingObjs of review class
     */
    public void setRatingObj(Review ratingObjs) { //want to keep setter
        this.ratingObj = ratingObjs;
    }

    /**
     * Gets the year the movie was released
     *
     * @return The year the movie was released
     */
    public int getMovieYear() { //want to keep getter
        return movieYear;
    }

    /**
     * Sets the year the movie was made.
     *
     * @param movieYears the year the movie was made
     */
    public void setMovieYear(int movieYears) {
        this.movieYear = movieYears;
    }

    /**
     * Gets the name of the movie
     *
     * @return The name of the movie
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * Sets the name of the movie
     *
     * @param movieNames The name of the movie
     */
    public void setMovieName(String movieNames) {
        this.movieName = movieNames;
    }

    /**
     * Gets the major of the user
     *
     * @return The major of the user
     */
    public String getMajor() {
        return major;
    }


    /**
     * Sets the major of the user
     *
     * @param majors The major of the user
     */
    public void setMajor(String majors) {
        this.major = majors;
    }

    /**
     * Gets the rated or not rated status of the user
     *
     * @return True if the user has rated the movie and false if they haven't
     */
    public boolean isHasRated() {
        return hasRated;
    }


    /**
     * Sets to true if the user has rated the movie false otherwise
     *
     * @param hasRateds True if the user has rated the movie false otherwise
     */
    public void setHasRated(boolean hasRateds) {
        this.hasRated = hasRateds;
    }

    /**
     * Gets the movie object
     *
     * @return The movie object
     */
    public Movie getMovie() {
        return movie;
    }


    /**
     * Sets the movie object
     *
     * @param movies the movie object
     */
    public void setMovie(Movie movies) {
        this.movie = movies;
    }

    /**
     * Gets the rating of the movie
     *
     * @return the rating of the movie
     */
    public float getRatings() {
        return ratings;
    }


    /**
     * sets the rating of the movie
     *
     * @param rating of the movie
     */
    public void setRatings(float rating) {
        this.ratings = rating;
    }

    /**
     * Gets the user object
     *
     * @return The user object
     */
    public User getUser() {
        return user;
    }


    /**
     * sets the user object
     *
     * @param users the user object
     */
    public void setUser(User users) {
        this.user = users;
    }

    /**
     * Gets the username
     *
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * sets the username
     *
     * @param usernames the username
     */
    public void setUsername(String usernames) {
        this.username = usernames;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //using protected gives one error not using protected gives us another error
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_page);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(ReviewPage.this, String.valueOf(rating), Toast.LENGTH_SHORT).show();
                ratings = rating;
                hasRated = true;
            }
        });

        if (savedInstanceState == null) {
            final Bundle extras = getIntent().getExtras();
            if (extras == null) {
                movie = null;
            } else {
                movie = extras.getParcelable("movie");
            }
        } else {
            movie = savedInstanceState.getParcelable("movie");
        }

        if (savedInstanceState == null) {
            final Bundle extras = getIntent().getExtras();
            if (extras == null) {
                user = null;
            } else {
                user = extras.getParcelable("user");
            }
        } else {
            user = savedInstanceState.getParcelable("user");
        }
        if (movie != null) {
            movieName = movie.getTitle();
            movieYear = movie.getYear();
            final TextView ratingTextViews = (TextView) findViewById(R.id.ratingTextView);
            ratingTextViews.setText(movieName);
            username = user.getUsername();
            major = user.getMajor();
        }
    }

    /**
     * When you press submit, it should save the ratings for the movie
     *
     * @param view of the viewAProfile Activity
     */
    public void onSubmitPress(View view) {  //we need the view in the method header
        final EditText comment = (EditText) findViewById(R.id.reviewEditText);
        final String comments = comment.getText().toString();

        if (ratingInfoNotEntered(comments, ratings)) {
            Toast.makeText(this, "Please enter either a review or rating before submitting!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insert user in database
        final ReviewRepo ratingRepo = new ReviewRepo(this);
        if (hasRated) {
            ratingObj = new Review(username, major, movieName, movieYear, ratings, comments);

        } else {
            ratingObj = new Review(username, major, movieName, movieYear, comments);
        }

        // insert user already rated the movie
        final long check = ratingRepo.insert(ratingObj);
        if (check == -1) {
            Toast.makeText(this, "Updated your previous review :D", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "Thanks for the rating!", Toast.LENGTH_SHORT).show();

        // Switch to Edit Profile Activity
        final Intent intent = new Intent(this, HomeApp.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    /**
     * method to check if rating info is entered or not
     *
     * @param comments comments made
     * @param num number
     * @return if comments made
     */
    boolean ratingInfoNotEntered(String comments, float num) {
        // so junit can use
        if (num == 0 && comments.length() == 0) {
            return true;
        }
            return false;
//        return num == 0 && comments.length() == 0;
    }
}
