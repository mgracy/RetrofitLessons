package com.mgx.retrofitlesson1.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mgx.retrofitlesson1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by glmgracy on 17/4/27.
 */

public class WechatSessionAdapter extends ArrayAdapter<WechatSession> {
    private static final String TAG = "WechatSessionAdapter";
    private int resouceId;


    public WechatSessionAdapter(Context context, int resource, List<WechatSession> objects) {
        super(context, resource, objects);
        this.resouceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       WechatSession wechatSession = getItem(position);
        View view;
        ViewHolder viewHolder = new ViewHolder();

        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resouceId, null);
            viewHolder.imageView= (ImageView) view.findViewById(R.id.ivSessionImg);
            viewHolder.name = (TextView) view.findViewById(R.id.tvSessionName);
            viewHolder.description = (TextView) view.findViewById(R.id.tvSessionDescription);
            viewHolder.time = (TextView) view.findViewById(R.id.tvSessionTime);
            viewHolder.mute = (TextView) view.findViewById(R.id.tvSessionMute);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.imageView.setImageResource(wechatSession.getImage());
        viewHolder.name.setText(wechatSession.getName());
        viewHolder.description.setText(wechatSession.getDescription());
        viewHolder.time.setText(wechatSession.getTime());
        viewHolder.mute.setText(wechatSession.getMute());

        return view;
    }

    class ViewHolder{
        ImageView imageView;
        TextView name;
        TextView description;
        TextView time;
        TextView mute;
    }
}
