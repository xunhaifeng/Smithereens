package com.xunhaifeng.module;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.xunhaifeng.R;
import com.xunhaifeng.module.meizhi.view.MeizhiFragment;
import com.xunhaifeng.module.science.view.ScienceFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar mToolbar;
    FloatingActionButton mFab;
    NavigationView mNavView;
    DrawerLayout mDrawerLayout;
    private MeizhiFragment mMeizhiFragment;
    private ScienceFragment scienceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mNavView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mNavView.setNavigationItemSelectedListener(this);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"定制系统信息：" , Snackbar.LENGTH_LONG).show();
            }
        });
        initFragment();
    }

    private void initFragment() {
        FragmentManager manager = getSupportFragmentManager();
        scienceFragment = new ScienceFragment();
        manager.beginTransaction().replace(R.id.fl_main_replace,scienceFragment).commit();
    }
    public void whitchFragmentToShow(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_replace,fragment).commit();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        //更换主界面内的fragment
        if(id == R.id.nav_technology){
            whitchFragmentToShow(scienceFragment);
        }else if (id == R.id.nav_girl) {
            whitchFragmentToShow(MeizhiFragment.getInstance());
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showProgress(){

    }

    public void hideProgress(){

    }

}
