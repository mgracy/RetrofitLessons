package com.mgx.retrofitlesson1.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.mgx.retrofitlesson1.R;
import com.mgx.retrofitlesson1.model.WechatSession;
import com.mgx.retrofitlesson1.model.WechatSessionAdapter;
import com.mgx.retrofitlesson1.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by glmgracy on 17/3/30.
 */

public class SecondFragment extends Fragment {
    private String context;
    private TextView mTextView;
    private ListView mListView;
    public SecondFragment(String context) {
        this.context = context;
    }
    private WechatSessionAdapter adapter;
    List<WechatSession> lists = new ArrayList<>();

    private static final String TAG = "SecondFragment";
//    public FirstFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.second_fragment, container, false);
//        mTextView = (TextView) view.findViewById(R.id.txtSecondContent);
//        mTextView.setText(context);
//        Log.d(TAG, "onCreateView: " + context);
//        return view;

        View view = inflater.inflate(R.layout.activity_wechat_sessionlist, container, false);
        mListView = (ListView) view.findViewById(R.id.lvWechatSession);
        initWechatSession();
        adapter = new WechatSessionAdapter(getContext(), R.layout.list_wechat_session, lists);
        mListView.setAdapter(adapter);
        return view;
    }

        private List<WechatSession> initWechatSession() {
            lists.add(new WechatSession( R.drawable.wechat, "Jack", "adlfkajdsl", "Y", "20.3kg"));
            lists.add(new WechatSession( R.drawable.banner, "Queen", "sdfd", "Y", "18.3kg"));
            lists.add(new WechatSession( R.drawable.contact_on, "King", "sdfldk", "Y", "3kg"));
            lists.add(new WechatSession( R.drawable.ic_launcher, "One", "dsflkdsj", "Y", "59g"));
            lists.add(new WechatSession( R.drawable.title_btn_function, "Two", "dlfkjsdlfk", "Y", "0g"));
            lists.add(new WechatSession( R.drawable.search24, "Three", "dsflkjsdfl", "N", ""));
            return lists;
    }
}
