package com.mgx.retrofitlesson1.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mgx.retrofitlesson1.R;
import com.mgx.retrofitlesson1.model.Contributor;
import com.mgx.retrofitlesson1.model.Repo;
import com.mgx.retrofitlesson1.service.GitHubService;
import com.mgx.retrofitlesson1.util.LogUtil;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity {
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
    private EditText etSavaInstanceState;
    private Button btnLoadRepo, btnListContributor, toHSProject, toMenu, ibBanner, toLaunchMode, toVoice, toSecondAty;
    private ListView list_view;
    GitHubService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private GitHubService buildRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LogUtil.i(this.getClass().getSimpleName(), "buildRetrofit: Thread Id is " + Thread.currentThread().getId());

        return retrofit.create(GitHubService.class);
    }

    private void initUI() {
        tvType = (TextView) findViewById(R.id.tvType);
        tvName = (TextView) findViewById(R.id.tvName);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        etSavaInstanceState = (EditText) findViewById(R.id.etSavaInstanceState);
        list_view = (ListView) findViewById(R.id.list_view);
        btnLoadRepo = (Button) findViewById(R.id.load_repo);
        btnListContributor = (Button) findViewById(R.id.list_contributor);
        toHSProject = (Button) findViewById(R.id.toHSProject);
        toMenu = (Button) findViewById(R.id.toMenu);
        ibBanner = (Button) findViewById(R.id.ibBanner);
        toLaunchMode = (Button) findViewById(R.id.toLaunchMode);
        toVoice = (Button) findViewById(R.id.toVoice);
        toSecondAty = (Button) findViewById(R.id.toSecondAty);
        btnLoadRepo.setOnClickListener(mOnClickListener);
        btnListContributor.setOnClickListener(mOnClickListener);
//        toHSProject.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                LogUtil.i(this.getClass().getSimpleName(), "onTouch: motionEvent(" + motionEvent.getAction() + ")");
//                return false;
//            }
//        });
        toHSProject.setOnClickListener(mOnClickListener);
        toMenu.setOnClickListener(mOnClickListener);
        ibBanner.setOnClickListener(mOnClickListener);
        toLaunchMode.setOnClickListener(mOnClickListener);
        toVoice.setOnClickListener(mOnClickListener);
        toSecondAty.setOnClickListener(mOnClickListener);
    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        Log.d(TAG, "onTouchEvent: ");
//        switch (event.getAction()){
//            case MotionEvent.ACTION_UP:
//                Log.d(TAG, "onTouchEvent: ACTION_UP");
//                break;
//            case MotionEvent.EDGE_LEFT:
//                Log.d(TAG, "onTouchEvent: EDGE_LEFT");
//                break;
//            case MotionEvent.ACTION_DOWN:
//                Log.d(TAG, "onTouchEvent: ACTION_DOWN");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.d(TAG, "onTouchEvent: ACTION_MOVE");
//                break;
//            case MotionEvent.ACTION_BUTTON_PRESS:
//                Log.d(TAG, "onTouchEvent: ACTION_BUTTON_PRESS");
//                break;
//            case MotionEvent.ACTION_BUTTON_RELEASE:
//                Log.d(TAG, "onTouchEvent: ACTION_BUTTON_RELEASE");
//                break;
//            default:
//                break;
//        }
//        return true;
//    }
//

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
            switch (view.getId()) {
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
                            Log.e(TAG, "onFailure: ", t);
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
                                lists.add("Login: " + contributor.getLogin() + "\t\tcontributions: " + contributor.getContributions());
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
                case R.id.toHSProject:

                    LogUtil.i(this.getClass().getSimpleName(), "onClick: ");
//                    Intent intent = new Intent(MainActivity.this, DormActivity.class);
//                    startActivity(intent);
//                    openNewActivity(ChooseAreaActivity.class);
                    try{
                        SQLiteDatabase db = LitePal.getDatabase();
                        Toast.makeText(MainActivity.this, "Connect db successfully", Toast.LENGTH_SHORT).show();
                    }catch (Exception ex){
                        ex.printStackTrace();
                        LogUtil.i(this.getClass().getSimpleName(), "onClick: Thread Id is " + Thread.currentThread().getId() + "\t" + ex.getMessage());
                        Toast.makeText(MainActivity.this, "Failed to connect db", Toast.LENGTH_SHORT).show();
                    }
                break;
                case R.id.toMenu:
                    openNewActivity(SlidingActivity.class);
                    break;
                case R.id.ibBanner:
                    openNewActivity(SensorActivity.class);
//                    openNewActivity(BannerActivity.class);
//                    Toast.makeText(MainActivity.this, "This function is under construction...", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.toVoice:
                    openNewActivity(VoiceRecognizerActivity.class);
//                    openNewActivity(BannerActivity.class);
//                    Toast.makeText(MainActivity.this, "This function is under construction...", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.toSecondAty:
                    openNewActivity(SecondActivity.class);
                    break;
                case R.id.toLaunchMode:
                    openNewActivity(LaunchModeActivity.class);
                    break;
                default:
                    break;
            }
        }
    };
}
