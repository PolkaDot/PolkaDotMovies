/**
 * @Author Arsh Momin
 * @version 1.0
 * Activity that starts after you log into the app
 * this is the right way to do javadocs
 */

package com.polka.pdm;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class StartApplication extends AppCompatActivity  {
    public DBHelper userdb;

    //when we create this activity, there are some things we need to do first
    // hence the name on create
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userdb = new DBHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_application);
//        counldn't get toolbar to work
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
    }

    /**
     * Tester code for playing around with the database, creates a toast if found "user1" in db
     *
     * @param view view it is being used on
     */
    public void testDB(View view) {
        UserRepo repo = new UserRepo(this);
        User user = new User("user1", "pass1", "firstName1", "lastName1", "email1");
        repo.insert(user);
        String name = repo.getUserByUsername("user1").toString();
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }

    /**
     *     if you press log out, it will take you to the main activity screen
     *     future reference, go to layout, xml add button and set its on click
     *     to the method in this case logoutClickListener
     *
     * @param view it is being used on
     */

    public void logoutClickListener(View view) {
        Intent intent = new Intent(StartApplication.this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * when you click view profile, it takes you to the view profile activity
     * @param view it is being used on
     *
     * */
    public void onViewProfileClick(View view) {
        Intent intent = new Intent(this, ViewProfile.class);
        startActivity(intent);
    }

}
