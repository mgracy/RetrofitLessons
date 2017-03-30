package com.mgx.retrofitlesson1.activity;

import android.database.Observable;
import android.graphics.Color;
import android.net.Network;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.mgx.retrofitlesson1.R;
import com.mgx.retrofitlesson1.adapter.ZhuangbiListAdapter;
import com.mgx.retrofitlesson1.model.RxJavaDemo.ZhuangbiImage;

import java.util.List;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;

/**
 * Created by glmgracy on 17/3/26.
 */

public class ElementaryFragment extends BaseFragment {

    @BindView(R.id.searchRb1)
    RadioButton searchBb1;
    @BindView(R.id.searchRb2)
    RadioButton searchRb2;
    @BindView(R.id.searchRb3)
    RadioButton searchRb3;
    @BindView(R.id.searchRb4)
    RadioButton searchRb4;
    @BindView(R.id.tipBt)
    Button tipBt;
    @BindView(R.id.gridRv)
    RecyclerView gridRv;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    public void aa(){
//        Observer<List<ZhuangbiImage>>
//        Observable<Long> observable = Observable
    }
    ZhuangbiListAdapter adapter = new ZhuangbiListAdapter();
//    Observer<List<ZhuangbiImage>>

//    Observer<List<ZhuangbiImage>>
//    Observable<List<ZhuangbiImage>> observable = new
//
//    Observer<List<ZhuangbiImage>> observer = new Observer() {
//        @Override
//        public void update(java.util.Observable o, Object arg) {
//
//        }
//    }


    @OnCheckedChanged({R.id.searchRb1, R.id.searchRb2, R.id.searchRb3, R.id.searchRb4})
    void OnTagChecked(RadioButton searchRb, boolean checked){
        if(checked){
            unsubscribe();
            adapter.setImages(null);
            swipeRefreshLayout.setRefreshing(false);
//            search(searchRb.getText().toString());
        }
    }
//
//    private void search(String key) {
//        subscription = Network.
//                .search(key)
//                .subscribeOn(Scheduler.io)
//                .observeOn(AndroidSchedulers.mainThread)
//                .subscribe(observer);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_elementary, container, false);
        ButterKnife.bind(this, view);
        gridRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        gridRv.setAdapter(adapter);

        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(false);

        return view;
    }

    @Override
    protected int getDialogRes() {
        return R.layout.dialog_elementary;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_elementary;
    }
}
