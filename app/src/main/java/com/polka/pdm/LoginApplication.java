/**
 * @author Christine Shih
 * @version 1.0
 * screen you see when you log in
 */
package com.polka.pdm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

        Intent intent = new Intent(this, MainActivity.class);
//        overridePendingTransition(17432579, 17432578);
        startActivity(intent);
    }

    public void onSocialMediaPressed(View v) {
        final Intent intent = new Intent(this, SocialMediaLogin.class);
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
        if (user.getIsAdmin() == 1 && passBox.getText().toString().equals(user.getPassword())) {
            text = "Logged in as Admin";
            Intent startApp = new Intent(this, BlockUser.class);
            startApp.putExtra("user", user); // just in case this is needed...
            startActivity(startApp);

        } else {
            // is a regular user then...
            // verify password
            if (passBox.getText().toString().equals(user.getPassword()) && isNotLocked(user) && user.getIsBanned() != 1) {
                text = "Login Success!";

                Intent startApp = new Intent(this, HomeApp.class);
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

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast t = Toast.makeText(context, text, duration);
        t.show();
    }

    /**
     * In case of a regular user, checks if your username and pass is legit
     * if so it takes you to the home screen of the app
     * @param password the user entered
     * @param user user user in question
     * @param repo used in the database
     */
    private void regularUser(String password, User user, UserRepo repo) {
        if (password.equals(user.getPassword()) && isNotLocked(user) && user.getIsBanned() != 1) {
            Toast.makeText(this, "Login Success!!", Toast.LENGTH_SHORT).show();

            //shared Preference for auto login
            SharedPreferences settings = getSharedPreferences("Auto login", MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("loggedIn", "loggedIn");
            editor.commit();


            Intent startApp = new Intent(this, HomeApp.class);

            startApp.putExtra("user", user);
            startActivity(startApp);
        } else {
            if (user.getIsBanned() == 1) {
                Toast.makeText(this, "YOU'RE BANNED FROM USING THIS!!!", Toast.LENGTH_SHORT).show();
            } else {
                incrementLock(repo, user);
            }
            Toast.makeText(this, "Login Failed!!", Toast.LENGTH_SHORT).show();
        }
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * *
     * @param v to get to the forget email screen
     */
    public void onClickForget(View v) {

        UserRepo repo = new UserRepo(this);
        String username = ((EditText) findViewById(R.id.usernameEdit)).getText().toString();
        User forgotUser = repo.getUserByUsername(username);
        if (forgotUser.getUsername() == null) {
            Toast.makeText(this, "No Such User", Toast.LENGTH_SHORT).show();
            return;
        }
        String to = forgotUser.getEmail();
        String subject = "Email Recovery";
        String message = "Username: " + username + "\n Password: " + forgotUser.getPassword();

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);

        //need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choose an Email client :"));

    }

}
