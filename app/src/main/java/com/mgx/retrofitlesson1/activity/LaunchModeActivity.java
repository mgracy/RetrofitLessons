package com.mgx.retrofitlesson1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mgx.retrofitlesson1.R;
import com.mgx.retrofitlesson1.util.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by glmgracy on 17/1/1.
 */

public class LaunchModeActivity extends BaseActivity {
    @BindView(R.id.tvStandard)
    TextView tvStandard;
    @BindView(R.id.tvSingleTop)
    TextView tvSingleTop;
    @BindView(R.id.tvSingleTask)
    TextView tvSingleTask;
    @BindView(R.id.tvSingleInstance)
    TextView tvSingleInstance;
    @BindView(R.id.btnSingleTask)
    Button btnSingleTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launchmode);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tvStandard, R.id.tvSingleTop, R.id.tvSingleTask, R.id.tvSingleInstance, R.id.btnSingleTask})
    void launchActivity(View view) {
        String str = "";
        switch (view.getId()) {
            case R.id.tvStandard:
                str = "standard：标准模式，这也是系统的默认模式。每次启动一个 Activity 都会重新创\n" +
                        "建一个新的实例，不管这个实例是否已经存在。";
                break;
            case R.id.tvSingleTop:
                str = "singleTop：栈顶复用模式。在这种模式下，如果新 Activity 已经位于任务栈的栈\n" +
                        "顶，那么此 Activity 不会被重新创建，同时它的 onNewIntent 方法会被回调，通过此方法的\n" +
                        "参数我们可以取出当前请求的信息。需要注意的是，这个 Activity 的 onCreate、onStart 不\n" +
                        "会被系统调用，因为它并没有发生改变。如果新 Activity 的实例已存在但不是位于栈顶，\n" +
                        "那么新 Activity 仍然会重新重建。举个例子，假设目前栈内的情况为 ABCD，其中 ABCD\n" +
                        "为四个 Activity，A 位于栈底，D 位于栈顶，这个时候假设要再次启动 D，如果 D 的启动\n" +
                        "模式为 singleTop，那么栈内的情况仍然为 ABCD；如果 D 的启动模式为 standard，那么由\n" +
                        "于 D 被重新创建，导致栈内的情况就变为 ABCDD。";
                break;
            case R.id.tvSingleTask:
                str = "singleTask：栈内复用模式。这是一种单实例模式，在这种模式下，只要 Activity\n" +
                        "在一个栈中存在，那么多次启动此 Activity 都不会重新创建实例，和 singleTop 一样，系统\n" +
                        "也会回调其 onNewIntent。具体一点，当一个具有 singleTask 模式的 Activity 请求启动后，\n" +
                        "比如 Activity A，系统首先会寻找是否存在 A 想要的任务栈，如果不存在，就重新创建一\n" +
                        "个任务栈，然后创建 A 的实例后把 A 放到栈中。如果存在 A 所需的任务栈，这时要看 A\n" +
                        "是否在栈中有实例存在，如果有实例存在，那么系统就会把 A 调到栈顶并调用它的\n" +
                        "onNewIntent 方法，如果实例不存在，就创建 A 的实例并把 A 压入栈中。";
                break;
            case R.id.tvSingleInstance:
                str = "singleInstance：单实例模式。这是一种加强的 singleTask 模式，它除了具有 singleTask\n" +
                        "模式的所有特性外，还加强了一点，那就是具有此种模式的 Activity 只能单独地位于一个\n" +
                        "任务栈中，换句话说，比如 Activity A 是 singleInstance 模式，当 A 启动后，系统会为它创\n" +
                        "建一个新的任务栈，然后 A 独自在这个新的任务栈中，由于栈内复用的特性，后续的请求\n" +
                        "均不会创建新的 Activity，除非这个独特的任务栈被系统销毁了。";
                break;
            case R.id.btnSingleTask:
                Intent intent = new Intent(LaunchModeActivity.this, LaunchModeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                LogUtil.w(this.getClass().getSimpleName(), "launchActivity: Thread Id is " + Thread.currentThread().getId() + ", TaskID is " + this.getTaskId());
                break;
            default:
                break;
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

    }

}
