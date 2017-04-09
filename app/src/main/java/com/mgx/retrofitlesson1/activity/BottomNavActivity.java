package com.mgx.retrofitlesson1.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;

import com.mgx.retrofitlesson1.R;
import com.mgx.retrofitlesson1.model.PopupWindowDemo.ActionItem;
import com.mgx.retrofitlesson1.model.PopupWindowDemo.TitlePopup;
import com.mgx.retrofitlesson1.model.PopupWindowDemo.WechatContextMenuActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by glmgracy on 17/3/30.
 */

public class BottomNavActivity extends BaseActivity {
    private static final String TAG = "BottomNavActivity";
    @BindView(R.id.txt_top)
    TextView txtTop;
    @BindView(R.id.tab_title)
    RelativeLayout tabTitle;
    @BindView(R.id.txtWechat)
    TextView txtWechat;
    @BindView(R.id.txtContact)
    TextView txtContact;
    @BindView(R.id.txtDiscovery)
    TextView txtDiscovery;
    @BindView(R.id.txtMyself)
    TextView txtMyself;
    @BindView(R.id.tab_menu)
    LinearLayout tabMenu;
    @BindView(R.id.div_tab_bar)
    View divTabBar;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    private FirstFragment f1;
    private SecondFragment f2;
    private ThirdFragment f3;
    private FourthFragment f4;
    private FragmentManager fragmentManager;

    // 定义标题栏上的按钮
    private ImageButton titleBtn;

    // 定义标题栏弹窗按钮
    private TitlePopup titlePopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_bottom_nav);
        ButterKnife.bind(this);
        initView();
        initData();
    }
    
    @OnClick(R.id.btnPlus)
    void wechatContextMenu(View v){
        titlePopup.show(v);
    }

    @OnClick({R.id.txtWechat, R.id.txtContact, R.id.txtDiscovery, R.id.txtMyself})
    void clickBottomNav(View view) {
        Log.d(TAG, "clickBottomNav: " + view.getId());
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (view.getId()) {
            case R.id.txtWechat:
                selected();
                txtWechat.setSelected(true);
                if (f1 == null) {
                    Log.d(TAG, "clickBottomNav: f1 == null");
                    f1 = new FirstFragment("第一个Fragment");
                    transaction.add(R.id.fragment_container, f1);
                } else {
                    transaction.show(f1);
                }
                break;

            case R.id.txtContact:
                selected();
                txtContact.setSelected(true);
                if (f2 == null) {
                    f2 = new SecondFragment("第二个Fragment");
                    transaction.add(R.id.fragment_container, f2);
                } else {
                    transaction.show(f2);
                }
                break;

            case R.id.txtDiscovery:
                selected();
                txtDiscovery.setSelected(true);
                if (f3 == null) {
                    f3 = new ThirdFragment("第三个Fragment");
                    transaction.add(R.id.fragment_container, f3);
                } else {
                    transaction.show(f3);
                }
                break;

            case R.id.txtMyself:
                selected();
                txtMyself.setSelected(true);
                if (f4 == null) {
                    f4 = new FourthFragment("第四个Fragment");
                    transaction.add(R.id.fragment_container, f4);
                } else {
                    transaction.show(f4);
                }
                break;

        }
        transaction.commit();
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        if (f1 != null) {
            transaction.hide(f1);
        }
        if (f2 != null) {
            transaction.hide(f2);
        }
        if (f3 != null) {
            transaction.hide(f3);
        }
        if (f4 != null) {
            transaction.hide(f4);
        }
    }

    private void selected() {
        txtWechat.setSelected(false);
        txtContact.setSelected(false);
        txtDiscovery.setSelected(false);
        txtMyself.setSelected(false);
    }


    private void initView(){
        // 实例化标题栏弹窗
        titlePopup = new TitlePopup(this, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        titlePopup.setItemOnClickListener(new TitlePopup.OnItemOnClickListener() {
            @Override
            public void onItemClick(ActionItem item, int position) {
                switch (position){
                    case 0:
                        Toast.makeText(BottomNavActivity.this, "0", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(BottomNavActivity.this, "1", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(BottomNavActivity.this, "2", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(BottomNavActivity.this, "3", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
    /**
     * 初始化数据
     */
    private void initData() {
        // 给标题栏弹窗添加子类
        titlePopup.addAction(new ActionItem(this, "发起群聊", R.drawable.mm_title_btn_compose_normal));
        titlePopup.addAction(new ActionItem(this, "添加朋友", R.drawable.mm_title_btn_receiver_normal));
        titlePopup.addAction(new ActionItem(this, "扫一扫", R.drawable.mm_title_btn_qrcode_normal));
        titlePopup.addAction(new ActionItem(this, "收付款", R.drawable.mm_title_btn_keyboard_normal));
//        titlePopup.addAction(new ActionItem(this, "帮助与反馈", R.drawable.envelope24));
//        titlePopup.addAction(new ActionItem(this, "发起聊天", R.drawable.mm_title_btn_compose_normal));
//        titlePopup.addAction(new ActionItem(this, "听筒模式", R.drawable.mm_title_btn_receiver_normal));
//        titlePopup.addAction(new ActionItem(this, "登录网页", R.drawable.mm_title_btn_keyboard_normal));
//        titlePopup.addAction(new ActionItem(this, "扫一扫", R.drawable.mm_title_btn_qrcode_normal));
    }
}
