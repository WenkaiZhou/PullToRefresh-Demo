package com.kevin.pulltorefresh.autohome;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.kevin.loopview.AdLoopView;
import com.kevin.pulltorefresh.autohome.loadinglayout.AutoHomeHeaderLayout;
import com.kevin.wraprecyclerview.WrapAdapter;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 版权所有：XXX有限公司</br>
 *
 * MainActivity
 *
 * @author zhou.wenkai ,Created on 2015-12-22 9:04:49
 * @description Major Function：PullToRefresh框架之汽车之家刷新头部
 *
 * 注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author mender，Modified Date Modify Content:
 */
public class MainActivity extends AppCompatActivity {

    private String[] mStrings = { "Auto Home is a good website.", "There are a lot of friends who love cars."};

    private PullToRefreshRecyclerView mPullToRefreshRecyclerView;
    private RecyclerView mRecyclerView;
    private LinkedList<String> mListItems;
    // 自定义可添加头部、尾部 RecyclerView数据适配器包装类
    private WrapAdapter<RecyclerViewAdapter> mWrapAdapter;
    private RecyclerViewAdapter mAdapter;
    private AdLoopView mAdLoopView;

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
        mPullToRefreshRecyclerView = (PullToRefreshRecyclerView) this.findViewById(R.id.main_act_recycler_view);
        mPullToRefreshRecyclerView.setHeaderLayout(new AutoHomeHeaderLayout(this));

        initRecyclerView();
        initLoopView();
    }

    /**
     * 初始化 RecyclerView
     */
    private void initRecyclerView() {
        mRecyclerView = mPullToRefreshRecyclerView.getRefreshableView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerViewAdapter();
        mWrapAdapter = new WrapAdapter<>(mAdapter);
        mRecyclerView.setAdapter(mWrapAdapter);
    }

    /**
     * 初始化LoopView
     *
     * 这里使用的是LoopView开源项目，项目地址：https://github.com/xuehuayous/Android-LoopView
     *
     * @return void
     */
    private void initLoopView() {
        LayoutInflater inflater = LayoutInflater.from(this);
        FrameLayout layout = (FrameLayout) inflater.inflate(R.layout.recycler_header, null);
        mAdLoopView = (AdLoopView) layout.findViewById(R.id.home_frag_rotate_vp);
        mWrapAdapter.addHeaderView(layout);

        // 初始化RotateView数据
        String json = LocalFileUtils.getStringFormAsset(this, "loopview.json");
        mAdLoopView.refreshData(json);
        mAdLoopView.startAutoLoop();
    }

    private void initEvents() {
        mPullToRefreshRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                new GetDataTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
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
            mWrapAdapter.notifyDataSetChanged();

            // Call onRefreshComplete when the list has been refreshed.
            mPullToRefreshRecyclerView.onRefreshComplete();

            super.onPostExecute(result);
        }
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public int getItemCount() {
            return mListItems.size();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    MainActivity.this).inflate(android.R.layout.simple_list_item_1, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((MyViewHolder)holder).tv.setText(mListItems.get(position));
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;
            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(android.R.id.text1);
            }
        }

    }
}
