package com.mgx.retrofitlesson1.network;

import com.mgx.retrofitlesson1.network.api.FakeApi;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by glmgracy on 17/3/29.
 */

public class Network {
//    private static ZhuangbiApi zhuangbiApi;
//    private static GankApi gankApi;
//    private static FakeApi fakeApi;
//    private static OkHttpClient okHttpClient = new OkHttpClient();
//    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
//    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
//
//    public static ZhuangbiApi getZhuangbiApi() {
//        if (zhuangbiApi == null) {
//            Retrofit retrofit = new Retrofit.Builder()
//                    .client(okHttpClient)
//                    .baseUrl("http://www.zhuangbi.info/")
//                    .addConverterFactory(gsonConverterFactory)
//                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
//                    .build();
//            zhuangbiApi = retrofit.create(ZhuangbiApi.class);
//        }
//        return zhuangbiApi;
//    }
//
//    public static GankApi getGankApi() {
//        if (gankApi == null) {
//            Retrofit retrofit = new Retrofit.Builder()
//                    .client(okHttpClient)
//                    .baseUrl("http://gank.io/api/")
//                    .addConverterFactory(gsonConverterFactory)
//                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
//                    .build();
//            gankApi = retrofit.create(GankApi.class);
//        }
//        return gankApi;
//    }

//    public static FakeApi getFakeApi() {
//        if (fakeApi == null) {
//            fakeApi = new FakeApi();
//        }
//        return fakeApi;
//    }
}