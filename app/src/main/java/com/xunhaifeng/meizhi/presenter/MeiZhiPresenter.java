package com.xunhaifeng.meizhi.presenter;

import android.text.TextUtils;

import com.xunhaifeng.http.FastJsonUtil;
import com.xunhaifeng.meizhi.contract.MeiZhiContract;
import com.xunhaifeng.meizhi.data.MeizhiRepository;
import com.xunhaifeng.meizhi.module.MeiZhiBean;

import io.reactivex.Observable;

/**
 * @name Smithereens
 * @class name：com.xunhaifeng.meizhi
 * @anthor xunhf
 * @email xunhf@inspur.com
 * @time 2017/8/25 11:21
 * @change
 * @chang time
 * @class describe
 */

public class MeiZhiPresenter implements MeiZhiContract.Presenter {
    private MeiZhiContract.View mView;
    private MeizhiRepository mMeizhiRepository;

    public MeiZhiPresenter(MeiZhiContract.View view){
        mMeizhiRepository = MeizhiRepository.getInstance();
        mView = view;
    }

    @Override
    public void subscribe() {
        getData(1);
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void getData(int page) {
        Observable<String> response = mMeizhiRepository.getNetData(page);
        response.subscribe(s -> {
            if(!TextUtils.isEmpty(s)){
                MeiZhiBean meiZhiBean = FastJsonUtil.getObject(s, MeiZhiBean.class);
                mView.showList(meiZhiBean);
            }
        });
    }
}
