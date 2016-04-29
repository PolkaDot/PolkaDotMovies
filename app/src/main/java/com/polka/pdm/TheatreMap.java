package com.polka.pdm;

/**
 * Created by esha on 4/25/2016.
 */
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class TheatreMap extends FragmentActivity implements LocationListener {

    private static final String GOOGLE_API_KEY = "AIzaSyDOZQBG1HJ_AmD_9K0nHTP_FA8oQ5MqDg8";
    GoogleMap gm;
    double latitude = 0;
    double longitude = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int connection = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (connection != ConnectionResult.SUCCESS ) {
            GooglePlayServicesUtil.getErrorDialog(connection, this, 0).show();
            finish();
        }
        setContentView(R.layout.activity_theatremap);
        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        gm = fragment.getMap();
        gm.setMyLocationEnabled(true);
        LocationManager manage = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = manage.getBestProvider(criteria, true);
        Location location = manage.getLastKnownLocation(bestProvider);
        if (location != null) {
            onLocationChanged(location);
        }
        manage.requestLocationUpdates(bestProvider, 20000, 0, this);
        findTheatre();
    }

    private void findTheatre(){
        StringBuilder buildUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/search/json?");;
        buildUrl.append("&location=");
        buildUrl.append(Double.toString(latitude));
        buildUrl.append(",");
        buildUrl.append(Double.toString(longitude));
        buildUrl.append("&radius=5000");
        buildUrl.append("&types=" + "movie_theater");
        buildUrl.append("&key=" + GOOGLE_API_KEY);
        Log.v("URL ", "" + buildUrl);
        UrlRead ru = new UrlRead();
        Object[] toPass = new Object[2];
        toPass[0] = gm;
        toPass[1] = buildUrl.toString();
        ru .execute(toPass);
    }


    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        gm.moveCamera(CameraUpdateFactory.newLatLng(latLng));
       gm.animateCamera(CameraUpdateFactory.zoomTo(12));
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }
}