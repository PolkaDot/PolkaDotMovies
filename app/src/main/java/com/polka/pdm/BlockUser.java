package com.polka.pdm;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class BlockUser extends AppCompatActivity {
    User user;
    UserRepo userRepo;

    // Needed for recycler view
    protected User[] mDataset;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        UserRepo repo = new UserRepo(this);
        mDataset = repo.getAllUsers();
        // BEGIN_INCLUDE (initializeRecyclerView)
        mRecyclerView = (RecyclerView) findViewById(R.id.userAdminRecyclerView);
        //


        // improves performance if you know that changes in content do not change the layout size
        // of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // user linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyUserAdapter(mDataset);
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

    /**
     * /**
     * when you press the ban button,
     * it bans the selected user
     * @param w that we were are on
     *
     */
    public void onBanButtonPress(View w) {
        UserRepo repo = new UserRepo(this);
        repo.setBanned(user.getUsername(), 1);
//        // Get user information from database
//        User user = repo.getUserByUsername(user.toString());

    }

    /**
     * /**
     * when you press the lock button,
     * it lock the selected user
     * @param w that we were are on
     *
     */
    public void onLockButtonPress(View w) {
        UserRepo repo = new UserRepo(this);
        repo.setLock(user.getUsername(),1);
    }

}
