package com.polka.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ViewProfile extends AppCompatActivity {
    private String original;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        UserRepo repo = new UserRepo(this);

        // Grab saved data about user
        User user;
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

        // Get TextViews on viewprofile page
        TextView nameTextView = (TextView)findViewById(R.id.NameTextField);
        // TextView editTextLastName; TODO: add this into view profile page
        TextView userTextView = (TextView)findViewById(R.id.UserTextField);
        TextView emailTextView = (TextView)findViewById(R.id.EmailTextField);
        TextView passTextView = (TextView)findViewById(R.id.PassTextField);

//        // Display user information
        nameTextView.setText(user.firstName);
        userTextView.setText(user.username);
        emailTextView.setText(user.email);
        passTextView.setText(user.password);

//        User user = new User("user1", "pass1", "firstName1", "lastName1", "email1");
//        repo.insert(user);
//        String name = repo.getUserByUsername("user1").toString();
//        TextView NameTextView = (TextView)findViewById(R.id.NameTextField);
//        NameTextView.setText(name);
//        TextView UserTextView = (TextView)findViewById(R.id.UserTextField);
//        UserTextView.setText(name);
//        TextView PassTextView = (TextView)findViewById(R.id.PassTextField);
//        PassTextView.setText(name);
//        TextView PhoneTextView = (TextView)findViewById(R.id.PhoneTextField);
//        PhoneTextView.setText(name);
//        TextView EmailTextView = (TextView)findViewById(R.id.EmailTextField);
//        EmailTextView.setText(name);
//        TextView MajorTextView = (TextView)findViewById(R.id.MajorTextField);
//        MajorTextView.setText(name);
//        TextView InterestTextView = (TextView)findViewById(R.id.InterestTextField);
//        InterestTextView.setText(name);
//
//        original = user.username;

    }

    public void onEditProfileClick(View view) {
        Intent intent = new Intent(this, EditProfile.class);
//        intent.putExtra("username",original);
        startActivity(intent);
    }

}
