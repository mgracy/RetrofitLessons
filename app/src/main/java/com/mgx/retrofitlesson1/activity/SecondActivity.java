package com.mgx.retrofitlesson1.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.mgx.retrofitlesson1.R;
import com.mgx.retrofitlesson1.model.User;
import com.mgx.retrofitlesson1.util.LogUtil;
import com.mgx.retrofitlesson1.util.UserManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by glmgr on 2017/1/3.
 */

public class SecondActivity extends BaseActivity {
    public static final int REQUEST_CODE = 1;
    public static  File outputTxt;
    @BindView(R.id.btnToThirdActivity)
    Button btnToThirdActivity;
    @BindView(R.id.btnImplicitIntent)
    Button btnImplicitIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        LogUtil.i(getClass().getSimpleName(), "onCreate: TaskId: " + getTaskId() + ", sUserId: " + UserManager.sUserId);
        doSerialize();
    }

    /**
     * Serialize 接口
     */
    private void doSerialize() {
        LogUtil.i(getClass().getSimpleName(), "doSerialize: start");
        User user = new User(0, "jake", true);
        try {
            outputTxt = new File(Environment.getExternalStorageDirectory(), "cache.txt");
            if(outputTxt.exists())
                outputTxt.delete();
            LogUtil.i(getClass().getSimpleName(), "doSerialize: path of outputTxt: " + outputTxt.getPath());
            outputTxt.createNewFile();

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outputTxt));
            out.writeObject(user);
            out.close();
            LogUtil.d(getClass().getSimpleName(), "doSerialize: TaskId: " + getTaskId() + ", ThreadId: " + Thread.currentThread().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtil.i(getClass().getSimpleName(), "doSerialize: end");
    }

    private void doDeserialization(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(outputTxt));
            User newUser = (User) in.readObject();
            in.close();
            LogUtil.d(getClass().getSimpleName(), "doDeserialization: TaskId: " + getTaskId() + ", ThreadId: " + Thread.currentThread().getId());
            Log.d("doDeserialization", "doDeserialization: UserId " + newUser.getUserId());
            Log.d("doDeserialization", "doDeserialization: UserName " + newUser.getUserName());
            Log.d("doDeserialization", "doDeserialization: isMale " + newUser.isIsMale());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示 intent 调用，并返回结果
     * 注意如果 REQUEST_CODE < 0，则认为客户端不期待有返回结果
     */
    @OnClick(R.id.btnToThirdActivity)
    void toThirdActivity() {
        LogUtil.d(getClass().getSimpleName(), "toThirdActivity: start");

        doDeserialization();
        LogUtil.d(getClass().getSimpleName(), "toThirdActivity: end");

//        openNewActivity(ThirdActivity.class);
//        Intent intent = new Intent();
//        intent.setClass(SecondActivity.this, ThirdActivity.class);
//        startActivityForResult(intent, REQUEST_CODE);
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

    /**
     * 隐式 intent 调用
     */
    @OnClick(R.id.btnImplicitIntent)
    void openImplicitIntent() {
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
        } else {
            Toast.makeText(this, "Activity not found exception", Toast.LENGTH_SHORT).show();
        }
        LogUtil.d(getClass().getSimpleName(), "openImplicitIntent--end: TaskId: " + getTaskId() + ", ThreadId: " + Thread.currentThread().getId());
    }
}
