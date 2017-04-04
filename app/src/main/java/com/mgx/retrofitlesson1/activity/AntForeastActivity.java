package com.mgx.retrofitlesson1.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mgx.retrofitlesson1.R;
import com.mgx.retrofitlesson1.model.AntForeastDemo.AntForeast;
import com.mgx.retrofitlesson1.model.AntForeastDemo.AntForeastAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by glmgracy on 17/4/2.
 */

public class AntForeastActivity extends Activity {
    private static final String TAG = "AntForeastActivity";
    @BindView(R.id.tvAntTop)
    TextView tvAntTop;
    @BindView(R.id.lvAntForeast)
    ListView lvAntForeast;
    private String[] names = {"Apple", "Orange", "Watermelon", "Pair", "Banana"};
    List<AntForeast> lists = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antforeast);
        ButterKnife.bind(this);
        initAntForeast();
        initListView();
        lvAntForeast.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemClick: id is " + String.valueOf(id) + ", currentPosition is " + String.valueOf(position));
                AntForeast antForeast = lists.get(position);
                String name = antForeast.getTopName();
                Toast.makeText(AntForeastActivity.this, name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initListView() {
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, lists);
//        lvAntForeast.setAdapter(adapter);

        AntForeastAdapter adapter = new AntForeastAdapter(this, R.layout.layout_antforeast, lists);
        lvAntForeast.setAdapter(adapter);
    }

    private List<AntForeast> initAntForeast(){
        lists.add(new AntForeast(1, 1, "Jack", R.drawable.banner, "Y", "20.3kg"));
        lists.add(new AntForeast(2, 2, "Queen", R.drawable.myself_off, "Y", "18.3kg"));
        lists.add(new AntForeast(3, 3, "King", R.drawable.contact_off, "Y", "3kg"));
        lists.add(new AntForeast(4, 4, "One", R.drawable.discovery_off, "Y", "59g"));
        lists.add(new AntForeast(5, 5, "Two", R.drawable.mode, "Y", "0g"));
        lists.add(new AntForeast(6, 6, "Three", R.drawable.menu, "N", ""));
        return  lists;
    }
}

