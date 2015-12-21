package com.kevin.pulltorefresh.jingdong;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.kevin.pulltorefresh.jingdong.loadinglayout.JingDongHeaderLayout;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 版权所有：XXX有限公司</br>
 *
 * MainActivity
 *
 * @author zhou.wenkai ,Created on 2015-12-21 9:59:31
 * @description Major Function：PullToRefresh框架之京东刷新头部
 *
 * 注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author mender，Modified Date Modify Content:
 */
public class MainActivity extends AppCompatActivity {

    private String[] mStrings = {"Jingdong's mascot is a dog.", "But I like the wolf.",
            "The wolf is a very powerful animal."};

    private PullToRefreshListView mPullToRefreshListView;
    private ListView mListView;

    private LinkedList<String> mListItems;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListItems = new LinkedList<>();
        mListItems.addAll(Arrays.asList(mStrings));

        initViews();
        initEvents();
    }

    /**
     * 初始化View
     */
    private void initViews() {
        mPullToRefreshListView = (PullToRefreshListView) this.findViewById(R.id.main_act_listview);
        // 设置自定义"刷新头部"
        mPullToRefreshListView.setHeaderLayout(new JingDongHeaderLayout(this));

        mListView = mPullToRefreshListView.getRefreshableView();
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mListItems);
        mListView.setAdapter(mAdapter);
    }

    /**
     * 初始化事件
     */
    private void initEvents() {
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                // Do work to refresh the list here.
                new GetDataTask().execute();
            }
        });
    }

    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            return mStrings;
        }

        @Override
        protected void onPostExecute(String[] result) {
            mListItems.addFirst("Added after refresh...");
            mAdapter.notifyDataSetChanged();

            // Call onRefreshComplete when the list has been refreshed.
            mPullToRefreshListView.onRefreshComplete();

            super.onPostExecute(result);
        }
    }
}
