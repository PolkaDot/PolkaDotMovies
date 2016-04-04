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

    //Needed for recycler view
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private User user;
    protected Movie[] mDataset;
    private static final int DATASET_COUNT = 30;
    EditText editTextSearchParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_movie);
        setToolbar((Toolbar) findViewById(R.id.toolbar));

        setSupportActionBar(getToolbar());

        setMDrawer((DrawerLayout) findViewById(R.id.drawer_layout));
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
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
     * Indicates that the user wants to search the movie typed in the editText
     *
     * @param view View of the search Movies activity
     */
    public void onSearchMajorButtonPress(View view) {
        editTextSearchParam = (EditText) findViewById(R.id.MajorTextField);
        String searchParam =  editTextSearchParam.getText().toString();

        ReviewRepo repo = new ReviewRepo(this);
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
