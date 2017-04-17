package com.mgx.retrofitlesson1.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;

import com.mgx.retrofitlesson1.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by glmgracy on 17/4/13.
 */

public class MyRefreshLayout extends SwipeRefreshLayout implements AbsListView.OnScrollListener {
    private static final String TAG = "MyRefreshLayout";
    /**
     * 滑动到最下面时的上拉操作
     */
    private int mTouchSlop;
    /**
     * listview实例
     */
    private ListView mListView;
    /**
     * 上拉监听器, 到了最底部的上拉加载操作
     */
    private OnLoadListener mOnLoadListener;
    /**
     * ListView的加载中footer
     */
    private View mListViewFooter;

    /**
     * 按下时的y坐标
     */
    private int mYDown;
    /**
     * 抬起时的y坐标, 与mYDown一起用于滑动到底部时判断是上拉还是下拉
     */
    private  int mLastY;
    /**
     * 是否在加载中 ( 上拉加载更多 )
     */
    private boolean isLoading = false;

    public MyRefreshLayout(Context context) {
        this(context, null);
    }

    @SuppressLint("InflateParams")
    public MyRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mListViewFooter = LayoutInflater.from(context).inflate(
                R.layout.listview_footer, null, false
        );
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d(TAG, "onLayout: mListView is null " + String.valueOf(mListView == null));
        super.onLayout(changed, left, top, right, bottom);
        if(mListView == null){
            getListView();
        }
    }

    private void getListView() {
        int childs = getChildCount();

        if(childs > 0){
            View childView = getChildAt(0);
            Log.d(TAG, "getListView: childs: " + childs);
            Log.d(TAG, "getListView: childView instanceof ListView " + String.valueOf(childView instanceof ListView));
            Log.d(TAG, "getListView: mListView is null " + String.valueOf(mListView == null));

            if(childView instanceof ListView){
                mListView = (ListView) childView;
                // 设置滚动监听器给ListView, 使得滚动的情况下也可以自动加载
                mListView.setOnScrollListener(this);
                Log.d(TAG, "getListView: ");
            }
        }
    }

    public void setLoading(boolean loading){
        isLoading = loading;
        if(isLoading){
            mListView.addFooterView(mListViewFooter);
        }else{
            mListView.removeFooterView(mListViewFooter);
            mYDown = 0;
            mLastY = 0;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        
        switch (action){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent: Down " + new Date().toString());
                mYDown = (int) ev.getRawY();
                break;
            
            case MotionEvent.ACTION_MOVE:
                mLastY = (int) ev.getRawY();
                break;
            
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent: Up " + new Date().toString());
                int incr = (int) ev.getRawY() - mYDown;
                Log.d(TAG, "dispatchTouchEvent: incr: " + String.valueOf(incr));
                if(canLoad()){
                    loadData();
                }
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(ev);
    }
    /**
     * 如果到了最底部,而且是上拉操作.那么执行onLoad方法
     */
    private void loadData() {
        if (mOnLoadListener != null) {
            setLoading(true);

            mOnLoadListener.onLoad();
        }
    }

    public void setOnLoadListener(OnLoadListener loadListener){
        mOnLoadListener = loadListener;
    }
    /**
     * 是否可以加载更多, 条件是到了最底部, listview不在加载中, 且为上拉操作.
     *
     * @return
     */
    private boolean canLoad() {
        Log.d(TAG, "canLoad: isLoading: " + String.valueOf(isLoading));
        return isBottom() && !isLoading && isPullup();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // 滚动时到了最底部也可以加载更多
        if(canLoad()){
            loadData();
        }
    }

    /**
     * Set Refresh
     * @return
     */
    public static void setRefreshing(SwipeRefreshLayout refreshLayout,
                                     boolean refreshing, boolean notify){
        Class<? extends  SwipeRefreshLayout>refreshLayoutClass = refreshLayout.getClass();
        if (refreshLayoutClass != null) {
            try{
                Method setRefreshing = refreshLayoutClass.getDeclaredMethod(
                        "setRefreshing", boolean.class, boolean.class
                );
                setRefreshing.setAccessible(true);
                setRefreshing.invoke(refreshLayout, refreshing, notify);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean isBottom() {
        Log.d(TAG, "isBottom: 1");
        if(mListView !=null && mListView.getAdapter() !=null){
            boolean a = mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount()-1);
            Log.d(TAG, "isBottom: a" + String.valueOf(a));
            return mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount()-1);
        }
        return false;
    }

    /**
     * 是否是上拉操作 
     *
     * @return
     */
    public boolean isPullup() {
        Log.d(TAG, "isPullup: mYDown:" + String.valueOf(mYDown) + ": mLastY:" + String.valueOf(mLastY)+ ": mTouchSlop:" + String.valueOf(mTouchSlop));

        boolean a = (mYDown - mLastY) >= mTouchSlop;
        Log.d(TAG, "isPullup: a " + String.valueOf(a));
        return (mYDown - mLastY) >= mTouchSlop;
    }

    public static interface OnLoadListener{
        public void onLoad();
    }
}
