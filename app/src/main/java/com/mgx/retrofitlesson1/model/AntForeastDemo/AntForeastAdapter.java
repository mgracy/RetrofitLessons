package com.mgx.retrofitlesson1.model.AntForeastDemo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mgx.retrofitlesson1.R;
import com.mgx.retrofitlesson1.activity.AntForeastInviteDialog;
import com.mgx.retrofitlesson1.util.LogUtil;

import java.util.List;

/**
 * Created by glmgracy on 17/4/4.
 */

public class AntForeastAdapter extends ArrayAdapter<AntForeast> {
    private static final String TAG = "AntForeastAdapter";
    private int resouceId;

    public AntForeastAdapter(Context context, int resource, List<AntForeast> objects) {
        super(context, resource, objects);
        this.resouceId = resource;
        LogUtil.d(this.getClass().getSimpleName(), "AntForeastAdapter-resource:" + String.valueOf(resource));
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AntForeast antForeast = getItem(position);
        Log.i(TAG, "getView: " + Thread.currentThread().getId() + ", antForeast is null: " + String.valueOf(antForeast == null));
        View view;
        //缓存view
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resouceId, null);
            viewHolder = new ViewHolder();
//            viewHolder.topId = (TextView) view.findViewById(R.id.tvTopNum);
            viewHolder.topNum = (TextView) view.findViewById(R.id.tvTopNum);
            viewHolder.topName = (TextView) view.findViewById(R.id.tvTopName);
            viewHolder.topImage = (ImageView) view.findViewById(R.id.tvTopImage);
//            viewHolder.topIsUsed = (TextView) view.findViewById(R.id.tvTopUnit);
            viewHolder.topUnit = (TextView) view.findViewById(R.id.tvTopUnit);
            viewHolder.topInvite = (Button) view.findViewById(R.id.btnTopInvite);
            view.setTag(viewHolder);

        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

//        viewHolder.topId.setText(antForeast.getId());
        viewHolder.topName.setText(antForeast.getTopName());
        viewHolder.topImage.setImageResource(antForeast.getTopImage());
//        viewHolder.topIsUsed.setText(antForeast.getIsUsed());
        if (antForeast.getIsUsed() == "Y") {
            viewHolder.topInvite.setVisibility(View.GONE);

            viewHolder.topUnit.setText(antForeast.getTopUnit());
        } else {
            viewHolder.topNum.setTextColor(Color.WHITE);
            viewHolder.topUnit.setText("");
            viewHolder.topInvite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    new AlertDialog.Builder(getContext())
//                            .setTitle("")
//                            .setView(R.layout.dialog_antforeast_invite)
//                            .show();
//                    new AlertDialog.Builder(getActivity())
//                            .setTitle(getTitleRes())
//                            .setView(getActivity().getLayoutInflater().inflate(getDialogRes(), null))
//                            .show();
                        Intent intent = new Intent(getContext(), AntForeastInviteDialog.class);
                        getContext().startActivity(intent);
                }
            });
        }
        viewHolder.topNum.setText(String.valueOf(antForeast.getTopNum()));


        return view;
    }

    class ViewHolder {
        ImageView topImage;
        TextView topNum;
        TextView topName;
        //        TextView topId;
        TextView topIsUsed;
        TextView topUnit;
        Button topInvite;
    }
}
