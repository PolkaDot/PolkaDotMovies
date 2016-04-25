/**
 * Activity that the admin sees
 * @author Alisha
 * @version 1.0
 */
package com.polka.pdm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

/**
 * Block the user
 * @author Alisha
 * @version 1.0
 */

public class BlockUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //needed
        User[] dataSet; //need for recycler view aka m-Data-set

        RecyclerView mRecyclerView;
        RecyclerView.Adapter mAdapter;
        RecyclerView.LayoutManager mLayoutManager;


        setContentView(R.layout.activity_block_user);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final UserRepo repo = new UserRepo(this);
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

//        get the user from extras
        User user;
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

        if (user != null) {
            Toast.makeText(this, "Logged in as " + user.getUsername(), Toast.LENGTH_SHORT).show();
        }
    }

}