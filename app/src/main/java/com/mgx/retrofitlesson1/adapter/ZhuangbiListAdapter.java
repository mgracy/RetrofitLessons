package com.mgx.retrofitlesson1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mgx.retrofitlesson1.R;
import com.mgx.retrofitlesson1.model.RxJavaDemo.ZhuangbiImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by glmgracy on 17/3/26.
 */

public class ZhuangbiListAdapter extends RecyclerView.Adapter {
    List<ZhuangbiImage> images;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
        ZhuangbiImage image = images.get(position);
        Glide.with(holder.itemView.getContext()).load(image.image_url).into(debounceViewHolder.imageTv);
        debounceViewHolder.descriptionTv.setText(image.description);
    }

    @Override
    public int getItemCount() {
        return images == null ? 0: images.size();
    }

    public void setImages(List<ZhuangbiImage> images) {
        this.images = images;
        notifyDataSetChanged();
    }

    static class DebounceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageIv)
        ImageView imageTv;
        @BindView(R.id.descriptionTv)
        TextView descriptionTv;

        public DebounceViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
