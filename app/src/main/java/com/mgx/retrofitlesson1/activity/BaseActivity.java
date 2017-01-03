package com.mgx.retrofitlesson1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mgx.retrofitlesson1.util.ActivityCollector;
import com.mgx.retrofitlesson1.util.LogUtil;

/**
 * Created by glmgracy on 16/12/20.
 */

public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        LogUtil.d(getClass().getSimpleName(), "onCreate: ThreadId: " + Thread.currentThread().getId() + ", TaskId: " + getTaskId());
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(getClass().getSimpleName(), "onStart: ThreadId: " + Thread.currentThread().getId() + ", TaskId: " + getTaskId());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d(getClass().getSimpleName(), "onRestart: ThreadId: " + Thread.currentThread().getId() + ", TaskId: " + getTaskId());
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(getClass().getSimpleName(), "onResume: ThreadId: " + Thread.currentThread().getId() + ", TaskId: " + getTaskId());
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d(getClass().getSimpleName(), "onPause: ThreadId: " + Thread.currentThread().getId() + ", TaskId: " + getTaskId());
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(getClass().getSimpleName(), "onStop: ThreadId: " + Thread.currentThread().getId() + ", TaskId: " + getTaskId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(getClass().getSimpleName(), "onDestroy: ThreadId: " + Thread.currentThread().getId() + ", TaskId: " + getTaskId());
        ActivityCollector.removeActivity(this);
    }

    public void openNewActivity(Class clazz){
        LogUtil.i(this.getClass().getSimpleName(), "openNewActivity: -------> " + clazz.getSimpleName());
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        startActivity(intent);
    }
}
