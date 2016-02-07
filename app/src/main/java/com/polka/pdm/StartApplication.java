package com.polka.pdm;
import android.content.Intent;
import android.view.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartApplication extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_application);

    }

    // if you press log out, it will take you to the main activity screen
    // future reference, go to layout, xml add button and set its on click
    // to the method in this case logoutClickListener
    public void logoutClickListener(View view) {
        Intent intent = new Intent(StartApplication.this, MainActivity.class);
        startActivity(intent);
    }

}
