 /**
 * @author Arsh Momin
 * @version 1.0
 * class to help a user edit his/her profile info
 * */

package com.polka.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.view.View;


public class EditProfile extends AppCompatActivity {



    /**
     * when this activity is created, it makes a toolbar and stuff
     * @param savedInstanceState to add stuff on this activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        UserRepo repo = new UserRepo(this);
        User user = repo.getUserByUsername("user1");
        EditText username = (EditText)findViewById(R.id.UserTextField);
        username.setText(user.username);
//        EditText pass = (EditText)findViewById(R.id.passwordText);
//        pass.setText(user.password);
//        EditText name = (EditText)findViewById(R.id.nameText);
//        name.setText(user.firstName);
//        EditText email = (EditText)findViewById(R.id.emailText);
//        email.setText(user.email);
//        EditText phone = (EditText)findViewById(R.id.PhoneTextField);
//        phone.setText(user.phone);
//        EditText interests = (EditText)findViewById(R.id.InterestsLabel);
//        interests.setText(user.interests);



        //        EditText major = (EditText)findViewById(R.id.MajorTextField);
        //  //        major.setText(user.major);

    }


    /**
     * save button. should take you back to view profile
     * @param view which we need to direct to the next activity
     *
     * */
    public void onSavePress(View view) {
        Log.d("EditProfile", "Save Button Pressed");
        //save some data here
        Intent intent = new Intent(this, ViewProfile.class);
        startActivity(intent);
    }

    /** cancel button. should take you back to view profile
     * wihthout saving anything
     *
     * @param view which we need to direct to the next activity
     */
    public void onCancelPress(View view) {
        Log.d("EditProfile", "Cancel Button Pressed");
        //don't save anything
//        Intent intent = new Intent(this, ViewProfile.class);
        Intent intent = new Intent(this, ViewProfile.class);
        startActivity(intent);
    }

}
