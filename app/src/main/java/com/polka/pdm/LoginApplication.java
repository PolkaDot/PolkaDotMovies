/**
 * @author Christine Shih
 * @version 1.0
 * screen you see when you log in
 */
package com.polka.pdm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

        // Checks if an admin
        if (user.isAdmin == 1 && passBox.getText().toString().equals(user.password)) {
            text = "Logged in as Admin";
            Intent startApp = new Intent(this, HomeApp.class); // TODO: CHANGE THIS ONCE ACTIVITY DONE!
            startApp.putExtra("user", user); // just in case this is needed...
            startActivity(startApp);

        } else {
            // is a regular user then...
            // verify password
            if (passBox.getText().toString().equals(user.password) && isNotLocked(user) && user.isBanned != 1) {
                text = "Login Success!";

                Intent startApp = new Intent(this, HomeApp.class);
                // Save user data for next activity
                startApp.putExtra("user", user);
                startActivity(startApp);
            } else {
                if (user.isBanned == 1) {
                    Toast.makeText(this, "YOU'RE BANNED FROM USING THIS!!!", Toast.LENGTH_SHORT).show();
                } else {
                    incrementLock(repo, user);
                }
                text = "Login Failure!";
            }
        }

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast t = Toast.makeText(context, text, duration);
        t.show();
    }

    /**
     * method to check if user is locked or unlocked
     * @param user user in question
     * @return true if user is not locked
     */
    private boolean isNotLocked(User user) {
        return user.isLocked != 3;
    }

    /**
     * logic to the lock, incrememnts lock number if login incorrect
     * then blocks once have 3 tries
     * @param userRepo the connector to the database
     * @param user trying to login
     */
    private void incrementLock(UserRepo userRepo, User user) {
        if (user.username != null && user.isAdmin != 1) {
            if (user.isLocked < 3) {
                userRepo.setLock(user.username, user.isLocked + 1);
            } else {
                Toast.makeText(this, "YOU'RE LOCKED OUT!!!", Toast.LENGTH_SHORT).show();
            }
        }
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
