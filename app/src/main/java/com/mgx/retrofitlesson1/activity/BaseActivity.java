package com.mgx.retrofitlesson1.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.mgx.retrofitlesson1.util.ActivityCollector;
import com.mgx.retrofitlesson1.util.LogUtil;

import java.util.Date;

/**
 * Created by glmgracy on 16/12/20.
 */

public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            LogUtil.i(this.getClass().getSimpleName(), "onCreate: time is " + savedInstanceState.getString("time") + ", and execute_user is " + savedInstanceState.getString("execute_user"));
        }
        ActivityCollector.addActivity(this);
        LogUtil.i(this.getClass().getSimpleName(), "onCreate: TaskID: " + this.getTaskId() + ", ThreadId: " + Thread.currentThread().getId());
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.i(this.getClass().getSimpleName(), "onStart: TaskID: " + this.getTaskId() + ", ThreadId: " + Thread.currentThread().getId());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.i(this.getClass().getSimpleName(), "onRestart: TaskID: " + this.getTaskId() + ", ThreadId: " + Thread.currentThread().getId());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            LogUtil.i(this.getClass().getSimpleName(), "onRestoreInstanceState: time is " + savedInstanceState.getString("time") + ", and execute_user is " + savedInstanceState.getString("execute_user"));
            return;
        }
        LogUtil.i(this.getClass().getSimpleName(), "onRestoreInstanceState: TaskID: " + this.getTaskId() + ", ThreadId: " + Thread.currentThread().getId());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String now = new Date().toString();
        outState.putString("time", now);
        outState.putString("execute_user", "gracy.ma");
        LogUtil.i(this.getClass().getSimpleName(), "onSaveInstanceState: time is " + outState.getString("time") + ", and execute_user is " + outState.getString("execute_user"));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        LogUtil.i(this.getClass().getSimpleName(), "onWindowFocusChanged: hasFocus is " + hasFocus );
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i(this.getClass().getSimpleName(), "onResume: TaskID: " + this.getTaskId() + ", ThreadId: " + Thread.currentThread().getId());
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.i(this.getClass().getSimpleName(), "onPause: TaskID: " + this.getTaskId() + ", ThreadId: " + Thread.currentThread().getId());
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        LogUtil.i(this.getClass().getSimpleName(), "onUserLeaveHint: TaskID: " + this.getTaskId() + ", ThreadId: " + Thread.currentThread().getId());
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.i(this.getClass().getSimpleName(), "onStop: TaskID: " + this.getTaskId() + ", ThreadId: " + Thread.currentThread().getId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i(this.getClass().getSimpleName(), "onDestroy: TaskID: " + this.getTaskId() + ", ThreadId: " + Thread.currentThread().getId());
        ActivityCollector.removeActivity(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtil.i(this.getClass().getSimpleName(), "onConfigurationChanged: " + newConfig.orientation);
    }

    public void openNewActivity(Class clazz){
        LogUtil.i(this.getClass().getSimpleName(), "openNewActivity: -------> " + clazz.getSimpleName());
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        startActivity(intent);
    }
}
