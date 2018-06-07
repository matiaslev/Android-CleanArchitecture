package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.model.ImageDetailDialogFragmentModel;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.ImageDetailDialogFragment;
import com.globant.equattrocchio.domain.model.Image;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageDetailDialogFragmentView extends FragmentView {

    @BindView(R.id.progressBar_image_detail) ProgressBar progressBarImageDetail;
    @BindView(R.id.image) ImageView imageView;
    @BindView(R.id.text_id) TextView textId;
    @BindView(R.id.text_url) TextView textUrl;
    @BindView(R.id.text_large_url) TextView textLargeUrl;
    @BindView(R.id.text_source_id) TextView textSourceId;
    @BindView(R.id.text_copyright) TextView textCopyright;
    @BindView(R.id.text_site) TextView textSite;

    public ImageDetailDialogFragmentView(ImageDetailDialogFragment fragment) {
        super(fragment);
        ButterKnife.bind(this, fragment.getView());
    }

    public void updateImageDialogFragment(ImageDetailDialogFragmentModel imageDetailDialogFragmentModel) {
        if (getFragmentView() != null) {
            Glide.with(getFragmentView()).load(imageDetailDialogFragmentModel.getImageUrl()).into(imageView);
        }
        textId.setText(imageDetailDialogFragmentModel.getImageId().toString());
        textUrl.setText(imageDetailDialogFragmentModel.getImageUrl());
    }

    public void updateImageDetailDialogFragment(Image image) {
        imageView.setVisibility(View.VISIBLE);
        textLargeUrl.setText(image.getLargeUrl());
        textSourceId.setText(image.getSourceId() != null ? image.getSourceId().toString() : "");
        textCopyright.setText(image.getCopyright());
        textSite.setText(image.getSite());
    }

    @OnClick(R.id.button_back)
    public void onButtonBackClick() {
        close();
    }

    public void hideLoader() {
        progressBarImageDetail.setVisibility(View.GONE);
    }

    public void close() {
        if (getFragment() != null) {
            getDialogFragment().getDialog().cancel();
        }
    }

    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
