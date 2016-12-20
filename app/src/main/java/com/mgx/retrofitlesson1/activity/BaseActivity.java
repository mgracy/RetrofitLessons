package com.mgx.retrofitlesson1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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
        LogUtil.i(this.getClass().getSimpleName(), "onCreate: Thread Id is " + Thread.currentThread().getId());
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.i(this.getClass().getSimpleName(), "onStart: Thread Id is " + Thread.currentThread().getId());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.i(this.getClass().getSimpleName(), "onRestart: Thread Id is " + Thread.currentThread().getId());
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i(this.getClass().getSimpleName(), "onResume: Thread Id is " + Thread.currentThread().getId());
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.i(this.getClass().getSimpleName(), "onPause: Thread Id is " + Thread.currentThread().getId());
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.i(this.getClass().getSimpleName(), "onStop: Thread Id is " + Thread.currentThread().getId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i(this.getClass().getSimpleName(), "onDestroy: Thread Id is " + Thread.currentThread().getId());
        ActivityCollector.removeActivity(this);
    }

    public void openNewActivity(Class clazz){
        LogUtil.i(this.getClass().getSimpleName(), "openNewActivity: -------> " + clazz.getSimpleName());
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        startActivity(intent);
    }
}
