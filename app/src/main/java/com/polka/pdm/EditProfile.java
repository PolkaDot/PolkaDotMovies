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

    //when this activity is created, it makes a toolbar and stuff
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        User user;
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


        EditText username = (EditText)findViewById(R.id.UserTextField);
        username.setText(user.username);
        EditText pass = (EditText)findViewById(R.id.PassTextField);
        pass.setText(user.password);
        EditText name = (EditText)findViewById(R.id.NameTextField);
        name.setText(user.firstName);
        EditText email = (EditText)findViewById(R.id.EmailTextField);
        email.setText(user.email);
        EditText phone = (EditText)findViewById(R.id.PhoneTextField);
        phone.setText(user.phone);
        EditText interests = (EditText)findViewById(R.id.InterestsLabel);
        interests.setText(user.interests);



        //        EditText major = (EditText)findViewById(R.id.MajorTextField);
        //  //        major.setText(user.major);

    }

//    save button. should take you back to view profile
    public void onSavePress(View view) {
        Log.d("EditProfile", "Save Button Pressed");
        //save some data here
        Intent intent = new Intent(this, ViewProfile.class);
        startActivity(intent);
    }
    public void onCancelPress(View view) {
        Log.d("EditProfile", "Cancel Button Pressed");
        //don't save anything
        Intent intent = new Intent(this, ViewProfile.class);
        startActivity(intent);
    }

}
