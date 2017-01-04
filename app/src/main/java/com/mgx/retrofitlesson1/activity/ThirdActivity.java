package com.mgx.retrofitlesson1.activity;

import android.content.Intent;
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
    public static final String DATA_RETURN = "data_return";
    @BindView(R.id.btnToMainAty)
    Button btnToMainAty;
    @BindView(R.id.btnToSecondAty)
    Button btnToSecondAty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnToMainAty)
    void toMainAty() {
        openNewActivity(MainActivity.class);
    }
    @OnClick(R.id.btnToSecondAty)
    void toSecondAty(){
        Intent intent = new Intent();
        intent.putExtra(DATA_RETURN, getResources().getString(R.string.open_second_activity));
        setResult(RESULT_OK, intent);
        finish();
    }
}
