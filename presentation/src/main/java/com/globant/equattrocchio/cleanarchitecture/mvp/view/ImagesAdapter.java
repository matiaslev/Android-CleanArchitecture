package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.getImageDetailButtonObserver;
import com.globant.equattrocchio.data.response.Image;
import com.globant.equattrocchio.data.response.Result;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesViewHolder> {
    public Result result;

    @Override
    public ImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImagesViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(ImagesViewHolder holder, int position) {
        final Image image = result.getImages().get(position);
        holder.bind(image);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.post(new getImageDetailButtonObserver
                        .GetImageDetailButtonPressed(image.getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return result != null ? result.getImages().size() : 0;
    }
}
