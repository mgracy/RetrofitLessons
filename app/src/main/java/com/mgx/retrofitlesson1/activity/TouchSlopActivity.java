package com.mgx.retrofitlesson1.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

import com.mgx.retrofitlesson1.R;
import com.mgx.retrofitlesson1.util.LogUtil;

/**
 * Created by glmgracy on 17/1/8.
 */

public class TouchSlopActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_slop);
        /**
         * touch slop 是系统所能识别出的被认为是滑动的最小距离
         * 如果两次滑动之间的距离小于这个常量，则系统就不认为你是在进行滑动操作
         */
        int tc = new ViewConfiguration().getScaledTouchSlop(); //8dp
        Log.d("TouchSlopActivity", "onCreate: tc is " + tc);
        int touchSlop = (ViewConfiguration.get(getApplicationContext())).getScaledTouchSlop(); //12dp
        Log.d("TouchSlopActivity", "onCreate: touchSlop is " + touchSlop);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float x1 = event.getRawX();
        float y = event.getY();
        float y1 = event.getRawY();
        String str = "";

        /**
         * VelocityTracker 速度追踪
         * 速度 = （终点位置 - 起点位置）/ 时间段
         */

        //首先，在View的onTouchEvent方法中追踪当前单击事件的速度
        VelocityTracker velocityTracker = VelocityTracker.obtain();
        velocityTracker.addMovement(event);

        //
        velocityTracker.computeCurrentVelocity(1000);
        int xVelocity = (int) velocityTracker.getXVelocity();
        int yVelocity = (int) velocityTracker.getYVelocity();
        Log.d("onTouchEvent", "速度追踪: xVelocity: " + xVelocity + ", yVelocity: " + yVelocity);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                str += "ACTION_DOWN, ";
                break;
            case MotionEvent.ACTION_MOVE:
                str += "ACTION_MOVE, ";
                break;
            case MotionEvent.ACTION_UP:
                str += "ACTION_UP, ";
                break;
        }
        str += String.format("x: %f, x1: %f; y: %f, y1: %f", x, x1, y, y1);
        LogUtil.i(this.getClass().getSimpleName(), "onTouchEvent:" + str);
        velocityTracker.clear();
        velocityTracker.recycle();
        return super.onTouchEvent(event);
    }
}
