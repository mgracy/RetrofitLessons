package com.mgx.retrofitlesson1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {
    //    - Base URL: 总是以 /结尾
//
//    - @Url: 不要以 / 开头
    private static final String TAG = "MainActivity";
    public static final String sUrl = "https://api.github.com/";
    public static final String path = "mgracy";
    private TextView tvType, tvName, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        runRetrofit();
    }

    private void runRetrofit() {
        Log.d(TAG, "runRetrofit:");
//        OkHttpClient client = new OkHttpClient();
//        client.interceptors().add(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//               Response response = chain.proceed(chain.request());
//                return response;
//            }
//        });


        Log.i(getClass().getSimpleName(), "runRetrofit: Thread Id is " + Thread.currentThread().getId());
        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl(sUrl)
                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
                .build();
        Log.d(TAG, "runRetrofit:");

        GitHubService service = retrofit.create(GitHubService.class);
        Call<Repo> call2 = service.loadRepo(path);
        //同步请求会阻塞线程
//        Repo repo = call2.execute();

        call2.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(Call<Repo> call, Response<Repo> response) {
                Log.d(TAG, "onResponse: Type is " + response.body().getType());
                Log.d(TAG, "onResponse: Name is " + response.body().getName());
                Log.d(TAG, "onResponse: Email is " + response.body().getEmail());
                tvType.setText("Type is " + response.body().getType());
                tvName.setText("Type is " + response.body().getName());
                tvEmail.setText("Type is " + response.body().getEmail());
            }

            @Override
            public void onFailure(Call<Repo> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t );
            }
        });

        Log.d(TAG, "runRetrofit: ending.");
    }

    private  void initUI(){
        tvType = (TextView) findViewById(R.id.tvType);
        tvName = (TextView) findViewById(R.id.tvName);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
    }
}
