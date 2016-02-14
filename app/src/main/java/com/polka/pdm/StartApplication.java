package com.polka.pdm;
import android.content.Intent;
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

    public void onViewProfileClick(View view) {
        Intent intent = new Intent(this, ViewProfile.class);
        startActivity(intent);
    }

}
