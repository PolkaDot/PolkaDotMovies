package com.polka.pdm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;

public class RecentDvds extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_recentdvds);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sendJsonRequest();
    }

    private void sendJsonRequest() {
        String url = "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?apikey=yedukp76ffytfuy24zsqk7f5";


        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, (String)null, new Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        parseJSONObject(response);
                    }
                }, new ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                }
                );
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);

    }

    private void parseJSONObject(JSONObject response) {
        if (response == null || response.length() == 0) {
            return;
        }

        try {
            TextView  movie_names = (TextView)findViewById(R.id.RecentDVDsTextView);
            StringBuilder data = new StringBuilder();
            JSONArray arrayMovies = response.getJSONArray(Keys.KEY_MOVIE);
            for (int i = 0; i < arrayMovies.length() && i < 10; i++) {

                JSONObject currentMovie = arrayMovies.getJSONObject(i);
                String name = currentMovie.getString(Keys.KEY_TITLE);
                int num = i +1;
                data.append(num + " " + name + "\n");
            }
            movie_names.setText(data);
        } catch (JSONException e) {
            System.out.println("JSON Exception Exception");
        }

    }


}
