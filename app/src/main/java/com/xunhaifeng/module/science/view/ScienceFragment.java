package com.xunhaifeng.module.science.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xunhaifeng.R;
import com.xunhaifeng.module.science.adapter.ScienceAdapter;
import com.xunhaifeng.module.science.contract.ScienceContract;
import com.xunhaifeng.module.science.model.ScienceBean;
import com.xunhaifeng.module.science.presenter.SciencePresenter;

import java.util.List;

/**
 * @version v0.0.1
 * @name Smithereens
 * @class name：com.xunhaifeng.module.science
 * @anthor xunhf
 * @email xunhf@inspur.com
 * @time 2017/11/17 11:27
 * @chang time
 * @class 技术展示界面
 */

public class ScienceFragment extends Fragment implements ScienceContract.SCView{
    private SciencePresenter mSciencePresenter;
    private RecyclerView mContent;
    private ScienceAdapter mScienceAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_science_fragment, container, false);
        mSciencePresenter = new SciencePresenter();
        mSciencePresenter.onSubscribe(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContent = view.findViewById(R.id.rlv);
        mContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSciencePresenter.getData(1);
    }

    @Override
    public void setPresenter(ScienceContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hiddenLoading() {

    }

    @Override
    public void showData(ScienceBean scienceBean) {
        List<ScienceBean.ResultsBean> results = scienceBean.getResults();
        if(mScienceAdapter== null){
            mScienceAdapter = new ScienceAdapter(getActivity(), results);
            mContent.setAdapter(mScienceAdapter);
        }else{
            mScienceAdapter.update(results);
        }
    }
}
