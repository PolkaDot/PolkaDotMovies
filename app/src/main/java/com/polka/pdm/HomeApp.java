
/**
 * @author Christine Shih
 * @version 2.0
 * The start/home page of the application (after we have logged in)
 * Activity that starts after you log into the app
 * this is the right way to do javadoc
 */


package com.polka.pdm;

import android.content.Intent;
import android.content.SharedPreferences;
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


    /**
     * user using app
     */
    private User user;

    /**
     * user string for extra
     */

    private static final String USERSTRING = "user";
    //when we create this activity, there are some things we need to do first
    // hence the name on create
    //we totally need this
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);

        setToolbar((Toolbar) findViewById(R.id.toolbar));

        setSupportActionBar(getToolbar());

        setMDrawer((DrawerLayout) findViewById(R.id.drawer_layout));
        final NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        setDrawerToggle(setupDrawerToggle());
        getMDrawer().setDrawerListener(getDrawerToggle());

        // Grab data about user from extras
        if (savedInstanceState == null) {
            final Bundle extras = getIntent().getExtras();
            if (extras == null) {
                user = null;
            } else {
                user = extras.getParcelable(USERSTRING);
            }
        } else {
            user = savedInstanceState.getParcelable(USERSTRING);
        }

        super.setUser(user);
    }

    /**
     * if you press log out, it will take you to the main activity screen
     * future reference, go to layout, xml add button and set its on click
     * to the method in this case logoutClickListener
     *
     * @param view it is being used on
     *             view is def used
     */
    public void logoutClickListener(View view) {  //we need the view in the method header
        Log.d("Logout", "Logout Button Pressed");

        //sharedprefence for autologin
        SharedPreferences settings = getSharedPreferences("Auto login", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove("loggedIn");
        editor.commit();
        finish();

        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * when you click view profile, it takes you to the view profile activity
     *
     * @param view that you're looking at.
     *             view is def used
     */
    public void onViewProfileClick(View view) { //we need the view in the method header
        Log.d("ViewProfile", "View Profile Button Pressed");

        final Intent intent = new Intent(this, ViewProfile.class);
        intent.putExtra(USERSTRING, user);
        startActivity(intent);
    }

    /**
     * Opens the Recent DVDs page
     *
     * @param view user interface component
     */
    public void onDVDsButtonClick(View view) {  //we need the view in the method header
        final Intent intent = new Intent(this, RecentDvds.class);
        startActivity(intent);
    }

    /*


    /**
     * opens search button page
     *
     * @param view user interface component
     *             need view
     */
    public void onSearchButtonPress(View view) {  //we need the view in the method header
        Log.d("HomeApp", "Search Button Pressed");

        final Intent intent = new Intent(this, SearchMovies.class);
        intent.putExtra(USERSTRING, user);
        startActivity(intent);

    }


    /**
     * Switches to recent movies page when the recent
     * button is clicked
     *
     * @param view of the page
     *             need view for method to work
     */
    public void onMoviesButtonClick(View view) {  //we need the view in the method header
        final Intent intent = new Intent(this, RecentMovies.class);
        intent.putExtra(USERSTRING, user);
        startActivity(intent);
    }

    /**
     * Switches to recommend movie page on recommendation button click
     *
     * @param view of the page
     *             just need view for method to work okay?
     */
    public void onRecommendationButtonClick(View view) {  //we need the view in the method header
        final Intent intent = new Intent(this, RecommendMovie.class);
        startActivity(intent);
    }
}


