package com.mgx.retrofitlesson1.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.mgx.retrofitlesson1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by glmgracy on 17/4/4.
 */

public class AntForeastInviteDialog extends Activity {
    @BindView(R.id.btnSendInvite)
    Button btnSendInvite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_antforeast_invite);
        ButterKnife.bind(this);
    }
    
    @OnClick(R.id.btnSendInvite)
    void SendInvite(){
        Toast.makeText(this, "邀请已发出", Toast.LENGTH_SHORT).show();
    }
}
