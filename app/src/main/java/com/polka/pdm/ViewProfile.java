package com.polka.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 * @author Esha Singh
 * @version 1.0
 * View User Profile
 */
public class ViewProfile extends NavBar {
    /**
     * user using app
     */
    private User user;//NEED to know which user's profile to display

    /**
     * user string for extra
     */
    private static final String USERSTRING = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //NAV bar stuff
        setToolbar((Toolbar) findViewById(R.id.toolbar));

        setSupportActionBar(getToolbar());

        setMDrawer((DrawerLayout) findViewById(R.id.drawer_layout));
        final NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        setDrawerToggle(setupDrawerToggle());
        getMDrawer().setDrawerListener(getDrawerToggle());

        // Grab saved data about user
        if (savedInstanceState == null) {
            final Bundle extras = getIntent().getExtras();
            if (extras == null) {
                user = null;
            } else {
                user = extras.getParcelable(USERSTRING);
            }
        } else {
            user = savedInstanceState.getParcelable(USERSTRING);
        }

        super.setUser(user);

        // Get TextViews on view profile page
        final TextView phoneTextView = (TextView)findViewById(R.id.PhoneTextField);
        final TextView firstNameTextView = (TextView)findViewById(R.id.FirstNameTextField);
        final TextView lastNameTextView = (TextView)findViewById(R.id.LastNameTextField);
        final TextView userTextView = (TextView)findViewById(R.id.UserTextField);
        final TextView emailTextView = (TextView)findViewById(R.id.EmailTextField);
        final TextView majorTextView = (TextView)findViewById(R.id.MajorTextField);
        final TextView passTextView = (TextView)findViewById(R.id.PassTextField);
        final TextView interestsTextView = (TextView)findViewById(R.id.InterestTextField);

        // Put user information in TextView boxes
        phoneTextView.setText(user.getPhone());
        firstNameTextView.setText(user.getFirstName());
        lastNameTextView.setText(user.getLastName());
        userTextView.setText(user.getUsername());
        emailTextView.setText(user.getEmail());
        majorTextView.setText(user.getMajor());
        passTextView.setText(user.getPassword());
        interestsTextView.setText(user.getInterests());
    }

    /**
     * This method takes the user to edit profile
     *
     * @param  view  the view of the Edit button that is clicked
     */
    public void onEditProfileClick(View view) { //we need the view in the method header
        final Intent intent = new Intent(this, EditProfile.class);
        // putExtra to store user information between views
        intent.putExtra("user", user);
        startActivity(intent);
    }

    /**
     * when you click on the home button it takes you to the home page
     * @param view of the current page
     */
    public void onHomeClick(View view) {  //we need the view in the method header
        final Intent intent = new Intent(this, HomeApp.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

}
