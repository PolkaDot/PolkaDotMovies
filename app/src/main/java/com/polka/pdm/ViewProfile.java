package com.polka.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ViewProfile extends AppCompatActivity {
    private String original;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        UserRepo repo = new UserRepo(this);
        User user = new User("user1", "pass1", "firstName1", "lastName1", "email1");
        repo.insert(user);
        String name = repo.getUserByUsername("user1").toString();
        TextView NameTextView = (TextView)findViewById(R.id.FirstNameTextField);
        NameTextView.setText(name);
        TextView UserTextView = (TextView)findViewById(R.id.UserTextField);
        UserTextView.setText(name);
        TextView PassTextView = (TextView)findViewById(R.id.PassTextField);
        PassTextView.setText(name);
        TextView PhoneTextView = (TextView)findViewById(R.id.PhoneTextField);
        PhoneTextView.setText(name);
        TextView EmailTextView = (TextView)findViewById(R.id.EmailTextField);
        EmailTextView.setText(name);
        TextView MajorTextView = (TextView)findViewById(R.id.MajorTextField);
        MajorTextView.setText(name);
        TextView InterestTextView = (TextView)findViewById(R.id.InterestTextField);
        InterestTextView.setText(name);

        original = user.username;
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    public void onEditProfileClick(View view) {
        Intent intent = new Intent(this, EditProfile.class);
//        intent.putExtra("username",original);
        startActivity(intent);
    }

}
