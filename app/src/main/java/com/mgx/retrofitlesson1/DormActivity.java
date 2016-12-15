package com.mgx.retrofitlesson1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by glmgr on 2016/12/15.
 */

public class DormActivity extends Activity {
    private static final String TAG = "DormActivity";
    private EditText etUrl;
    private Button btnGo;
    private WebView web_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dorm);
        Log.i(TAG, "onCreate: ");
        initUI();
    }

    protected void initUI(){
        etUrl= (EditText) findViewById(R.id.etUrl);
        btnGo = (Button) findViewById(R.id.btnGo);
        web_view = (WebView) findViewById(R.id.web_view);
        btnGo.setOnClickListener(mOnClickListener);
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            web_view.getSettings().setJavaScriptEnabled(true);
            web_view.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            web_view.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    web_view.loadUrl(request.getUrl().toString());
                    return true;
                }
            });
            web_view.loadUrl(etUrl.getText().toString().trim());
        }
    };

    //改写物理键返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(web_view.canGoBack()){
                web_view.goBack();//返回上一页面
                return true;
            }else{
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
