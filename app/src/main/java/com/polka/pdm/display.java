package com.polka.pdm;

/**
 * Created by esha on 4/26/2016.
 */
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class display extends AsyncTask<Object, Integer, List<HashMap<String, String>>> {

    GoogleMap gm;
    JSONObject jsonOb;

    @Override
    protected void onPostExecute(List<HashMap<String, String>> list) {
        gm.clear();
        for (int i = 0; i < list.size(); i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlePlace = list.get(i);
            double lat = Double.parseDouble(googlePlace.get("lat"));
            double lng = Double.parseDouble(googlePlace.get("lng"));
            String placeName = googlePlace.get("place_name");
            String vicinity = googlePlace.get("vicinity");
            LatLng latLng = new LatLng(lat, lng);
            markerOptions.position(latLng);
            markerOptions.title(placeName + " : " + vicinity);
            gm.addMarker(markerOptions);
        }
    }

    @Override
    protected List<HashMap<String, String>> doInBackground(Object... inputObj) {

        googlePlace placeJsonParser = new googlePlace();
        List<HashMap<String, String>> listOfPlaces = null;

        try {
            gm = (GoogleMap) inputObj[0];
            jsonOb = new JSONObject((String) inputObj[1]);
            listOfPlaces = placeJsonParser.parse(jsonOb);
        } catch (Exception e) {
            Log.d("Exception", e.toString());
        }
        return listOfPlaces;
    }

}
