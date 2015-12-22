package com.kevin.pulltorefresh.autohome;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * 版权所有：XXX有限公司</br>
 *
 * WrapAdapter </br>
 *
 * @author zhou.wenkai ,Created on 2015-11-24 10:48:29</br>
 * @description Major Function：A RecyclerView.Adapter that allows for headers and footers as well. </br>
 *
 * 注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！</br>
 * @author mender，Modified Date Modify Content:
 */
@SuppressWarnings("rawtypes")
public class WrapAdapter<T extends RecyclerView.Adapter> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	
    private final T mRealAdapter;

    // Defines available view type integers for headers and footers.
	private static final int BASE_HEADER_VIEW_TYPE = -1 << 10;
	private static final int BASE_FOOTER_VIEW_TYPE = -1 << 11;
	
    private ArrayList<FixedViewInfo> mHeaderViewInfos = new ArrayList<FixedViewInfo>();
    private ArrayList<FixedViewInfo> mFooterViewInfos = new ArrayList<FixedViewInfo>();
    
    /**
     * A class that represents a fixed view in a list, for example a header at the top
     * or a footer at the bottom.
     */
    public class FixedViewInfo {
        /** The view to add to the list */
        public View view;
        /** The data backing the view. This is returned from {@link RecyclerView.Adapter#getItemViewType(int)}. */
        public int viewType;
    }

	/**
	 * Constructor.
	 *
	 * @param adapter
	 * 		the adapter to wrap
	 */
	public WrapAdapter(T adapter) {
		super();
		mRealAdapter = adapter;
	}
	
	/**
	 * Gets the real adapter
	 * 
	 * @return T:
	 * @version 1.0 
	 * @date 2015-11-24
	 * @Author zhou.wenkai
	 */
	public T getWrappedAdapter() {
		return mRealAdapter;
	}

	/**
	 * Adds a header view
	 * 
	 * @param view
	 * @return void:
	 * @version 1.0 
	 * @date 2015-11-24
	 * @Author zhou.wenkai
	 */
	public void addHeaderView(View view) {
		if (null == view) {
			throw new IllegalArgumentException("the view to add must not be null!");
		}
        final FixedViewInfo info = new FixedViewInfo();
        info.view = view;
        info.viewType = BASE_HEADER_VIEW_TYPE + mHeaderViewInfos.size();
        mHeaderViewInfos.add(info);
        notifyDataSetChanged();
	}

	/**
	 * Adds a footer view
	 * 
	 * @param view
	 * @return void:
	 * @version 1.0 
	 * @date 2015-11-24
	 * @Author zhou.wenkai
	 */
	public void addFooterView(View view) {
		if (null == view) {
			throw new IllegalArgumentException("the view to add must not be null!");
		}
        final FixedViewInfo info = new FixedViewInfo();
        info.view = view;
        info.viewType = BASE_FOOTER_VIEW_TYPE + mFooterViewInfos.size();
		mFooterViewInfos.add(info);
		notifyDataSetChanged();
	}
	
	/**
	 * gets the headers view
	 * 
	 * @return
	 * @return List<View>:
	 * @version 1.0 
	 * @date 2015-11-24
	 * @Author zhou.wenkai
	 */
	public List<View> getHeadersView() {
		List<View> viewList = new ArrayList<View>(getHeadersCount());
		for (FixedViewInfo fixedViewInfo : mHeaderViewInfos) {
			viewList.add(fixedViewInfo.view);
		}
		return viewList;
	}
	
	/**
	 * gets the footers view
	 * 
	 * @return
	 * @return List<View>:
	 * @version 1.0 
	 * @date 2015-11-24
	 * @Author zhou.wenkai
	 */
	public List<View> getFootersView() {
		List<View> viewList = new ArrayList<View>(getHeadersCount());
		for (FixedViewInfo fixedViewInfo : mFooterViewInfos) {
			viewList.add(fixedViewInfo.view);
		}
		return viewList;
	}
	
	/**
	 * adjust the GridLayoutManager SpanSize
	 * 
	 * @param recycler
	 * @return void:
	 * @version 1.0 
	 * @date 2015-11-24
	 * @Author zhou.wenkai
	 */
	public void adjustGridSpanSize(RecyclerView recycler) {
		if(recycler.getLayoutManager() instanceof GridLayoutManager) {
			final GridLayoutManager layoutManager = (GridLayoutManager) recycler.getLayoutManager();
	        layoutManager.setSpanSizeLookup(new SpanSizeLookup() {
					
				@Override
				public int getSpanSize(int position) {
				    boolean isHeaderOrFooter =
				            isHeaderPosition(position) || isFooterPosition(position);
				        return isHeaderOrFooter ? layoutManager.getSpanCount() : 1;
				}
					
			});
		}
		
	}

	/**
	 * Setting the visibility of the header views
	 * 
	 * @param shouldShow
	 * @return void:
	 * @version 1.0 
	 * @date 2015-11-24
	 * @Author zhou.wenkai
	 */
	public void setHeaderVisibility(boolean shouldShow) {
		for (FixedViewInfo fixedViewInfo : mHeaderViewInfos) {
			fixedViewInfo.view.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
		}
		notifyDataSetChanged();
	}

	/**
	 * Setting the visibility of the footer views
	 * 
	 * @param shouldShow
	 * @return void:
	 * @version 1.0 
	 * @date 2015-11-24
	 * @Author zhou.wenkai
	 */
	public void setFooterVisibility(boolean shouldShow) {
		for (FixedViewInfo fixedViewInfo : mFooterViewInfos) {
			fixedViewInfo.view.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
		}
		notifyDataSetChanged();
	}

	/**
	 * get the count of headers
	 * 
	 * @return number of headers
	 * @version 1.0 
	 * @date 2015-11-24
	 * @Author zhou.wenkai
	 */
	public int getHeadersCount() {
		return mHeaderViewInfos.size();
	}

	/**
	 * get the count of footers
	 * 
	 * @return the number of footers
	 * @version 1.0 
	 * @date 2015-11-24
	 * @Author zhou.wenkai
	 */
	public int getFootersCount() {
		return mFooterViewInfos.size();
	}

	private boolean isHeader(int viewType) {
		return viewType >= BASE_HEADER_VIEW_TYPE 
				&& viewType < (BASE_HEADER_VIEW_TYPE + mHeaderViewInfos.size());
	}
	
	private boolean isFooter(int viewType) {
		return viewType >= BASE_FOOTER_VIEW_TYPE 
				&& viewType < (BASE_FOOTER_VIEW_TYPE + mFooterViewInfos.size());
	}
	
	private boolean isHeaderPosition(int position) {
		return position < mHeaderViewInfos.size();
	}
	
	private boolean isFooterPosition(int position) {
		return position >= mHeaderViewInfos.size() + mRealAdapter.getItemCount();
	}
	
	

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
		if (isHeader(viewType)) {
			int whichHeader = Math.abs(viewType - BASE_HEADER_VIEW_TYPE);
			View headerView = mHeaderViewInfos.get(whichHeader).view;
			return new RecyclerView.ViewHolder(headerView) { };
		} else if (isFooter(viewType)) {
			int whichFooter = Math.abs(viewType - BASE_FOOTER_VIEW_TYPE);
			View footerView = mFooterViewInfos.get(whichFooter).view;
			return new RecyclerView.ViewHolder(footerView) { };

		} else {
			return mRealAdapter.onCreateViewHolder(viewGroup, viewType);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
		if (position < mHeaderViewInfos.size()) {
			// Headers don't need anything special

		} else if (position < mHeaderViewInfos.size() + mRealAdapter.getItemCount()) {
			// This is a real position, not a header or footer. Bind it.
			mRealAdapter.onBindViewHolder(viewHolder, position - mHeaderViewInfos.size());

		} else {
			// Footers don't need anything special
		}
	}

	@Override
	public int getItemCount() {
		return mHeaderViewInfos.size() + mRealAdapter.getItemCount() + mFooterViewInfos.size();
	}

	@Override
	public int getItemViewType(int position) {
		if (isHeaderPosition(position)) {
			return mHeaderViewInfos.get(position).viewType;

		} else if (isFooterPosition(position)) {
			return mFooterViewInfos.get(position - mHeaderViewInfos.size()
					- mRealAdapter.getItemCount()).viewType;

		} else {
			return mRealAdapter.getItemViewType(position - mHeaderViewInfos.size());
		}
	}
}
