package com.mgx.retrofitlesson1.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.mgx.retrofitlesson1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by glmgracy on 16/12/20.
 */

public class ChooseAreaActivity extends BaseActivity {
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.list_view)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_area);
        ButterKnife.bind(this);
        initData();
    }

    private void initData(){
        titleText.setText("Hello Butterknife");
    }
}
