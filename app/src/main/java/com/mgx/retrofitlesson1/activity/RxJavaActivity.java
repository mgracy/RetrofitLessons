package com.mgx.retrofitlesson1.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.mgx.retrofitlesson1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by glmgracy on 17/3/25.
 */

public class RxJavaActivity extends AppCompatActivity {

    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(android.R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);
        Log.i(this.getClass().getSimpleName(), "onCreate: Thread Id is " + Thread.currentThread().getId());
//        setSupportActionBar(toolBar);
        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        TestRx.main(new String[]{});
                        return new ElementaryFragment();
                    case 1:
                        return new ElementaryFragment();
                    default:
                        return new ElementaryFragment();
                }
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position){
                switch (position){
                    case 0:
                        return "基本";
                    case 1:
                        return "转换";
                    default:
                        return "aaa";
                }

            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }
}
