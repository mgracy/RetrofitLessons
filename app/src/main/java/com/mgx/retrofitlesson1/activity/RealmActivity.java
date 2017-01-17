package com.mgx.retrofitlesson1.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mgx.retrofitlesson1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

/**
 * Created by glmgracy on 17/1/17.
 */

public class RealmActivity extends BaseActivity {
    @BindView(R.id.btnInitRealm)
    Button btnInitRealm;
    @BindView(R.id.btnAddRealm)
    Button btnAddRealm;
    @BindView(R.id.btnDeleteRealm)
    Button btnDeleteRealm;
    @BindView(R.id.btnModifyRealm)
    Button btnModifyRealm;
    @BindView(R.id.btnQueryRealm)
    Button btnQueryRealm;
    private Realm mRealm;
    private static final String TAG = "RealmActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnInitRealm, R.id.btnAddRealm, R.id.btnDeleteRealm, R.id.btnModifyRealm, R.id.btnQueryRealm})
    void executeR(View view) {
        switch (view.getId()) {
            case R.id.btnInitRealm:
                Log.d(TAG, "executeR: you click Init Realm button");
                Realm.init(this);
                mRealm = Realm.getDefaultInstance();
                Log.d(TAG, "executeR: mRealm");
                break;
            case R.id.btnAddRealm:
                break;
            case R.id.btnDeleteRealm:
                break;
            case R.id.btnModifyRealm:
                break;
            case R.id.btnQueryRealm:
                break;
        }
    }

//
//    private void realmMethod(){
//        Realm.init(this);
//        mRealm = Realm.getDefaultInstance();
//
//        RealmConfiguration config = new RealmConfiguration.Builder()
//                .name("myRealm")
//                .schemaVersion(1)
//                .build();
//        Realm realm = Realm.getInstance(config);
//
//        //创建非持久化的Realm，保存在内存中，当应用关闭后即清除了
//        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
//                .name("myRealm")
//                .inMemory()
//                .build();
//
//        //使用 executeTransaction 方法插入数据
//        mRealm.executeTransaction(new Realm.Transaction(){
//
//            @Override
//            public void execute(Realm realm) {
//                Customer customer = realm.createObject(Customer.class);
//                customer.setName("gracy.ma");
//                customer.setAge(30);
//            }
//        });
//    }
}
