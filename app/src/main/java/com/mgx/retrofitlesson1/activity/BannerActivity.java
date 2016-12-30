package com.mgx.retrofitlesson1.activity;

import android.net.Uri;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mgx.retrofitlesson1.R;

/**
 * Created by glmgr on 2016/12/30.
 */

public class BannerActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_banner);
        Uri uri = Uri.parse("http://45.78.55.42/images/chinajoygirl.jpg");
        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
        draweeView.setImageURI(uri);
    }
}
