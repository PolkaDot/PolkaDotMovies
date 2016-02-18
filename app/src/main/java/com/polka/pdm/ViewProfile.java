package com.polka.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ViewProfile extends AppCompatActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
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

        // Get TextViews on view profile page
        TextView nameTextView = (TextView)findViewById(R.id.NameTextField);
        // TextView editTextLastName; TODO: add this into view profile page
        TextView userTextView = (TextView)findViewById(R.id.UserTextField);
        TextView emailTextView = (TextView)findViewById(R.id.EmailTextField);
        TextView majorTextView = (TextView)findViewById(R.id.MajorTextField);
        TextView passTextView = (TextView)findViewById(R.id.PassTextField);

        // Put user information in TextView boxes
        nameTextView.setText(user.firstName);
        userTextView.setText(user.username);
        emailTextView.setText(user.email);
        majorTextView.setText(user.major);
        passTextView.setText(user.password);
    }

    public void onEditProfileClick(View view) {
        Intent intent = new Intent(this, EditProfile.class);
        // putExtra to store user information between views
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void onHomeClick(View view) {
        Intent intent = new Intent(this, HomeApp.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }



}
