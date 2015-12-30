package com.kevin.pulltorefresh.tmall.loadinglayout;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.LoadingLayoutBase;
import com.kevin.pulltorefresh.tmall.R;

/**
 * Created by zhouwk on 2015/12/30 0030.
 */
public class TmallHeaderLayout extends LoadingLayoutBase{

    private FrameLayout mInnerLayout;
    private TextView mHeaderText;
    private ImageView mBikeImage;

    private CharSequence mPullLabel;
    private CharSequence mRefreshingLabel;
    private CharSequence mReleaseLabel;

    private AnimationDrawable animBike;

    public TmallHeaderLayout(Context context) {
        super(context);

        LayoutInflater.from(context).inflate(R.layout.tmall_header_loadinglayout, this);
        mInnerLayout = (FrameLayout) findViewById(R.id.fl_inner);
        mHeaderText = (TextView) mInnerLayout.findViewById(R.id.pull_to_refresh_text);
        mBikeImage = (ImageView) mInnerLayout.findViewById(R.id.pull_to_refresh_bike);

        LayoutParams lp = (LayoutParams) mInnerLayout.getLayoutParams();
        lp.gravity = Gravity.BOTTOM;

        // Load in labels
        mPullLabel = context.getString(R.string.tmall_pull_label);
        mRefreshingLabel = context.getString(R.string.tmall_refreshing_label);
        mReleaseLabel = context.getString(R.string.tmall_release_label);

        reset();
    }

    // 获取"加载头部"高度
    @Override
    public int getContentSize() {
        // 设置未完全显示的时候就促发刷新动作
        return mInnerLayout.getHeight() * 7 / 10;
    }

    // 开始下拉时的回调
    @Override
    public void pullToRefresh() {
        mHeaderText.setText(mPullLabel);
    }

    // "加载头部"完全显示时的回调
    @Override
    public void releaseToRefresh() {
        mHeaderText.setText(mReleaseLabel);

        if (animBike == null) {
            mBikeImage.setImageResource(R.drawable.refreshing_header_anim);
            animBike = (AnimationDrawable) mBikeImage.getDrawable();
        }
        animBike.start();
    }

    // 下拉拖动时的回调
    @Override
    public void onPull(float scaleOfLayout) {

    }

    // 释放后刷新时的回调
    @Override
    public void refreshing() {
        mHeaderText.setText(mRefreshingLabel);
    }

    // 初始化到未刷新状态
    @Override
    public void reset() {
        if (animBike != null) {
            animBike.stop();
            animBike = null;
        }
        mBikeImage.setImageResource(R.mipmap.tm_mui_bike_0);
    }

    @Override
    public void setPullLabel(CharSequence pullLabel) {
        mPullLabel = pullLabel;
    }

    @Override
    public void setRefreshingLabel(CharSequence refreshingLabel) {
        mRefreshingLabel = refreshingLabel;
    }

    @Override
    public void setReleaseLabel(CharSequence releaseLabel) {
        mReleaseLabel = releaseLabel;
    }
}
