package com.mgx.retrofitlesson1.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mgx.retrofitlesson1.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by glmgr on 2017/1/3.
 */

public class VoiceRecognizerActivity extends BaseActivity {
    @BindView(R.id.etInput)
    EditText etInput;
    @BindView(R.id.btnVoiceRecognizer)
    Button btnVoiceRecognizer;
    @BindView(R.id.btnToMain)
    Button btnToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voicerecognizer);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (matches.size() > 0) {
                    // 取第一个识别出的字符串，显示在EditText控件中
                    etInput.setText(matches.get(0));
                }
            }
        }
    }

    @OnClick(R.id.btnVoiceRecognizer)
    void voidRecognizer(View view) {
        Log.w(getClass().getSimpleName(), "voidRecognizer:");
        try {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "语音录入");
            startActivityForResult(intent, 1);
        } catch (Exception e) {
            String appPackageName = "com.google.android.googlequicksearchbox";
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                Log.w(getClass().getSimpleName(), "voidRecognizer: second try");
            } catch (ActivityNotFoundException anfe) {
                Log.w(getClass().getSimpleName(), "voidRecognizer: second exception");
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                Log.w(getClass().getSimpleName(), "voidRecognizer: second try exception");
            }
        }
    }

    @OnClick(R.id.btnToMain)
    void toMain(View view){
        openNewActivity(MainActivity.class);
    }
}
