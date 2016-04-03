package com.polka.pdm;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Welcome Page
 * @author Alisha
 * @version 1.0
 */

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     *
     * when you press the login button,
     * it takes you back to the login activity
     *
     * @param view current view
     */
    public void onLoginButtonClicked(View view) {
        final Intent intent = new Intent(this, LoginApplication.class);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.d("OptionsSelect", "optionsItemSelected was run.");
        return super.onOptionsItemSelected(item);
    }


    /**
     * when you press the sign up button,
     * it takes you back to the registration activity
     *
     * @param view current view
     */
    public void onSignUpButtonPress(View view) {
        final Intent intent = new Intent(this, RegistrationPage.class);
        startActivity(intent);


    }

}
