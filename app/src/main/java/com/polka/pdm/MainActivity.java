package com.polka.pdm;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
     * @param w that we were are on
     *
     */
    public void onLoginButtonClicked(View w) {
        Intent intent = new Intent(this, LoginApplication.class);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }


    /**
     * when you press the sign up button,
     * it takes you back to the registration activity
     * @param v that we were are on
     */
    public void onSignUpButtonPress(View v ) {
        Intent intent = new Intent(this, RegistrationPage.class);
        startActivity(intent);


    }

}
