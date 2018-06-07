package com.globant.equattrocchio.cleanarchitecture.mvp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.domain.model.Image;

import java.util.List;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListViewHolder> {
    private List<Image> imageEntities;

    @Override
    public ImageListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageListViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(ImageListViewHolder holder, int position) {
        final Image image = imageEntities.get(position);
        holder.bind(image);
    }

    @Override
    public int getItemCount() {
        return imageEntities != null ? imageEntities.size() : 0;
    }

    public void setImageEntities(List<Image> imageEntities) {
        this.imageEntities = imageEntities;
        notifyDataSetChanged();
    }
}
