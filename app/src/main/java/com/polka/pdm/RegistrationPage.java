package com.polka.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationPage extends AppCompatActivity {

    EditText editTextFirstName;
    // EditText editTextLastName; TODO: add this into registration page
    EditText editTextUserName;
    EditText editTextEmail;
    EditText editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get EditText fields
        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
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
        // Insert user in database
        UserRepo repo = new UserRepo(this);
        User user = new User();

        user.firstName = editTextFirstName.getText().toString();
        user.username = editTextUserName.getText().toString();
        user.email = editTextEmail.getText().toString();
        user.password = editTextPassword.getText().toString();

        // insert user into database TODO: Check if user is already in database
        if (repo.getUserByUsername(user.username).username == null) {
            repo.insert(user);
            Toast.makeText(this, "New student inserted", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();

            // Switch to Edit Profile Activity
            Intent intent = new Intent(this, EditProfile.class);
            intent.putExtra("user", user);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Username already in use.", Toast.LENGTH_SHORT).show();
        }

    }

}
