package com.mgx.retrofitlesson1.activity;

import android.app.AlertDialog;
import android.app.Fragment;

import com.mgx.retrofitlesson1.R;


import org.reactivestreams.Subscription;

import butterknife.OnClick;

/**
 * Created by glmgracy on 17/3/25.
 */

public abstract class BaseFragment extends Fragment {
    protected Subscription subscription;

    @OnClick(R.id.tipBt)
    void tip(){
        new AlertDialog.Builder(getActivity())
                .setTitle(getTitleRes())
                .setView(getActivity().getLayoutInflater().inflate(getDialogRes(), null))
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }

    protected void unsubscribe(){
        if(subscription != null){
            subscription.cancel();
        }
    }
    protected abstract int getDialogRes();
    protected abstract int getTitleRes();
}
