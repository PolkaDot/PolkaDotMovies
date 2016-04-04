package com.polka.pdm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

/**
 * Block the user
 * @author Alisha
 * @version 1.0
 */

public class BlockUser extends AppCompatActivity {
    private User user; //I'm using it in this conditional

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User[] dataSet; //need for recycler view aka m-Data-set

        RecyclerView mRecyclerView;
        RecyclerView.Adapter mAdapter;
        RecyclerView.LayoutManager mLayoutManager;


        setContentView(R.layout.activity_block_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        UserRepo repo = new UserRepo(this);
        dataSet = repo.getAllUsers();
        // BEGIN_INCLUDE (initializeRecyclerView)
        mRecyclerView = (RecyclerView) findViewById(R.id.userAdminRecyclerView);
        //


        // improves performance if you know that changes in content do not change the layout size
        // of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // user linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyUserAdapter(dataSet);
        mRecyclerView.setAdapter(mAdapter);

        //get the user from extras
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

}