package com.polka.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Arsh Momin
 * @version 1.0
 * class to help a user edit his/her profile
 */
 public class EditProfile extends AppCompatActivity {
     private User user; //NEED to know which user's profile to display and update

//     private TextView username;
     private String userString;
     private EditText pass;
     private EditText firstName;
     private EditText lastName;
     private EditText email;
     private EditText major;
     private EditText phone;
     private EditText interests;

     //when this activity is created, it makes a toolbar and stuff
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Grab saved data about user
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                user = null;
            } else {
                user = extras.getParcelable(userString);
            }
        } else {
            user = savedInstanceState.getParcelable(userString);
        }
        if (user!=null) {
            Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
        }

        // get edit text fields
        TextView username = (TextView)findViewById(R.id.UserTextField);
        pass = (EditText)findViewById(R.id.PassTextField);
        firstName = (EditText)findViewById(R.id.FirstNameTextField);
        lastName = (EditText)findViewById(R.id.LastNameTextField);
        email = (EditText)findViewById(R.id.EmailTextField);
        major = (EditText)findViewById(R.id.MajorTextField);
        phone = (EditText)findViewById(R.id.PhoneTextField);
        interests = (EditText)findViewById(R.id.InterestsLabel);

        // Set all the text fields with user data
        username.setText(user.getUsername());
        pass.setText(user.getPassword());
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
        major.setText(user.getMajor());
        phone.setText(user.getPhone());
        interests.setText(user.getInterests());
    }

     /**
      * when you press save, it should save all the updates you made to your profile
      * @param view of the viewAProfile Activity
      * need view
      */
    public void onSavePress(View view) {
        Log.d("EditProfile", "Save Button Pressed");
        //save some data here

//        String aUsername = username.getText().toString();
        String aPassword = pass.getText().toString();
        String aFirstName = firstName.getText().toString();
        String aLastName = lastName.getText().toString();
        String aEmail = email.getText().toString();


        if (aFirstName.length() == 0 || aLastName.length() == 0 || aEmail.length() == 0 || aPassword.length() == 0) {
            Toast.makeText(this, "First Name, Last Name, Password, and Email must all be filled!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!aEmail.contains("@")) {
            Toast.makeText(this, "Must have valid email", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insert user in database
        UserRepo repo = new UserRepo(this);


        // insert updated user info into database
        repo.updateProfile(user.getUsername(), /*username.getText().toString(),*/ pass.getText().toString(),
                firstName.getText().toString(),lastName.getText().toString(), email.getText().toString(), phone.getText().toString(),
                major.getText().toString(), interests.getText().toString());
        // update current User instance (I believe this is unchanged at this point)
        user = repo.getUserByUsername(user.getUsername());

        // Toasts
        Toast.makeText(this, "Saved Changes to " + user.toString(), Toast.LENGTH_SHORT).show();

        // Switch activities
        Intent intent = new Intent(this, ViewProfile.class);
        intent.putExtra(userString, user);
        startActivity(intent);
    }

     /**
      * cancel button. should take you back to view profile
      * without saving data
      * @param view of Edit Profile
      * need view though
      */
    public void onCancelPress(View view) {
        Log.d("EditProfile", "Cancel Button Pressed");
        //don't save anything
        Intent intent = new Intent(this, ViewProfile.class);
        intent.putExtra(userString, user);
        startActivity(intent);
    }

}
