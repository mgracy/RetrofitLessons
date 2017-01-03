package com.mgx.retrofitlesson1.activity;

import android.os.Bundle;
import android.widget.Button;

import com.mgx.retrofitlesson1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by glmgr on 2017/1/3.
 */

public class SecondActivity extends BaseActivity {
    @BindView(R.id.btnToThirdActivity)
    Button btnToThirdActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnToThirdActivity)
    void toThirdActivity() {
        openNewActivity(ThirdActivity.class);
    }
}
