package com.kevin.pulltorefresh.autohome.loadinglayout;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.LoadingLayoutBase;
import com.kevin.pulltorefresh.autohome.R;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by zhouwk on 2015/12/22 0022.
 */
public class AutoHomeHeaderLayout extends LoadingLayoutBase{

    private FrameLayout mInnerLayout;
    private TextView mHeaderText;
    private TextView mSubHeaderText;
    private ImageView mDialImage;
    private ImageView mPointerImage;

    private CharSequence mPullLabel;
    private CharSequence mRefreshingLabel;
    private CharSequence mReleaseLabel;

    private Animation mAnimPointer;

    public AutoHomeHeaderLayout(Context context) {
        super(context);

        LayoutInflater.from(context).inflate(R.layout.autohome_header_loadinglayout, this);
        mInnerLayout = (FrameLayout) findViewById(R.id.fl_inner);
        mHeaderText = (TextView) mInnerLayout.findViewById(R.id.pull_to_refresh_text);
        mSubHeaderText = (TextView) mInnerLayout.findViewById(R.id.pull_to_refresh_sub_text);
        mDialImage = (ImageView) mInnerLayout.findViewById(R.id.pull_to_refresh_dial);
        mPointerImage = (ImageView) mInnerLayout.findViewById(R.id.pull_to_refresh_pointer);

        LayoutParams lp = (LayoutParams) mInnerLayout.getLayoutParams();
        lp.gravity = Gravity.BOTTOM;

        // Load in labels
        mPullLabel = context.getString(R.string.autohome_pull_label);
        mRefreshingLabel = context.getString(R.string.autohome_refreshing_label);
        mReleaseLabel = context.getString(R.string.autohome_release_label);

        mAnimPointer = AnimationUtils.loadAnimation(context, R.anim.pointer_rotate);

        reset();
    }

    // 获取"加载头部"高度
    @Override
    public int getContentSize() {
        return mInnerLayout.getHeight();
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
    }

    // 下拉拖动时的回调
    @Override
    public void onPull(float scaleOfLayout) {
        if(scaleOfLayout < 0.7f) scaleOfLayout = 0.7f;
        if(scaleOfLayout > 1.0f) scaleOfLayout = 1.0f;

        //旋转动画
        ViewHelper.setPivotX(mPointerImage, mPointerImage.getMeasuredWidth()/2);  // 设置中心点
        ViewHelper.setPivotY(mPointerImage, mPointerImage.getMeasuredHeight()/2);
        ObjectAnimator animPY = ObjectAnimator.ofFloat(mPointerImage, "rotation", 0, 250).setDuration(300);
        animPY.setCurrentPlayTime((long) (scaleOfLayout * 1000 - 700));
    }

    // 释放后刷新时的回调
    @Override
    public void refreshing() {
        mHeaderText.setText(mRefreshingLabel);
        mPointerImage.startAnimation(mAnimPointer);
    }

    @Override
    public void reset() {
        mPointerImage.clearAnimation();
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
