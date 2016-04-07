package com.polka.pdm;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Navigation bar class to have all methods in one place
 *
 * @author Created by C. Shih on 3/4/2016.
 * @version 2.1
 */
public abstract class NavBar extends AppCompatActivity {

    /**
     * drawer layout
     */
    private DrawerLayout mDrawer;

    /**
     * toolbar
     */
    private Toolbar toolbar;

    /**
     * action bar toggle
     */
    private ActionBarDrawerToggle drawerToggle;

    //Navigation bar stuff
    //can't be abstract because every class would define the same thing

    /**
     * user to be passed
     */
    private User superuser;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //cannot be replaced with 1 return statement because
        //if it is false we need to check super.onOptionsItemSelected
        //and must user super class to do that so must be extended
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * sets up listener for the side bar
     * can't be abstract because every class would define the same thing
     * @param navigationView the side bar
     */
    public void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                }
        );
    }

    /**
     * when you click on a menu item
     * this method is responsible
     * for directing you to the correct
     * new activity
     * and changing the appearances and stuff
     * (like highlighting your selection)
     * shouldn't be abstract because every class would define the same thing
     * @param menuItem the item that you pressed
     */
    private void selectDrawerItem(MenuItem menuItem) {
        //create new frag
        //determine what to show
        Intent intent;
        switch(menuItem.getItemId()) {
            case R.id.ViewProfile:
                intent = new Intent(this, ViewProfile.class);
                break;
            case R.id.SearchMovies:
                intent = new Intent(this, SearchMovies.class);
                break;
            case R.id.Movies:
                intent = new Intent(this, RecentMovies.class);
                break;
            case R.id.DVDs:
                intent = new Intent(this, RecentDvds.class);
                break;
            case R.id.Recommendations:
                intent = new Intent(this, RecommendMovie.class);
                break;
            case R.id.LogOut:
                intent = new Intent(this, MainActivity.class);
                break;
            default:
                intent = new Intent(this, HomeApp.class);
        }

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
        intent.putExtra("user", superuser);
        startActivity(intent);
    }

    /**
     * allows us to create a new ActionBarDrawerToggle specific to our needs
     * shouldn't be abstract because every class would define the same thing
     * @return a new ActionBarDrawer Toggle
     */
    public ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    /**
     * allows us to change the state of the toolbar whenever we change config
     * shouldn't be abstract because every class would define the same thing
     * @param newConfig the new configuration
     */
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * getter method for mDrawer
     * shouldn't be abstract because every class would define the same thing
     * @return mDrawer
     */
    public DrawerLayout getMDrawer() {
        return this.mDrawer;
    }
    /**
     * getter method for toolbar
     * shouldn't be abstract because every class would define the same thing
     * @return toolbar
     */
    public Toolbar getToolbar() {
        return this.toolbar;
    }

    /**
     * getter method for bar for drawer toggle
     * shouldn't be abstract because every class would define the same thing
     * @return drawer toggle
     */
    public ActionBarDrawerToggle getDrawerToggle() {
        return this.drawerToggle;
    }

    /**
     * getter method for user
     * shouldn't be abstract because every class would define the same thing
     * @return user
     */
    public User getUser() {
        return this.superuser;
    }

    /**
     * set method for mDrawer
     * shouldn't be abstract because every class would define the same thing
     *
     * @param amDrawer drawer layout
     */
    public void setMDrawer(DrawerLayout amDrawer) {
        this.mDrawer = amDrawer;
    }

    /**
     * set method for toolbar
     * shouldn't be abstract because every class would define the same thing
     *
     * @param atoolbar toolbar to be set
     */
    public void setToolbar(Toolbar atoolbar) {
        this.toolbar = atoolbar;
    }

    /**
     * set method for drawerToggle
     * shouldn't be abstract because every class would define the same thing
     *
     * @param adrawerToggle toggle bar
     */
    public void setDrawerToggle(ActionBarDrawerToggle adrawerToggle) {
        this.drawerToggle = adrawerToggle;
    }

    /**
     * set method for user
     * shouldn't be abstract because every class would define the same thing
     *
     * @param auser a user to be set
     */
    public void setUser(User auser) {
        this.superuser = auser;
    }


}
