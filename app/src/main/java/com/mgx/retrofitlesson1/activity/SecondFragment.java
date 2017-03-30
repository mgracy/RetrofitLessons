package com.mgx.retrofitlesson1.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mgx.retrofitlesson1.R;
import com.mgx.retrofitlesson1.util.LogUtil;

/**
 * Created by glmgracy on 17/3/30.
 */

public class SecondFragment extends Fragment {
    private String context;
    private TextView mTextView;
    public SecondFragment(String context) {
        this.context = context;
    }

    private static final String TAG = "SecondFragment";
//    public FirstFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        mTextView = (TextView) view.findViewById(R.id.txtSecondContent);
        mTextView.setText(context);
        Log.d(TAG, "onCreateView: " + context);
        return view;
    }
}
