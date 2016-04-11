/**
 * Recommends movie to the user based on their major
 *
 * @author Yamilex
 * @version 1.0
 */
package com.polka.pdm;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class RecommendMovie extends NavBar {

    /**
     * mAdapter of movie for recycler view
     */
    private RecyclerView.Adapter mAdapter;

    /**
     * user using app
     */
    private User user;
    /**
     * movies to be displayed
     */
    private Movie[] mDataset;
    /**
     * number of total movies showing
     */
    private static final int DATASET_COUNT = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //using protected gives one error not using protected gives us another error

        //Needed for recycler view
        RecyclerView mRecyclerView;
        RecyclerView.LayoutManager mLayoutManager;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_movie);
        setToolbar((Toolbar) findViewById(R.id.toolbar));

        setSupportActionBar(getToolbar());

        // BEGIN_INCLUDE (initializeRecyclerView)
        mRecyclerView = (RecyclerView) findViewById(R.id.moviesMajorRecylerView);

        setMDrawer((DrawerLayout) findViewById(R.id.drawer_layout));
        final NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        setDrawerToggle(setupDrawerToggle());
        getMDrawer().setDrawerListener(getDrawerToggle());

        // Improves performance if you know that changes in content
        // do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // User linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

        //Toggle for nav bar
//        drawerToggle = setupDrawerToggle();
//        mDrawer.setDrawerListener(drawerToggle);

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


    }

    /**
     * Indicates that the user wants to search the movie typed in the editText
     *
     * @param view View of the search Movies activity
     */
    public void onSearchMajorButtonPress(View view) {  //we need the view in the method header
        EditText editTextSearchParam;
        editTextSearchParam = (EditText) findViewById(R.id.MajorTextField);
        final String searchParam =  editTextSearchParam.getText().toString();

        final ReviewRepo repo = new ReviewRepo(this);
        mDataset = repo.getRatingsByMajor(searchParam, DATASET_COUNT);
//        mDataset = movies;
        ((MyAdapter)mAdapter).setData(mDataset);


    }

    /**
     * Gives access to the user from this activity. It is used in MyAdapter.
     *
     * @return the user object
     */
    public User getUser() {
        return user;
    }
}
