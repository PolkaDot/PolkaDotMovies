/**
 * @author Christine Shih
 * @version 1.0
 * screen you see when you log in
 */
package com.polka.pdm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginApplication extends AppCompatActivity {
    /**
     * when you press the cancel button,
     * it takes you back to the main activity
     * @param v that we were are on
     */
    public void onLoginCancelPressed(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * when you press login
     * it checks if your username and pass is legit
     * if so it takes you to the homescreen of the app
     * @param v view that you are on
     */
    public void onLoginButtonPressed(View v) {
        Log.d("LOGIN ACTIVITY", "Login Button Pressed");
        EditText nameBox = (EditText) findViewById(R.id.usernameEdit);
        EditText passBox = (EditText) findViewById(R.id.passwordEdit);
        CharSequence text;

        UserRepo repo = new UserRepo(this);
        // Get user information from database
        User user = repo.getUserByUsername(nameBox.getText().toString());
        // verify password
        if (passBox.getText().toString().equals(user.password)) {
            text = "Login Success!";

            Intent startApp = new Intent(this, HomeApp.class);
            // Save user data for next activity
            startApp.putExtra("user", user);
            startActivity(startApp);
        } else {
            text = "Login Failure!";
        }

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast t = Toast.makeText(context, text, duration);
        t.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_application);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        Button btCancel = (Button) findViewById(R.id.cancelButton);
//
//        btCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setResult(RESULT_OK);
//                finish();
//            }
//        });

    }

}
