package com.kevin.pulltorefresh.meituan;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.kevin.pulltorefresh.meituan.loadinglayout.MeiTuanHeaderLayout;

/**
 * 版权所有：XXX有限公司</br>
 *
 * MainActivity
 *
 * @author zhou.wenkai ,Created on 2015-12-21 14:58:38
 * @description Major Function：PullToRefresh框架之美团刷新头部
 *
 * 注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author mender，Modified Date Modify Content:
 */
public class MainActivity extends AppCompatActivity {

    private PullToRefreshScrollView mPullToRefreshScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initEvents();
    }

    /**
     * 初始化Views
     */
    private void initViews() {
        mPullToRefreshScrollView = (PullToRefreshScrollView) this.findViewById(R.id.main_act_scrollview);
        mPullToRefreshScrollView.setHeaderLayout(new MeiTuanHeaderLayout(this));
    }

    /**
     * 初始化事件
     */
    private void initEvents() {
        mPullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {

            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                new GetDataTask().execute();
            }
        });
    }

    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {
            // Do some stuff here

            // Call onRefreshComplete when the list has been refreshed.
            mPullToRefreshScrollView.onRefreshComplete();

            super.onPostExecute(result);
        }
    }
}
