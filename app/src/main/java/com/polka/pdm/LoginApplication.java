package com.polka.pdm;

//import statements
//Mockito
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
     */
    public void onLoginCancelPressed() {
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
        String Username = ((EditText) findViewById(R.id.usernameEdit)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordEdit)).getText().toString();


            if (checkInput(Username,password) == 0) {
                Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
//                return true;
            } else if (checkInput(Username,password)== -1) {
                Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
//                return true;
            } else if ((checkInput(Username,password)) == 1) {
                Toast.makeText(getApplicationContext(), "Enter UserName and Password", Toast.LENGTH_SHORT).show();
//                return true;
            } else {
            UserRepo repo = new UserRepo(this); //create repository for users
            // Get user information from database
            User user = repo.getUserByUsername(Username);

            // Checks if an admin
            if (isAdmin(user)) {
                Toast.makeText(this, "Logged in as Admin", Toast.LENGTH_SHORT).show();
                Intent startApp = new Intent(this, BlockUser.class);
                startApp.putExtra("user", user); // just in case this is needed...
                startActivity(startApp);

            } else {
                // is a regular user then...
                // verify password
                regularUser(password, user, repo);
            }
        }
    }

    public int checkInput(String Username,String password) {
        if(Username.isEmpty() && password.isEmpty()){
            return -1;
        }
        if(Username.isEmpty()) {
            return 0;
        }

        if(password.isEmpty()) {
            return 1;
        }

        return 2;
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
    public void incrementLock(UserRepo userRepo, User user) {
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
