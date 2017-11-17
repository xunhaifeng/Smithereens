package com.xunhaifeng.module.meizhi.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.xunhaifeng.R;
import com.xunhaifeng.module.meizhi.adapter.MeizhiListAdapter;
import com.xunhaifeng.module.meizhi.adapter.OnMeizhiTouchListener;
import com.xunhaifeng.module.meizhi.contract.MeiZhiContract;
import com.xunhaifeng.module.meizhi.model.MeiZhiBean;
import com.xunhaifeng.module.meizhi.presenter.MeiZhiPresenter;

import java.util.ArrayList;

public class MeizhiFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener , MeiZhiContract.View<MeiZhiBean>{
    private MeiZhiPresenter mPresenter;
    private ArrayList<MeiZhiBean.ResultsBean> mMeizhiList = new ArrayList<>();
    private static final int PRELOAD_SIZE = 6;
    private RecyclerView mRecyclerView;
    private MeizhiListAdapter mMeizhiListAdapter;
    private boolean mIsFirstTimeTouchBottom = true;
    private int mPage = 1;
    private boolean mMeizhiBeTouched;
    private SwipeRefreshLayout mSwipeRefresh;

    public static MeizhiFragment getInstance() {
        return new MeizhiFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meizhi, container, false);
        mPresenter = new MeiZhiPresenter(this);
        mPresenter.onSubscribe(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.list);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnScrollListener(getOnBottomListener(layoutManager));
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2));
        mSwipeRefresh.setOnRefreshListener(this);
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
                        mPresenter.getData(mPage);
                    } else {
                        mIsFirstTimeTouchBottom = false;
                    }
                }
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    private OnMeizhiTouchListener getOnMeizhiTouchListener() {
        return (v, meizhiView, card, meizhi) -> {
            if (meizhi == null) return;
            if (v == meizhiView && !mMeizhiBeTouched) {
                mMeizhiBeTouched = true;
                Picasso.with(getContext()).load(meizhi.getUrl()).fetch(new Callback() {
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
        Intent intent = PictureActivity.newIntent(getContext(), meizhi.getUrl(), meizhi.getDesc());
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) getContext(), transitView, PictureActivity.TRANSIT_PIC);
        try {
            ActivityCompat.startActivity(getContext(), intent, optionsCompat.toBundle());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            startActivity(intent);
        }
    }

    public void setData(MeiZhiBean meiZhiBean) {
        mSwipeRefresh.setRefreshing(false);
        mMeizhiList.addAll(meiZhiBean.getResults());
        if(mMeizhiListAdapter == null){
            mMeizhiListAdapter = new MeizhiListAdapter(getContext(), mMeizhiList);
            mRecyclerView.setAdapter(mMeizhiListAdapter);
        }else {
            mMeizhiListAdapter.notifyDataSetChanged();
        }
        mMeizhiListAdapter.setOnMeizhiTouchListener(getOnMeizhiTouchListener());
    }

    @Override
    public void onRefresh() {
        mMeizhiList.clear();
        mSwipeRefresh.setRefreshing(true);
        mPresenter.getData(1);
    }

    @Override
    public void setPresenter(MeiZhiContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hiddenLoading() {

    }

    @Override
    public void showList(MeiZhiBean meiZhiBean) {
        setData(meiZhiBean);
    }
}
