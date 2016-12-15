package com.mgx.retrofitlesson1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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
    public static final String BASE_URL = "https://api.github.com/";
//    https://api.github.com/repos/square/retrofit/contributors
    public static final String mUSER = "mgracy";
    public static final String mOWNER = "square";
    public static final String mREPO = "retrofit";
    private TextView tvType, tvName, tvEmail;
    private Button btnLoadRepo, btnListContributor;
    private ListView list_view;
    GitHubService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private GitHubService buildRetrofit() {
        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
                .build();
        Log.d(TAG, "runRetrofit:");

        return retrofit.create(GitHubService.class);
    }

    private  void initUI(){
        tvType = (TextView) findViewById(R.id.tvType);
        tvName = (TextView) findViewById(R.id.tvName);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        list_view = (ListView) findViewById(R.id.list_view);
        btnLoadRepo = (Button) findViewById(R.id.load_repo);
        btnListContributor = (Button) findViewById(R.id.list_contributor);
        btnLoadRepo.setOnClickListener(mOnClickListener);
        btnListContributor.setOnClickListener(mOnClickListener);
    }

    private void clearItem() {
        tvEmail.setText("");
        tvType.setText("");
        tvName.setText("");
        tvEmail.setVisibility(View.GONE);
        tvType.setVisibility(View.GONE);
        tvName.setVisibility(View.GONE);
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.load_repo:
                    service = buildRetrofit();
                    Call<Repo> call = service.loadRepo(mUSER);

                    call.enqueue(new Callback<Repo>() {
                        @Override
                        public void onResponse(Call<Repo> call, Response<Repo> response) {
                            Log.d(TAG, "onResponse: Type is " + response.body().getType());
                            Log.d(TAG, "onResponse: Name is " + response.body().getName());
                            Log.d(TAG, "onResponse: Email is " + response.body().getEmail());
                            tvType.setText("  Type is " + response.body().getType());
                            tvName.setText("  Name is " + response.body().getName());
                            tvEmail.setText("  Email is " + response.body().getEmail());
                        }

                        @Override
                        public void onFailure(Call<Repo> call, Throwable t) {
                            Log.e(TAG, "onFailure: ", t );
                        }
                    });
                    break;
                case R.id.list_contributor:
                    clearItem();
                    service = buildRetrofit();
                    Call<List<Contributor>> call2 = service.contributorsByAddConverterGetCall(mOWNER, mREPO);
                    call2.enqueue(new Callback<List<Contributor>>() {
                        @Override
                        public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                            List<String> lists = new ArrayList<String>();
                            for (Contributor contributor : response.body()) {
                                lists.add("Login: " + contributor.getLogin() + "\nHtml_url: " +contributor.getHtml_url() +"\ncontributions: " + contributor.getContributions());
                                Log.d(TAG, "onResponse: Login is " + contributor.getLogin());
                                Log.d(TAG, "onResponse: Html_url is " + contributor.getHtml_url());
                                Log.d(TAG, "onResponse: contributions is " + contributor.getContributions());//
                            }

                            ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, lists);
                            list_view.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<List<Contributor>> call, Throwable t) {

                        }
                    });
                    break;
                default:
                    break;
            }
        }
    };
}
