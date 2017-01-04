package com.mgx.retrofitlesson1.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.mgx.retrofitlesson1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by glmgr on 2017/1/4.
 */

public class ShareActivity extends BaseActivity {
    @BindView(R.id.ivPhoto)
    ImageView ivPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);
    }
}
