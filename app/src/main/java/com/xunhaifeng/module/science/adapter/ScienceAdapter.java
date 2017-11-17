package com.xunhaifeng.module.science.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xunhaifeng.R;
import com.xunhaifeng.module.science.model.ScienceBean;
import com.xunhaifeng.module.web.WebActivity;

import java.util.List;

/**
 * @version v0.0.1
 * @name Smithereens
 * @class name：com.xunhaifeng.module.science.adapter
 * @anthor xunhf
 * @email xunhf@inspur.com
 * @time 2017/11/17 14:20
 * @chang time
 * @class describe
 */

public class ScienceAdapter extends RecyclerView.Adapter<ScienceAdapter.ScienceViewHolder> {

    private List<ScienceBean.ResultsBean> mList;
    private Context mContext;

    public ScienceAdapter(Context context, List<ScienceBean.ResultsBean> results) {
        mContext = context;
        mList = results;
    }
    public void update(List<ScienceBean.ResultsBean> results){
        notifyDataSetChanged();
    }
    @Override
    public ScienceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ScienceViewHolder(View.inflate(mContext, R.layout.item_science,null));
    }

    @Override
    public void onBindViewHolder(ScienceViewHolder holder, int position) {
        ScienceBean.ResultsBean resultsBean = mList.get(position);
        holder.mTitle.setText(resultsBean.getDesc());
        holder.mContentSource.setText(resultsBean.getSource());
        holder.mContentWho.setText(resultsBean.getWho());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到webview
                Intent intent = new Intent();
                intent.setClass(mContext, WebActivity.class);
                intent.putExtra("url",resultsBean.getUrl());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ScienceViewHolder extends RecyclerView.ViewHolder{

        private TextView mTitle,mContentSource,mContentWho;
        public ScienceViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tv_content_title);
            mContentSource = itemView.findViewById(R.id.tv_source);
            mContentWho = itemView.findViewById(R.id.tv_who);
        }
    }
}
