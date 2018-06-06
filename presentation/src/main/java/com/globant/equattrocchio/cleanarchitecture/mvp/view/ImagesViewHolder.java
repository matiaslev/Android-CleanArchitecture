package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.data.response.Image;
import com.globant.equattrocchio.domain.model.ImageEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_id)
    TextView textId;
    @BindView(R.id.image)
    ImageView imageView;

    private RequestManager requestManager;

    ImagesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        requestManager = Glide.with(itemView);
    }

    public void bind(ImageEntity image) {
        textId.setText(image.getId().toString());
        requestManager.load(image.getUrl()).into(imageView);
    }
}
