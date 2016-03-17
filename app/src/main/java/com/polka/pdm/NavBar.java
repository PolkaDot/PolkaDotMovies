package com.polka.pdm;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * attempt at Navigation bar class to have all methods in one place
 *
 * Created by C. Shih on 3/4/2016.
 */
public abstract class NavBar extends AppCompatActivity {

    protected DrawerLayout mDrawer;
    protected Toolbar toolbar;
    protected ActionBarDrawerToggle drawerToggle;

//    User user;
    //Navigation bar stuff
    protected User superuser;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //open close drawer
//        switch(item.getItemId()) {
//            case android.R.id.home:
//                mDrawer.openDrawer(GravityCompat.START);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //
//    @Override
//    public void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        drawerToggle.syncState();
//    }

    /**
     * sets up listener for the side bar
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
     * (like highlighing your selection)
     * @param menuItem the item that you pressed
     */
    public void selectDrawerItem(MenuItem menuItem) {
        //create new frag
        //determine what to show
        Fragment fragment = null;
        Class fragmentClass;
        Intent intent;
//        Log.d("HomeApp","starting method");
        switch(menuItem.getItemId()) {
            case R.id.ViewProfile:
//                fragmentClass = Frag.class;
                intent = new Intent(this, ViewProfile.class);
                break;
            case R.id.SearchMovies:
//                fragmentClass = Frag.class;
//                Log.d("HomeApp","searching movies");
                intent = new Intent(this, SearchMovies.class);
//                Log.d("HomeApp","searched movies");
                break;
            case R.id.Movies:
                intent = new Intent(this, RecentMovies.class);
//                fragmentClass = Frag.class;
                break;
            case R.id.DVDs:
                intent = new Intent(this, RecentDvds.class);
//                fragmentClass = Frag.class;
                break;
            case R.id.Recommendations:
                intent = new Intent(this, RecommendMovie.class);
//                fragmentClass = Frag.class;
                break;
            case R.id.LogOut:
//                fragmentClass = Frag.class;
//                Log.d("HomeApp","logging out");
                intent = new Intent(this, MainActivity.class);
                break;
//            case R.id.Cancel:
////                fragmentClass = Frag.class;
////                Log.d("HomeApp","logging out");
//                intent = new Intent(this, HomeApp.class);
//                break;
            default:
//                fragmentClass = Frag.class;
                intent = new Intent(this, HomeApp.class);
        }
//        try {
//            fragment = (Fragment) fragmentClass.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        //insert frag by replacing existing frag
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.flContent,fragment).commit();

        // Highlight the selected item
        menuItem.setChecked(true);
//        Log.d("HomeApp", "checking item");
        // update the title
        setTitle(menuItem.getTitle());
//        Log.d("HomeApp", "creating title");
        // close the drawer
        mDrawer.closeDrawers();
        intent.putExtra("user", superuser);
        startActivity(intent);

    }

    /**
     * allows us to create a new ActionBarDrawerToggle specific to our needs
     * @return a new ActionBarDrawer Toggle
     */
    public ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    /**
     * allows us to change the state of the toolbar whenever we change config
     * @param newConfig the new configuration
     */
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public DrawerLayout getMDrawer() {
        return this.mDrawer;
    }

    public Toolbar getToolbar() {
        return this.toolbar;
    }

    public ActionBarDrawerToggle getDrawerToggle() {
        return this.drawerToggle;
    }

    public User getUser() {
        return this.superuser;
    }


    public void setMDrawer(DrawerLayout mDrawer) {
        this.mDrawer = mDrawer;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public void setDrawerToggle(ActionBarDrawerToggle drawerToggle) {
        this.drawerToggle = drawerToggle;
    }

    public void setUser(User user) {
        this.superuser = user;
    }


}
