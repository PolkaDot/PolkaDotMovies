package com.polka.pdm;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewMovie extends AppCompatActivity {
    private Movie movie;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private ImageView poster;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        drawerToggle = setupDrawerToggle();
        mDrawer.setDrawerListener(drawerToggle);

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

    /**
     * sets up listener for the side bar
     *
     * @param navigationView the side bar
     */
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                }
        );
    }

    /**
     * when you click on a menu item
     * this method is responsible
     * for directing you to the correct
     * new activity
     * and changing the appearances and stuff
     * (like highlighing your selection)
     *
     * @param menuItem the item that you pressed
     */
    public void selectDrawerItem(MenuItem menuItem) {
        //create new frag
        //determine what to show
        Fragment fragment = null;
        Class fragmentClass;
        Intent intent;
//        Log.d("HomeApp","starting method");
        switch (menuItem.getItemId()) {
            case R.id.ViewProfile:
//                fragmentClass = Frag.class;
                intent = new Intent(this, ViewProfile.class);
                break;
            case R.id.SearchMovies:
//                fragmentClass = Frag.class;
//                Log.d("HomeApp","searching movies");
                intent = new Intent(this, SearchMovies.class);
//                Log.d("HomeApp","searched movies");
                break;
            case R.id.Movies:
                intent = new Intent(this, RecentMovies.class);
//                fragmentClass = Frag.class;
                break;
            case R.id.DVDs:
                intent = new Intent(this, RecentDvds.class);
//                fragmentClass = Frag.class;
                break;
            case R.id.Recommendations:
                intent = new Intent(this, MainActivity.class);
                break;
            case R.id.LogOut:
                intent = new Intent(this, MainActivity.class);
                break;
            default:
                intent = new Intent(this, ViewProfile.class);
        }

        // Highlight the selected item
        menuItem.setChecked(true);
//        Log.d("HomeApp", "checking item");
        // update the title
        setTitle(menuItem.getTitle());
//        Log.d("HomeApp", "creating title");
        // close the drawer
        mDrawer.closeDrawers();
        //intent.putExtra("user", user);
        startActivity(intent);

    }


    /**
     * Allows us to create a new ActionBarDrawerToggle specific to our needs
     *
     * @return a new ActionBarDrawer Toggle
     */
    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    /**
     * Allows us to change the state of the toolbar whenever we change config
     *
     * @param newConfig the new configuration
     */
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public void onRateMoviePress(View view) {
        Intent intent = new Intent(this, ReviewPage.class);
        intent.putExtra("movie", movie);
        intent.putExtra("user", user);

        startActivity(intent);
    }
}

