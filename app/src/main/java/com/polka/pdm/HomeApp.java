package com.polka.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

/**
 * @author Christine Shih
 * @version 2.0
 * The start/home page of the application (after we have logged in)
 * Activity that starts after you log into the app
 */
public class HomeApp extends NavBar {

    private User user;

    //when we create this activity, there are some things we need to do first
    // hence the name on create
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        drawerToggle = setupDrawerToggle();
        mDrawer.setDrawerListener(drawerToggle);

        // Grab data about user from extras
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

        super.setUser(user);
    }

    /**
     *     if you press log out, it will take you to the main activity screen
     *     future reference, go to layout, xml add button and set its on click
     *     to the method in this case logoutClickListener
     *
     * @param view it is being used on
     */
    public void logoutClickListener(View view) {
        Log.d("Logout", "Logout Button Pressed");

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
    * when you click view profile, it takes you to the view profile activity
    * @param view that you're looking at.
     */
    public void onViewProfileClick(View view) {
        Log.d("ViewProfile", "View Profile Button Pressed");

        Intent intent = new Intent(this, ViewProfile.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    /*
     * Opens the Recent DVDs page
     *
     * @param view user interface component
     */
    public void onDVDsButtonClick(View view) {
        Intent intent = new Intent(this, RecentDvds.class);
        startActivity(intent);
    }


    /**
     * opens search button page
     *
     * @param view user interface component
     */
    public void onSearchButtonPress(View view) {
        Log.d("HomeApp", "Search Button Pressed");

        Intent intent = new Intent(this, SearchMovies.class);
        intent.putExtra("user", user);
        startActivity(intent);

    }


    /**
     * Switches to recent movies page when the recent
     * button is clicked
     *
     * @param view of the page
     */
    public void onMoviesButtonClick(View view) {
        Intent intent = new Intent(this, RecentMovies.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    /**
     * Switches to recommend movie page on recommendation button click
     *
     * @param view of the page
     */
    public void onRecommendationButtonClick(View view) {
        Intent intent = new Intent(this, RecommendMovie.class);
        startActivity(intent);
    }
}
