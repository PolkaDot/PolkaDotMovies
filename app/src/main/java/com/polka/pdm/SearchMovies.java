/**
 * Search Activity Class
 * for When the user wants to search for movies
 */
package com.polka.pdm;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SearchMovies extends AppCompatActivity {
    // Size of response array
    protected Movie[] mDataset;
    private static final int DATASET_COUNT = 30;

    // Needed for recycler view
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    // search param
    EditText editTextSearchParam;

    private User user;

    private Toolbar toolbar;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        // BEGIN_INCLUDE (initializeRecyclerView)
        mRecyclerView = (RecyclerView) findViewById(R.id.moviesRecylerView);

        // improves performance if you know that changes in content do not change the layout size
        // of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // user linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

        //
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        //toggle for nav bar
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

    }

    /**
     * indicates that the user wants to search the movie typed in the editText
     *
     * @param view of the search Movies activity
     */
    public void onSearchButtonPress(View view) {
        editTextSearchParam = (EditText) findViewById(R.id.searchMovie);
        String searchParam =  editTextSearchParam.getText().toString();
        sendJSONRequest(searchParam);
    }

    /**
     * Sends a JSON request to the Rotten Tomato API using the search parameter
     * specified by the user
     * @param searchParam param used to search for movie
     */
    private void sendJSONRequest(String searchParam) {
        String apiKey = "yedukp76ffytfuy24zsqk7f5";
        String baseUrl = "http://api.rottentomatoes.com/api/public/v1.0/";
        String searchUrl = null;
        try {
            searchUrl = "movies.json?apikey=" + apiKey + "&q=" + URLEncoder.encode(searchParam, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // UTF-8 should always be supported; can safely ignore
            e.printStackTrace();
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, baseUrl + searchUrl, (String)null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject resp) {
                        //handle a valid response coming back.  Getting this string mainly for debug
                        mDataset = parseJSONObject(resp);
                        // update data in the adapter
                        ((MyAdapter)mAdapter).setData(mDataset);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // err
                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }

    //NAVIGATION BAR STUFF

    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //open close drawer
//        switch(item.getItemId()) {
//            case android.R.id.home:
//                mDrawer.openDrawer(GravityCompat.START);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    /**
     * sets up listener for the side bar
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
     * @param menuItem the item that you pressed
     */
    public void selectDrawerItem(MenuItem menuItem) {
        //create new frag
        //determine what to show
//        Fragment fragment = null;
//        Class fragmentClass;
        Intent intent;
//        Log.d("HomeApp","starting method");
        switch(menuItem.getItemId()) {
            case R.id.ViewProfile:
//                fragmentClass = Frag.class;
                intent = new Intent(this, ViewProfile.class);
                intent.putExtra("user", user);
                break;
            case R.id.SearchMovies:
//                fragmentClass = Frag.class;
//                Log.d("HomeApp","searching movies");
                intent = new Intent(this, SearchMovies.class);
//                Log.d("HomeApp","searched movies");
                break;
            case R.id.Movies:
                intent = new Intent(this, MainActivity.class);
//                fragmentClass = Frag.class;
                break;
            case R.id.DVDs:
                intent = new Intent(this, RecentDvds.class);
//                fragmentClass = Frag.class;
                break;
            case R.id.Recommendations:
                intent = new Intent(this, MainActivity.class);
//                fragmentClass = Frag.class;
                break;
            case R.id.LogOut:
//                fragmentClass = Frag.class;
//                Log.d("HomeApp","logging out");
                intent = new Intent(this, MainActivity.class);
                break;
            default:
//                fragmentClass = Frag.class;
                intent = new Intent(this, SearchMovies.class);
        }
//        try {
//            fragment = (Fragment) fragmentClass.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        //insert frag by replacing existing frag
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.flContent,fragment).commit();

        // Highlight the selected item
        menuItem.setChecked(true);
//        Log.d("HomeApp", "checking item");
        // update the title
        setTitle(menuItem.getTitle());
//        Log.d("HomeApp", "creating title");
        // close the drawer
        mDrawer.closeDrawers();
        startActivity(intent);

    }

    /**
     * allows us to create a new ActionBarDrawerToggle specific to our needs
     * @return a new ActionBarDrawer Toggle
     */
    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    /**
     * allows us to change the state of the toolbar whenever we change config
     * @param newConfig the new configuration
     */
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    /**
     * Parses the JSON object into movie titles. Returns an array of the
     * top 10 movie titles matching the search query.
     * @param response response from the Rotten Tomatoes API
     * @return array of 10 movie titles that match search
     */
    private Movie[] parseJSONObject(JSONObject response) {
        if (response == null || response.length() == 0) {
            return null;
        }
        try {
            Movie[] data = new Movie[DATASET_COUNT];
            JSONArray arrayMovies = response.getJSONArray("movies");
            for (int i = 0; i < arrayMovies.length() && i < DATASET_COUNT; i++) {
                JSONObject currentMovie = arrayMovies.getJSONObject(i);
                String title = currentMovie.getString(Keys.KEY_TITLE);
                int year = currentMovie.getInt("year");
                String synopsis = currentMovie.getString("synopsis");
                JSONObject posters = currentMovie.getJSONObject("posters");
                String poster;
                if (posters.isNull("thumbnail")) {
                    poster = null;
                } else {
                    poster = posters.getString("thumbnail");
                }
                data[i] = new Movie(title, year, synopsis, poster);
            }
            return data;
        } catch (JSONException e) {
            System.out.println("JSON Exception Exception");
        }
        return null;
    }

    /**
     * Returns the user from this activity. To be used in MyAdapter.
     * @return the user object
     */
    public User getUser() {
        return user;
    }
}
