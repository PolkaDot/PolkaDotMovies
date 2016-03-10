package com.polka.pdm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class BlockUser extends AppCompatActivity {
    User user;
    UserRepo userRepo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
