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

    public void logoutClickListener(View view) {
        Intent intent = new Intent(StartApplication.this, MainActivity.class);
        startActivity(intent);
    }

}
