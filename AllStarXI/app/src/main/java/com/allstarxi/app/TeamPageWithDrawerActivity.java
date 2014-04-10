package com.allstarxi.app;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import android.app.FragmentActivity;

public class TeamPageWithDrawerActivity
        extends android.support.v4.app.FragmentActivity
        implements TeamFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener,
        TransfersFragment.OnFragmentInteractionListener,
        LeaguesFragment.OnFragmentInteractionListener,
        ShopFragment.OnFragmentInteractionListener,
        MatchesFragment.OnFragmentInteractionListener,
        RankingsFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener {

    // Drawer Related
    private String[] drawerListViewItems;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_page_with_drawer);

        // Drawer Related
        drawerListViewItems = getResources().getStringArray(R.array.nav_drawer_items);
        drawerListView = (ListView) findViewById(R.id.left_drawer);
        drawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, drawerListViewItems));

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.drawable.ic_launcher,
                R.string.drawer_open,
                R.string.drawer_close
        );

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        // just styling option
        //drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        drawerListView.setOnItemClickListener(new DrawerItemClickListener());

        // Load initial fragment

        FragmentManager fm = this.getSupportFragmentManager();

        android.support.v4.app.Fragment frag1 = fm.findFragmentById(R.id.content_frame);

        if ( frag1 == null ) {
            frag1 = TeamFragment.newInstance("test1","test2");
            FragmentHelper.initFragment( frag1, R.id.content_frame, fm);


        }

        /*Fragment frag2 = fm.findFragmentById(R.id.container2);
        if ( frag2 == null ) {
            frag2 = Panel2Fragment.newInstance();
            FragmentHelper.initFragment( frag2, R.id.container2, fm);
        }*/
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.team_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {


            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void goToScreen(int position)
    {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        android.support.v4.app.Fragment newFragment = new android.support.v4.app.Fragment();

        switch(position)
        {
            case 0:
                newFragment = new TeamFragment();
                break;
            case 1:
                newFragment = new ProfileFragment();
                break;
            case 2:
                newFragment = new TransfersFragment();
                break;
            case 3:
                newFragment = new LeaguesFragment();
                break;
            case 4:
                newFragment = new ShopFragment();
                break;
            case 5:
                newFragment = new MatchesFragment();
                break;
            case 6:
                newFragment = new RankingsFragment();
                break;
            case 7:
                newFragment = new SettingsFragment();
                break;
            case 8:
                newFragment = new ShopFragment();
                break;
            case 9:
                newFragment = new ShopFragment();
                break;
            default:
                break;
        }

        transaction.replace(R.id.content_frame, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {

            goToScreen(position);
            Toast.makeText(TeamPageWithDrawerActivity.this, ((TextView)view).getText(), Toast.LENGTH_LONG).show();
            drawerLayout.closeDrawer(drawerListView);

        }
    }
}