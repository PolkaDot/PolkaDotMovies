package com.polka.pdm;
import android.content.Intent;
import android.database.Cursor;
import android.content.CursorLoader;
import android.app.LoaderManager.LoaderCallbacks;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartApplication extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_application);
//        Button logout = findViewById(R.id.logout);
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(StartApplication.this, MainActivity.class);
//                startActivity(intent);
//            }
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
////            }
//        });
    }

    public void logoutClickListener(View view) {
        Intent intent = new Intent(StartApplication.this, MainActivity.class);
        startActivity(intent);
    }
//    Button logout;

//    logout.setOnClickListener(new View.OnClickListener() {
//
//        @Override
//        public void onClick(View view) {
//
//        }
//    });

}
