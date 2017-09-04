package com.xunhaifeng.meizhi.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.xunhaifeng.R;
import com.xunhaifeng.meizhi.adapter.MeizhiListAdapter;
import com.xunhaifeng.meizhi.adapter.OnMeizhiTouchListener;
import com.xunhaifeng.meizhi.contract.MeiZhiContract;
import com.xunhaifeng.meizhi.module.MeiZhiBean;
import com.xunhaifeng.meizhi.presenter.MeiZhiPresenter;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class MeiZhiActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MeiZhiContract.View<MeiZhiBean>, SwipeRefreshLayout.OnRefreshListener {

    Toolbar mToolbar;
    FloatingActionButton mFab;
    NavigationView mNavView;
    DrawerLayout mDrawerLayout;
    private MeiZhiContract.Presenter mPresenter;
    private MeiZhiPresenter meiZhiPresenter;
    private RecyclerView mRecyclerView;
    private ArrayList<MeiZhiBean.ResultsBean> mMeizhiList;
    private MeizhiListAdapter mMeizhiListAdapter;
    private boolean mIsFirstTimeTouchBottom = true;
    private int mPage = 1;
    private static final int PRELOAD_SIZE = 6;
    private boolean mMeizhiBeTouched;
    private SwipeRefreshLayout mSwipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMeizhiList = new ArrayList<>();
        initView();
        meiZhiPresenter = new MeiZhiPresenter(this);
    }

    private void initView() {
        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.refresh);
        mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2));
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mNavView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mNavView.setNavigationItemSelectedListener(this);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnScrollListener(getOnBottomListener(layoutManager));
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"这是massage", Snackbar.LENGTH_LONG).show();
            }
        });
        mSwipeRefresh.setOnRefreshListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        meiZhiPresenter.subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        meiZhiPresenter.unsubscribe();
    }

    @Override
    public void showList(MeiZhiBean meiZhiBean) {
        mSwipeRefresh.setRefreshing(false);
        mMeizhiList.addAll(meiZhiBean.getResults());
        if(mMeizhiListAdapter == null){
            mMeizhiListAdapter = new MeizhiListAdapter(this, mMeizhiList);
            mRecyclerView.setAdapter(mMeizhiListAdapter);
        }else {
            mMeizhiListAdapter.notifyDataSetChanged();
        }
        mMeizhiListAdapter.setOnMeizhiTouchListener(getOnMeizhiTouchListener());
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
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

    @Override
    public void setPresenter(MeiZhiContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hiddenLoading() {

    }

    RecyclerView.OnScrollListener getOnBottomListener(StaggeredGridLayoutManager layoutManager) {
        return new RecyclerView.OnScrollListener() {
            @Override public void onScrolled(RecyclerView rv, int dx, int dy) {
                boolean isBottom =
                        layoutManager.findLastCompletelyVisibleItemPositions(new int[2])[1] >=
                                mMeizhiListAdapter.getItemCount() - PRELOAD_SIZE;
                if (isBottom) {
                    if (!mIsFirstTimeTouchBottom) {
                        mPage += 1;
                        meiZhiPresenter.getData(mPage);
                    } else {
                        mIsFirstTimeTouchBottom = false;
                    }
                }
            }
        };
    }
    private OnMeizhiTouchListener getOnMeizhiTouchListener() {
        return (v, meizhiView, card, meizhi) -> {
            if (meizhi == null) return;
            if (v == meizhiView && !mMeizhiBeTouched) {
                mMeizhiBeTouched = true;
                Picasso.with(this).load(meizhi.getUrl()).fetch(new Callback() {
                    @Override
                    public void onSuccess() {
                        mMeizhiBeTouched = false;
                        startPictureActivity(meizhi, meizhiView);
                    }

                    @Override
                    public void onError() {
                        mMeizhiBeTouched = false;
                    }
                });
            } else if (v == card) {
//                startGankActivity(meizhi.publishedAt);
            }
        };
    }
    private void startPictureActivity(MeiZhiBean.ResultsBean meizhi, View transitView) {
        Intent intent = PictureActivity.newIntent(MeiZhiActivity.this, meizhi.getUrl(), meizhi.getDesc());
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                MeiZhiActivity.this, transitView, PictureActivity.TRANSIT_PIC);
        try {
            ActivityCompat.startActivity(MeiZhiActivity.this, intent, optionsCompat.toBundle());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            startActivity(intent);
        }
    }

    @Override
    public void onRefresh() {
        mMeizhiList.clear();
        mSwipeRefresh.setRefreshing(true);
        meiZhiPresenter.getData(1);
    }
}
