package com.polka.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class RegistrationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    /**
     * This method takes the user back to the main screen without
     * saving the user's details in the database. The registration
     * is incomplete.
     *
     * @param  v  the view of the Cancel button that is clicked
     */

    public void onCancelButtonPress(View v ) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * This method saves the user's details in the database and
     * redirects the user back to the home page if all the information
     * provided by the user is correct. It gives an error message if the
     * information is incorrect.
     *
     * @param  v  the view of the Register button that is clicked
     */

    public void onRegisterButtonPress(View v ) {
        Log.d("Register", "Register Button Pressed");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
