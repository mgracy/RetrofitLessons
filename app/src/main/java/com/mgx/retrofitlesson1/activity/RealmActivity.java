package com.mgx.retrofitlesson1.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mgx.retrofitlesson1.R;
import com.mgx.retrofitlesson1.model.Customer;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmResults;
import io.realm.RealmSchema;

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
//                Realm.init(this);
//                mRealm = Realm.getDefaultInstance();
//
                Realm.init(this);
                RealmConfiguration config = new RealmConfiguration.Builder()
                        .name("configRealm")
                        .schemaVersion(1)
                        .migration(new CustomerMigration()) //升级数据库
                        .build();
                mRealm = Realm.getInstance(config);

//                Realm.init(this);
//                RealmConfiguration inMemoryConfig = new RealmConfiguration.Builder()
//                        .name("inMemory")
//                        .inMemory()
//                        .build();
//                mRealm = Realm.getInstance(inMemoryConfig);
                Log.d(TAG, "初始化记录: mRealm");
                break;
            case R.id.btnAddRealm:
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        for (int i = 1; i < 3; i++) {
                            Customer customer = realm.createObject(Customer.class);
                            customer.setName(new Date().getTime() + " Gracy" + i);
                            customer.setSex(i%2);
                            customer.setAge(i);
                            Log.d(TAG, "增加记录(以createObject方式): 第 " + i + " 条");
                        }
                        for (int i = 5; i < 7; i++) {
                            realm.createObjectFromJson(Customer.class, "{name:\"FromJson\", sex: " + i%2 + ", age: " + i + "}");
                            Log.d(TAG, "增加记录(以createObjectFromJson方式): 第 " + i + " 条");
                        }
                    }
                });
                break;
            case R.id.btnDeleteRealm:
                final RealmResults<Customer> customersList = mRealm.where(Customer.class).findAll();
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        customersList.get(0).deleteFromRealm();
                        Log.d(TAG, "删除记录: delete first from realm");
                        customersList.deleteLastFromRealm();
                        Log.d(TAG, "删除记录: delete last from realm");
                        customersList.deleteAllFromRealm();
                        Log.d(TAG, "删除记录: delete all from realm");
                    }
                });
                break;
            case R.id.btnModifyRealm:
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Customer customer = mRealm.where(Customer.class).findFirst();
                        int age = customer.getAge();
                        customer.setAge(100);
                        Log.d(TAG, "修改记录: 原来的age为: " + age + ", 修改后为：" + customer.getAge());
                    }
                });
                break;
            case R.id.btnQueryRealm:
                RealmResults<Customer> filter = mRealm.where(Customer.class).equalTo("name", "Gracy").equalTo("age", 30).findAllAsync();
                for (Customer customer : filter) {
                    Log.d(TAG, "查询记录: filter " + customer.getName() + "--" + customer.getAge());
                }

                RealmResults<Customer> customers = mRealm.where(Customer.class).findAll();
                for (Customer customer : customers) {
                    Log.d(TAG, "查询记录: " + customer.getName() + "-" + String.valueOf(customer.getAge()));
                }

                if (customers.size() > 0) {
                    Log.d(TAG, "查询记录: 聚合查询");
                    Log.d(TAG, "查询记录: the sum age of customer is " + customers.sum("age").toString());
                    Log.d(TAG, "查询记录: the min age of customer is " + customers.min("age").toString());
                    Log.d(TAG, "查询记录: the max age of customer is " + customers.max("age").toString());
                    Log.d(TAG, "查询记录: the average age of customer is " + customers.average("age"));
                    Log.d(TAG, "查询记录: the size age of customer is " + customers.size());
                    Log.d(TAG, "查询第一条记录: name is " + customers.first().getName() + "，sex is " + customers.first().getSex() + ", age is " + customers.first().getAge());
                } else {
                    Log.d(TAG, "查询记录: customer is empty");
                }
                break;
        }
    }

    class CustomerMigration implements RealmMigration{

        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
            RealmSchema schema = realm.getSchema();
            if(oldVersion == 0 && newVersion == 1){
                RealmObjectSchema realmObjectSchema = schema.get("Customer");
                realmObjectSchema
                        .addField("sex", int.class);
                oldVersion++;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
