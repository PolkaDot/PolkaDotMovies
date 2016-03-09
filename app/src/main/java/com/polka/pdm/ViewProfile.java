/**
 * @author Esha Singh
 * @version 1.0
 * View User Profile
 */
package com.polka.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ViewProfile extends NavBar {
    private User user;

//    private DrawerLayout mDrawer;
//    private ActionBarDrawerToggle drawerToggle;
//    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //NAV bar stuff
        //
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        drawerToggle = setupDrawerToggle();
        mDrawer.setDrawerListener(drawerToggle);

        // Grab saved data about user
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
//        if (user != null) {
//            Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
//        }
        super.setUser(user);

        // Get TextViews on view profile page
        // TextView editTextLastName; TODO: add this into view profile page
        TextView phoneTextView = (TextView)findViewById(R.id.PhoneTextField);
        TextView firstNameTextView = (TextView)findViewById(R.id.FirstNameTextField);
        TextView lastNameTextView = (TextView)findViewById(R.id.LastNameTextField);
        TextView userTextView = (TextView)findViewById(R.id.UserTextField);
        TextView emailTextView = (TextView)findViewById(R.id.EmailTextField);
        TextView majorTextView = (TextView)findViewById(R.id.MajorTextField);
        TextView passTextView = (TextView)findViewById(R.id.PassTextField);
        TextView interestsTextView = (TextView)findViewById(R.id.InterestTextField);

        // Put user information in TextView boxes
        phoneTextView.setText(user.getPhone());
        firstNameTextView.setText(user.getFirstName());
        lastNameTextView.setText(user.getLastName());
        userTextView.setText(user.getUsername());
        emailTextView.setText(user.getEmail());
        majorTextView.setText(user.getMajor());
        passTextView.setText(user.getPhone());
        interestsTextView.setText(user.getInterests());
    }

    /**
     * This method takes the user to edit profile
     *
     * @param  view  the view of the Edit button that is clicked
     */
    public void onEditProfileClick(View view) {
        Intent intent = new Intent(this, EditProfile.class);
        // putExtra to store user information between views
        intent.putExtra("user", user);
        startActivity(intent);
    }

    /**
     * when you click on the home button it takes you to the home page
     * @param view of the current page
     */
    public void onHomeClick(View view) {
        Intent intent = new Intent(this, HomeApp.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

//    //Navigation bar stuff
//    //
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        //open close drawer
////        switch(item.getItemId()) {
////            case android.R.id.home:
////                mDrawer.openDrawer(GravityCompat.START);
////                return true;
////        }
////        return super.onOptionsItemSelected(item);
//        if (drawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    //
//    @Override
//    public void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        drawerToggle.syncState();
//    }
//
//    /**
//     * sets up listener for the side bar
//     * @param navigationView the side bar
//     */
//    private void setupDrawerContent(NavigationView navigationView) {
//        navigationView.setNavigationItemSelectedListener(
//                new NavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(MenuItem menuItem) {
//                        selectDrawerItem(menuItem);
//                        return true;
//                    }
//                }
//        );
//    }
//
//    /**
//     * when you click on a menu item
//     * this method is responsible
//     * for directing you to the correct
//     * new activity
//     * and changing the appearances and stuff
//     * (like highlighing your selection)
//     * @param menuItem the item that you pressed
//     */
//    public void selectDrawerItem(MenuItem menuItem) {
//        //create new frag
//        //determine what to show
//        Fragment fragment = null;
//        Class fragmentClass;
//        Intent intent;
////        Log.d("HomeApp","starting method");
//        switch(menuItem.getItemId()) {
//            case R.id.ViewProfile:
////                fragmentClass = Frag.class;
//                intent = new Intent(this, ViewProfile.class);
//                break;
//            case R.id.SearchMovies:
////                fragmentClass = Frag.class;
////                Log.d("HomeApp","searching movies");
//                intent = new Intent(this, SearchMovies.class);
////                Log.d("HomeApp","searched movies");
//                break;
//            case R.id.Movies:
//                intent = new Intent(this, RecentMovies.class);
////                fragmentClass = Frag.class;
//                break;
//            case R.id.DVDs:
//                intent = new Intent(this, RecentDvds.class);
////                fragmentClass = Frag.class;
//                break;
//            case R.id.Recommendations:
//                intent = new Intent(this, MainActivity.class);
////                fragmentClass = Frag.class;
//                break;
//            case R.id.LogOut:
////                fragmentClass = Frag.class;
////                Log.d("HomeApp","logging out");
//                intent = new Intent(this, MainActivity.class);
//                break;
////            case R.id.Cancel:
//////                fragmentClass = Frag.class;
//////                Log.d("HomeApp","logging out");
////                intent = new Intent(this, HomeApp.class);
////                break;
//            default:
////                fragmentClass = Frag.class;
//                intent = new Intent(this, ViewProfile.class);
//        }
////        try {
////            fragment = (Fragment) fragmentClass.newInstance();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        //insert frag by replacing existing frag
////        FragmentManager fragmentManager = getSupportFragmentManager();
////        fragmentManager.beginTransaction().replace(R.id.flContent,fragment).commit();
//
//        // Highlight the selected item
//        menuItem.setChecked(true);
////        Log.d("HomeApp", "checking item");
//        // update the title
//        setTitle(menuItem.getTitle());
////        Log.d("HomeApp", "creating title");
//        // close the drawer
//        mDrawer.closeDrawers();
//        intent.putExtra("user", user);
//        startActivity(intent);
//
//    }
//
//    /**
//     * allows us to create a new ActionBarDrawerToggle specific to our needs
//     * @return a new ActionBarDrawer Toggle
//     */
//    private ActionBarDrawerToggle setupDrawerToggle() {
//        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
//    }
//
//    /**
//     * allows us to change the state of the toolbar whenever we change config
//     * @param newConfig the new configuration
//     */
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        drawerToggle.onConfigurationChanged(newConfig);
//    }
//



}
