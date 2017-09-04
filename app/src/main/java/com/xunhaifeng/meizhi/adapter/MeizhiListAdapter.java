/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.xunhaifeng.meizhi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xunhaifeng.R;
import com.xunhaifeng.meizhi.module.MeiZhiBean;
import com.xunhaifeng.meizhi.view.RatioImageView;

import java.util.ArrayList;

/**
 * Created by drakeet on 6/20/15.
 */
public class MeizhiListAdapter
        extends RecyclerView.Adapter<MeizhiListAdapter.ViewHolder> {

    public static final String TAG = "MeizhiListAdapter";

    private ArrayList<MeiZhiBean.ResultsBean> mList;
    private Context mContext;
    private OnMeizhiTouchListener mOnMeizhiTouchListener;


    public MeizhiListAdapter(Context context, ArrayList meizhiList) {
        mList = meizhiList;
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.item_meizhi, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        MeiZhiBean.ResultsBean meizhi = mList.get(position);
        int limit = 48;
        String text = meizhi.getDesc().length() > limit ? meizhi.getDesc().substring(0, limit) +
                "..." : meizhi.getDesc();
        viewHolder.meizhi = meizhi;
        viewHolder.titleView.setText(text);
        viewHolder.card.setTag(meizhi.getDesc());

        Glide.with(mContext)
                .load(meizhi.getUrl())
                .into(viewHolder.meizhiView)
                .getSize((width, height) -> {
                    if (!viewHolder.card.isShown()) {
                        viewHolder.card.setVisibility(View.VISIBLE);
                    }
                });
    }
    public void setOnMeizhiTouchListener(OnMeizhiTouchListener onMeizhiTouchListener) {
        this.mOnMeizhiTouchListener = onMeizhiTouchListener;
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RatioImageView meizhiView;
        TextView titleView;
        View card;
        MeiZhiBean.ResultsBean meizhi;

        public ViewHolder(View itemView) {
            super(itemView);
            card = itemView;
            meizhiView = (RatioImageView) itemView.findViewById(R.id.meizhi);
            titleView = (TextView) itemView.findViewById(R.id.title);
            meizhiView.setOnClickListener(this);
            card.setOnClickListener(this);
            meizhiView.setOriginalSize(50, 50);
        }


        @Override
        public void onClick(View v) {
            mOnMeizhiTouchListener.onTouch(v, meizhiView, card, meizhi);
        }
    }
}
