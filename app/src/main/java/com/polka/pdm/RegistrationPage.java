/**
 * @author Alisha KC
 * @version 1.0
 * when you want to create a new user
 */
package com.polka.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Alisha KC
 * @version 1.0
 * when you want to create a new user
 */
public class RegistrationPage extends AppCompatActivity {

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextUserName;
    private EditText editTextEmail;
    private EditText editTextPassword;


    @Override
    final protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get EditText fields
        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
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
        Log.d("Cancel", "Cancel Button Pressed");
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

        String aFirstName = editTextFirstName.getText().toString();
        String aLastName = editTextLastName.getText().toString();
        String aUsername = editTextUserName.getText().toString();
        String aEmail = editTextEmail.getText().toString();
        String aPassword = editTextPassword.getText().toString();

        if (checkInfo(aFirstName, aLastName, aUsername, aEmail, aPassword) == -1) {
            Toast.makeText(this, "First Name, Last Name, Username, Password, and Email must all be filled!", Toast.LENGTH_SHORT).show();

            return;
        }
        if (checkInfo(aFirstName, aLastName, aUsername, aEmail, aPassword) == 0) {
            Toast.makeText(this, "Must have valid email", Toast.LENGTH_SHORT).show();
        }

        // Insert user in database
        UserRepo repo = new UserRepo(this);
        User user = new User();

        user.setFirstName(aFirstName);
        user.setLastName(aLastName);
        user.setUsername(aUsername);
        user.setEmail(aEmail);
        user.setPassword(aPassword);



        // insert user into database
        if (repo.getUserByUsername(user.getUsername()).getUsername() == null) {
            repo.insert(user);
            Toast.makeText(this, "New student inserted", Toast.LENGTH_SHORT).show();

            // Switch to Edit Profile Activity
            Intent intent = new Intent(this, EditProfile.class);
            intent.putExtra("user", user);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Username already in use.", Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * checks to see if user has filled out all fields of registration page
     *
     * @param aFirstName of new registered user
     * @param aLastName of new registered user
     * @param aUsername of new registered user
     * @param aEmail of new registered user
     * @param aPassword of new registered user
     * @return whether or not all fields are filled
     */
    int checkInfo(String aFirstName, String aLastName, String aUsername, String aEmail, String aPassword) {
        if (aFirstName.length() == 0 || aLastName.length() == 0 || aUsername.length() == 0 || aEmail.length() == 0 || aPassword.length() == 0) {
            // needs all fields filled in
            return -1;
        }


        if (!aEmail.contains("@")) {
            // need valid looking email, just checks for @ now...
            return 0;
        }

        return 1;
    }

}
