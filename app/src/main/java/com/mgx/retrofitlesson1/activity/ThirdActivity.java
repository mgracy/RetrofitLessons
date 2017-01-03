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

public class ThirdActivity extends BaseActivity {
    @BindView(R.id.btnToMainAty)
    Button btnToMainAty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnToMainAty)
    void toMainAty(){
        openNewActivity(MainActivity.class);
    }
}
