package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.ImageDetailButtonObserver;
import com.globant.equattrocchio.data.response.Image;
import com.globant.equattrocchio.data.response.Result;
import com.globant.equattrocchio.domain.model.ImageEntity;

import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesViewHolder> {
    private List<ImageEntity> imageEntities;

    @Override
    public ImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImagesViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(ImagesViewHolder holder, int position) {
        final ImageEntity image = imageEntities.get(position);
        holder.bind(image);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.post(new ImageDetailButtonObserver.OnImageDetailButtonPressed(image.getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageEntities != null ? imageEntities.size() : 0;
    }

    public List<ImageEntity> getImageEntities() {
        return imageEntities;
    }

    public void setImageEntities(List<ImageEntity> imageEntities) {
        this.imageEntities = imageEntities;
    }
}
