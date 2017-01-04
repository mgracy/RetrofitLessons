package com.mgx.retrofitlesson1.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.mgx.retrofitlesson1.R;
import com.mgx.retrofitlesson1.util.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by glmgr on 2017/1/3.
 */

public class SecondActivity extends BaseActivity {
    public static final int REQUEST_CODE = 1;

    @BindView(R.id.btnToThirdActivity)
    Button btnToThirdActivity;
    @BindView(R.id.btnImplicitIntent)
    Button btnImplicitIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnToThirdActivity)
    void toThirdActivity() {
//        openNewActivity(ThirdActivity.class);
        Intent intent = new Intent();
        intent.setClass(SecondActivity.this, ThirdActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.i(getClass().getSimpleName(), "onActivityResult before: TaskId: " + getTaskId() + ", ThreadId: " + Thread.currentThread().getId());
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                LogUtil.i(getClass().getSimpleName(), "onActivityResult: TaskId: " + getTaskId() + ", ThreadId: " + Thread.currentThread().getId() + ", and the return msg is " + data.getStringExtra(ThirdActivity.DATA_RETURN));
            }
        }
    }

    @OnClick(R.id.btnImplicitIntent)
    void openImplicitIntent(){
        LogUtil.d(getClass().getSimpleName(), "openImplicitIntent--begin: TaskId: " + getTaskId() + ", ThreadId: " + Thread.currentThread().getId());
        Intent intent = new Intent("com.mgx.abcd.a");
        intent.setDataAndType(Uri.parse("http://45.78.55.42:80"), "text/plain");
//        intent.setDataAndType(Uri.parse("http://45.78.55.42:80/images/cr7andmessi.jpg"), "image/jpg");
//        intent.addCategory("com.mgx.abcd.b");
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setType("text/plain");
        ComponentName componentName = intent.resolveActivity(getPackageManager());
        if (componentName != null) {
            startActivity(intent);
        }else{
            Toast.makeText(this, "Activity not found exception", Toast.LENGTH_SHORT).show();
        }
        LogUtil.d(getClass().getSimpleName(), "openImplicitIntent--end: TaskId: " + getTaskId() + ", ThreadId: " + Thread.currentThread().getId());
    }
}
