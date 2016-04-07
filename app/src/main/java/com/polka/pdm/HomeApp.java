
/**
 * @author Christine Shih
 * @version 2.0
 * The start/home page of the application (after we have logged in)
 * Activity that starts after you log into the app
 * this is the right way to do javadoc
 */


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


    /**
     * user using app
     */
    private User user;

    /**
     * user string for extra
     */
    private final String userString = "user";
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
                user = extras.getParcelable(userString);
            }
        } else {
            user = savedInstanceState.getParcelable(userString);
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
        intent.putExtra(userString, user);
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


    /**
     * opens search button page
     *
     * @param view user interface component
     *             need view
     */
    public void onSearchButtonPress(View view) {  //we need the view in the method header
        Log.d("HomeApp", "Search Button Pressed");

        final Intent intent = new Intent(this, SearchMovies.class);
        intent.putExtra(userString, user);
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
        intent.putExtra(userString, user);
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

    //
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        //open close drawer
////        switch(item.getItemId()) {
////            case android.R.id.home:
////                mDrawer.openDrawer(GravityCompat.START);
////                return true;
////        }
////        return super.onOptionsItemSelected(item);
//        if (drawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    //
//    @Override
//    public void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        drawerToggle.syncState();
//    }
//
//    /**
//     * sets up listener for the side bar
//     * @param navigationView the side bar
//     */
//    private void setupDrawerContent(NavigationView navigationView) {
//        navigationView.setNavigationItemSelectedListener(
//                new NavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(MenuItem menuItem) {
//                        selectDrawerItem(menuItem);
//                        return true;
//                    }
//                }
//        );
//    }
//
//    /**
//     * when you click on a menu item
//     * this method is responsible
//     * for directing you to the correct
//     * new activity
//     * and changing the appearances and stuff
//     * (like highlighting your selection)
//     * @param menuItem the item that you pressed
//     */
//    public void selectDrawerItem(MenuItem menuItem) {
//        //create new frag
//        //determine what to show
//        Fragment fragment = null;
//        Class fragmentClass;
//        Intent intent;
////        Log.d("HomeApp","starting method");
//        switch(menuItem.getItemId()) {
//            case R.id.ViewProfile:
////                fragmentClass = Frag.class;
//                intent = new Intent(this, ViewProfile.class);
//                break;
//            case R.id.SearchMovies:
////                fragmentClass = Frag.class;
////                Log.d("HomeApp","searching movies");
//                intent = new Intent(this, SearchMovies.class);
////                Log.d("HomeApp","searched movies");
//                break;
//            case R.id.Movies:
//                intent = new Intent(this, RecentMovies.class);
////                fragmentClass = Frag.class;
//                break;
//            case R.id.DVDs:
//                intent = new Intent(this, RecentDvds.class);
////                fragmentClass = Frag.class;
//                break;
//            case R.id.Recommendations:
//                intent = new Intent(this, RecommendMovie.class);
////                fragmentClass = Frag.class;
//                break;
//            case R.id.LogOut:
////                fragmentClass = Frag.class;
////                Log.d("HomeApp","logging out");
//                intent = new Intent(this, MainActivity.class);
//                break;
////            case R.id.Cancel:
//////                fragmentClass = Frag.class;
//////                Log.d("HomeApp","logging out");
////                intent = new Intent(this, HomeApp.class);
////                break;
//            default:
////                fragmentClass = Frag.class;
//                intent = new Intent(this, HomeApp.class);
//        }
////        try {
////            fragment = (Fragment) fragmentClass.newInstance();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        //insert frag by replacing existing frag
////        FragmentManager fragmentManager = getSupportFragmentManager();
////        fragmentManager.beginTransaction().replace(R.id.flContent,fragment).commit();
//
//        // Highlight the selected item
//        menuItem.setChecked(true);
////        Log.d("HomeApp", "checking item");
//        // update the title
//        setTitle(menuItem.getTitle());
////        Log.d("HomeApp", "creating title");
//        // close the drawer
//        mDrawer.closeDrawers();
//        intent.putExtra("user", user);
//        startActivity(intent);
//
//    }
//
//    /**
//     * allows us to create a new ActionBarDrawerToggle specific to our needs
//     * @return a new ActionBarDrawer Toggle
//     */
//    private ActionBarDrawerToggle setupDrawerToggle() {
//        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
//    }
//
//    /**
//     * allows us to change the state of the toolbar whenever we change config
//     * @param newConfig the new configuration
//     */
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        drawerToggle.onConfigurationChanged(newConfig);
//    }

