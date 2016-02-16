 /*
 * @author Arsh Momin
 * @version 1.0
 * class to help a user edit his/her profile
 * */

package com.polka.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


 public class EditProfile extends AppCompatActivity {
     private User user;

     EditText username;
     EditText pass;
     EditText name;
     EditText email;
     EditText phone;
     EditText interests;

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
                user = extras.getParcelable("user");
            }
        } else {
            user = savedInstanceState.getParcelable("user");
        }
        Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();

        // get edit text fields
        username = (EditText)findViewById(R.id.UserTextField); //TODO: DECIDE WHETHER USER CAN CHANGE USERNAME
        pass = (EditText)findViewById(R.id.PassTextField);
        name = (EditText)findViewById(R.id.NameTextField);
        email = (EditText)findViewById(R.id.EmailTextField);
        phone = (EditText)findViewById(R.id.PhoneTextField);
        interests = (EditText)findViewById(R.id.InterestsLabel);

        // Set all the text fields with user data
        username.setText(user.username);
        pass.setText(user.password);
        name.setText(user.firstName);
        email.setText(user.email);
        phone.setText(user.phone);
        interests.setText(user.interests);

        //        EditText major = (EditText)findViewById(R.id.MajorTextField);
        //  //        major.setText(user.major);

    }

//    save button. should take you back to view profile
    public void onSavePress(View view) {
        Log.d("EditProfile", "Save Button Pressed");
        //save some data here


        // Insert user in database
        UserRepo repo = new UserRepo(this);

//        user.firstName = name.getText().toString();
//        user.username = username.getText().toString();
//        user.email = email.getText().toString();
//        user.password = pass.getText().toString();

        // insert updated user info into database TODO: Check if user is already in database
        repo.updateProfile(user.username, username.getText().toString(), pass.getText().toString(),
                name.getText().toString(), email.getText().toString(), phone.getText().toString(),
                null, interests.getText().toString());
        // update current User instance (I believe this is unchanged at this point)
        user = repo.getUserByUsername(user.username);

        // Toasts
        Toast.makeText(this, "Changed name", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();

        // Switch activities
        Intent intent = new Intent(this, ViewProfile.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
    public void onCancelPress(View view) {
        Log.d("EditProfile", "Cancel Button Pressed");
        //don't save anything
        Intent intent = new Intent(this, ViewProfile.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

}
