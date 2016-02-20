package com.polka.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class HomeApp extends AppCompatActivity {

    private User user;
    private String response;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

//        couldn't get toolbar to work
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Grab data about user from extras
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                user = null;
            } else {
                user = extras.getParcelable("user");
            }
        } else {
            user = savedInstanceState.getParcelable("user");
        }
        Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
    }

    /**
     * Tester code for playing around with the database, creates a toast if found "user1" in db
     *
     * @param view view it is being used on
     */
    public void testDB(View view) {
        UserRepo repo = new UserRepo(this);
        User user = new User("user1", "pass1", "firstName1", "lastName1", "email1");
        repo.insert(user);
        String name = repo.getUserByUsername("user1").toString();
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }

    /**
     *     if you press log out, it will take you to the main activity screen
     *     future reference, go to layout, xml add button and set its on click
     *     to the method in this case logoutClickListener
     *
     * @param view it is being used on
     */

    public void logoutClickListener(View view) {
        Log.d("Logout", "Logout Button Pressed");

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /*
    * when you click view profile, it takes you to the view profile activity
     */
    public void onViewProfileClick(View view) {
        Log.d("ViewProfile", "View Profile Button Pressed");

        Intent intent = new Intent(this, ViewProfile.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    /**
     * Test Method to see if call to API works.
     * TODO: Implement in actual search activity when Arsh is done.
     * @param view
     */
    public void onSearchMovieClick(View view) {
        String apiKey = "yedukp76ffytfuy24zsqk7f5";
        String baseUrl = "http://api.rottentomatoes.com/api/public/v1.0/";
        String searchUrl = "movies.json?apikey=" + apiKey + "&q=Jack&page_limit=1";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, baseUrl + searchUrl, (String)null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject resp) {
                        //handle a valid response coming back.  Getting this string mainly for debug
                        response = resp.toString();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        response = "JSon Request Failed!!";
                    }
                });

        // Access the RequestQueue through your singleton class.
        Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }

}
