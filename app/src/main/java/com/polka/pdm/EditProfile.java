 /*
 * @author Arsh Momin
 * @version 1.0
 * class to help a user edit his/her profile
 * */

package com.polka.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class EditProfile extends AppCompatActivity {

    //when this activity is created, it makes a toolbar and stuff
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
