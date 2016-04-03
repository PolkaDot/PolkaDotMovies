package com.polka.pdm;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * Get recent dvds
 * @author Alisha
 *
 * @version 1.0
 */

public class RecentDvds extends NavBar {

    /**
     * value that gives amount to be displayed
     */
    private static final int TOPTEN = 10;


//    private Toolbar toolbar;
//    private DrawerLayout mDrawer;
//    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        User user;
        super.onCreate(savedInstanceState);
        // getActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_recentdvds);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        super.setToolbar(toolbar);
        setSupportActionBar(toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        super.setMDrawer(mDrawer);
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);

        setupDrawerContent(nvDrawer);

        drawerToggle = setupDrawerToggle();
        mDrawer.setDrawerListener(drawerToggle);
//        super.setMDrawer(mDrawer);
        // Grab data about user from extras
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
        super.setUser(user);

        sendJsonRequest();
    }

    /**
     * Sends a JSON request to the Rotten Tomato API for a JSON object
     */
    private void sendJsonRequest() {
        final String url = "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?apikey=yedukp76ffytfuy24zsqk7f5";


        final JsonObjectRequest jsObjRequest =
            new JsonObjectRequest(Request.Method.GET, url, (String)null, new Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    parseJSONObject(response);
                }
            }, new ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {}
            }
            );
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);

    }

    /**
     * Parses the JSON object from the rotten tomato website
     * Sets text on the Recent DVDs page to the string that was parsed
     * @param response of the JSON object
     */
    private void parseJSONObject(JSONObject response) {
        if (response == null || response.length() == 0) {
            return;
        }

        try {
            final TextView  movienames = (TextView)findViewById(R.id.RecentDVDsTextView);
            final StringBuilder data = new StringBuilder();
            final JSONArray arrayMovies = response.getJSONArray(Keys.KEY_MOVIE);
            for (int i = 0; i < arrayMovies.length() && i < TOPTEN; i++) {

                final JSONObject currentMovie = arrayMovies.getJSONObject(i);
                final String name = currentMovie.getString(Keys.KEY_TITLE);
                final int num = i +1;
                data.append(num).append(" ").append(name).append("\n");
            }
            movienames.setText(data);
        } catch (JSONException e) {
//            System.out.println("JSON Exception Exception");
            Log.d("JSON", "JSON Exception Exception");
        }

    }
//
//
//    //
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
//
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
//     * (like highlighing your selection)
//     * @param menuItem the item that you pressed
//     */
//    public void selectDrawerItem(MenuItem menuItem) {
//        //create new frag
//        //determine what to show
////        Fragment fragment = null;
////        Class fragmentClass;
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
//                intent = new Intent(this, MainActivity.class);
////                fragmentClass = Frag.class;
//                break;
//            case R.id.LogOut:
////                fragmentClass = Frag.class;
////                Log.d("HomeApp","logging out");
//                intent = new Intent(this, MainActivity.class);
//                break;
//            default:
////                fragmentClass = Frag.class;
//                intent = new Intent(this, RecentDvds.class);
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


}
