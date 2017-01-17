package com.mgx.retrofitlesson1.service;

import android.content.Context;

import com.mgx.retrofitlesson1.model.Customer;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by glmgracy on 17/1/17.
 */

public class RealmService {

    private void realmMethod(){
        Realm.init(null);
        Realm mRealm = Realm.getDefaultInstance();

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myRealm")
                .schemaVersion(1)
                .build();
        Realm realm = Realm.getInstance(config);

        //创建非持久化的Realm，保存在内存中，当应用关闭后即清除了
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("myRealm")
                .inMemory()
                .build();

        //使用 executeTransaction 方法插入数据
        mRealm.executeTransaction(new Realm.Transaction(){

            @Override
            public void execute(Realm realm) {
                Customer customer = realm.createObject(Customer.class);
                customer.setName("gracy.ma");
                customer.setAge(30);
            }
        });

        //使用完需要关闭Realm
        mRealm.close();
    }
}
