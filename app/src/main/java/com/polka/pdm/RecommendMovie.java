/**
 * Search Activity Class
 * for When the user wants to search for movies
 */
package com.polka.pdm;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class RecommendMovie extends NavBar {

    // Needed for recycler view
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private User user;

//    private Toolbar toolbar;
//    private DrawerLayout mDrawer;
//    private ActionBarDrawerToggle drawerToggle;
    protected Movie[] mDataset;
    private static final int DATASET_COUNT = 30;
    EditText editTextSearchParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_movie);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // BEGIN_INCLUDE (initializeRecyclerView)
        mRecyclerView = (RecyclerView) findViewById(R.id.moviesMajorRecylerView);
        //
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        // improves performance if you know that changes in content do not change the layout size
        // of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // user linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

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
        super.setUser(user);


    }

    /**
     * indicates that the user wants to search the major typed in the editText
     *
     * @param view of the search Movies activity
     */
    public void onSearchMajorButtonPress(View view) {
        editTextSearchParam = (EditText) findViewById(R.id.MajorTextField);
        String searchParam =  editTextSearchParam.getText().toString();

        ReviewRepo repo = new ReviewRepo(this);
        Movie[] movies = repo.getRatingsByMajor(searchParam, DATASET_COUNT);
        mDataset = movies;
        ((MyAdapter)mAdapter).setData(mDataset);


    }

    private void initDataset() {
        Movie[] data = new Movie[DATASET_COUNT];
        for (int i = 0; i < 10 && i < DATASET_COUNT; i++) {
            String title = "title";
            int year = 1996;
            String synopsis = "Blahhhhhh";
            String poster = "";
            data[i] = new Movie(title, year, synopsis, poster);
            mDataset = data;
        }
        ((MyAdapter)mAdapter).setData(mDataset);
    }

    //NAVIGATION BAR STUFF

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
//                intent = new Intent(this, RecommendMovie.class);
////                fragmentClass = Frag.class;
//                break;
//            case R.id.LogOut:
////                fragmentClass = Frag.class;
////                Log.d("HomeApp","logging out");
//                intent = new Intent(this, MainActivity.class);
//                break;
//            default:
////                fragmentClass = Frag.class;
//                intent = new Intent(this, SearchMovies.class);
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

    /**
     * Returns the user from this activity. To be used in MyAdapter.
     * @return the user object
     */
    public User getUser() {
        return user;
    }
}
