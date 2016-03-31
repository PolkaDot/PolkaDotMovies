package com.polka.pdm;

//import statements
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Christine Shih
 * @version 1.0
 * screen you see when you log in
 */
public class LoginApplication extends AppCompatActivity {
    /**
     * Number of times a user can attempt to login before being locked out
     * of their account.
     */
    private static final int MAX_LOGIN_ATTEMPTS = 3;

    /**
     * when you press the cancel button,
     * it takes you back to the main activity
     * @param v that we were are on
     */
    public void onLoginCancelPressed(View v) {
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * when you press login
     * it checks if your username and pass is legit
     * if so it takes you to the home screen of the app
     * @param v view that you are on
     */
    public void onLoginButtonPressed(View v) {
        Log.d("LOGIN ACTIVITY", "Login Button Pressed");
        final EditText nameBox = (EditText) findViewById(R.id.usernameEdit);
        final EditText passBox = (EditText) findViewById(R.id.passwordEdit);
        CharSequence text;

        final UserRepo repo = new UserRepo(this); //create repository for users
        // Get user information from database
        final User user = repo.getUserByUsername(nameBox.getText().toString());

        // Checks if an admin
        if(isAdmin(user)){
            text = "Logged in as Admin";
            final Intent startApp = new Intent(this, BlockUser.class);
            startApp.putExtra("user", user); // just in case this is needed...
            startActivity(startApp);

        } else {
            // is a regular user then...
            // verify password
            if (passBox.getText().toString().equals(user.getPassword()) && isNotLocked(user) && user.getIsBanned() != 1) {
                text = "Login Success!";

                final Intent startApp = new Intent(this, HomeApp.class);
                // Save user data for next activity
                startApp.putExtra("user", user);
                startActivity(startApp);
            } else {
                if (user.getIsBanned() == 1) {
                    Toast.makeText(this, "YOU'RE BANNED FROM USING THIS!!!", Toast.LENGTH_SHORT).show();
                } else {
                    incrementLock(repo, user);
                }
                text = "Login Failure!";
            }
        }

        final Context context = getApplicationContext();
        final int duration = Toast.LENGTH_SHORT;
        final Toast t = Toast.makeText(context, text, duration);
        t.show();
    }

    /**
     * method to check if user is admin or not
     * @param user user in question
     * @return true if user is admin
     */
    private boolean isAdmin(User user) {
        final EditText pass = (EditText) findViewById(R.id.passwordEdit);
        return user.getIsAdmin() == 1 && pass.getText().toString().equals(user.getPassword());
    }

    /**
     * method to check if user is locked or unlocked
     * @param user user in question
     * @return true if user is not locked
     */
    private boolean isNotLocked(User user) {
        return user.getIsLocked() != MAX_LOGIN_ATTEMPTS;
    }

    /**
     * logic to the lock, increments lock number if login incorrect
     * then blocks once have 3 tries
     * @param userRepo the connector to the database
     * @param user trying to login
     */
    private void incrementLock(UserRepo userRepo, User user) {
        if (user.getUsername() != null && user.getIsAdmin() != 1) {
            if (user.getIsLocked() < MAX_LOGIN_ATTEMPTS) {
                userRepo.setLock(user.getUsername(), user.getIsLocked() + 1);
            } else {
                Toast.makeText(this, "YOU'RE LOCKED OUT!!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_application);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
