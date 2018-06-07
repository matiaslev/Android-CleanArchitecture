package com.globant.equattrocchio.cleanarchitecture.mvp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.ImageDetailButtonObserver;
import com.globant.equattrocchio.domain.model.Image;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_id)
    TextView textId;
    @BindView(R.id.image)
    ImageView imageView;

    private Image image;

    ImageListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Image image) {
        this.image = image;
        textId.setText(String.valueOf(image.getId()));
        Glide.with(itemView).load(image.getUrl()).into(imageView);
    }

    @OnClick(R.id.image)
    public void onImageClick() {
        RxBus.post(new ImageDetailButtonObserver.OnImageDetailButtonPressed(image));
    }
}
