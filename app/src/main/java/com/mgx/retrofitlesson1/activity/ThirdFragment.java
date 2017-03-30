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

/**
 * Created by glmgracy on 17/3/30.
 */

public class ThirdFragment extends Fragment {
    private String context;
    private TextView mTextView;
    private static final String TAG = "ThirdFragment";
    public ThirdFragment(String context) {
        this.context = context;
    }

//    public FirstFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.third_fragment, container, false);
        mTextView = (TextView) view.findViewById(R.id.txtThirdContent);
        mTextView.setText(context);
        Log.d(TAG, "onCreateView: " + context);

        return view;
    }
}
