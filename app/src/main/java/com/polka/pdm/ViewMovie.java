package com.polka.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

/**
 * Activity when viewing a movie any movie
 * @author Esha Singh
 * @version 1.0
 */
public class ViewMovie extends NavBar {

    private Movie movie;//the movie that the user has selected

    //private ImageView poster;
    private User user;//the user that wants to view the movie

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
        movieYearTextView.setText(String.valueOf(movie.getYear()));
        movieSynopsisTextView.setText(movie.getSynopsis());
    }

//    /**
//     * when you click on a menu item
//     * this method is responsible
//     * for directing you to the correct
//     * new activity
//     * and changing the appearances and stuff
//     * (like highlighting your selection)
//     *
//     * @param menuItem the item that you pressed
//     */
//    public void selectDrawerItem(MenuItem menuItem) {
//        Intent intent;
//        switch (menuItem.getItemId()) {
//            case R.id.ViewProfile:
//                intent = new Intent(this, ViewProfile.class);
//                break;
//            case R.id.SearchMovies:
//                intent = new Intent(this, SearchMovies.class);
//                break;
//            case R.id.Movies:
//                intent = new Intent(this, RecentMovies.class);
//                break;
//            case R.id.DVDs:
//                intent = new Intent(this, RecentDvds.class);
//                break;
//            case R.id.Recommendations:
//                intent = new Intent(this, MainActivity.class);
//                break;
//            case R.id.LogOut:
//                intent = new Intent(this, MainActivity.class);
//                break;
//            default:
//                intent = new Intent(this, ViewProfile.class);
//        }
//
//        // Highlight the selected item
//        menuItem.setChecked(true);
//        // update the title
//        setTitle(menuItem.getTitle());
//        // close the drawer
//        mDrawer.closeDrawers();
//        startActivity(intent);
//    }
//
//    /**
//     * Allows us to change the state of the toolbar whenever we change config
//     *
//     * @param newConfig the new configuration
//     */
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        drawerToggle.onConfigurationChanged(newConfig);
//    }

    public void onRateMoviePress(View view) { //we need the view in the method header
        Intent intent = new Intent(this, ReviewPage.class);
        intent.putExtra("movie", movie);
        intent.putExtra("user", user);

        startActivity(intent);
    }
}

