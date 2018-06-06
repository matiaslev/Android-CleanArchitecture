package com.globant.equattrocchio.cleanarchitecture.mvp.view.imageDetail;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImageDetailPresenter;
import com.globant.equattrocchio.data.ImagesServicesImpl;
import com.globant.equattrocchio.domain.model.ImageEntity;
import com.globant.equattrocchio.domain.service.ImagesServices;
import com.globant.equattrocchio.domain.useCases.GetImageDetailUseCase;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageDetailDialogFragment extends DialogFragment {

    @BindView(R.id.progressBar_image_detail) ProgressBar progressBarImageDetail;
    @BindView(R.id.image) ImageView imageView;
    @BindView(R.id.text_id) TextView textId;
    @BindView(R.id.text_url) TextView textUrl;
    @BindView(R.id.text_large_url) TextView textLargeUrl;
    @BindView(R.id.text_source_id) TextView textSourceId;
    @BindView(R.id.text_copyright) TextView textCopyright;
    @BindView(R.id.text_site) TextView textSite;
    @BindView(R.id.button_back) Button buttonBack;

    private static final String image_id = "id";
    private View view;

    public static ImageDetailDialogFragment newInstance(int id) {
        ImageDetailDialogFragment imageDetailDialogFragment = new ImageDetailDialogFragment();

        Bundle args = new Bundle();
        args.putInt(image_id, id);
        imageDetailDialogFragment.setArguments(args);

        return imageDetailDialogFragment;
    }

    private ImageDetailPresenter imageDetailPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GetImageDetailUseCase getImageDetailUseCase = new GetImageDetailUseCase(new ImagesServicesImpl());
        imageDetailPresenter = new ImageDetailPresenter(this, getImageDetailUseCase);
        imageDetailPresenter.onGetImageDetailButtonObserver(getArguments().getInt(image_id));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_fragment_image, container, false);
        ButterKnife.bind(this, view);
        return view;
    }



    public void updateImageDetailDialogFragment(ImageEntity imageEntity) {
        Glide.with(view).load(imageEntity.getUrl()).into(imageView);
        progressBarImageDetail.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
        textId.setText(imageEntity.getId().toString());
        textUrl.setText(imageEntity.getUrl());
        textLargeUrl.setText(imageEntity.getLargeUrl());
        textSourceId.setText(imageEntity.getSourceId() != null ? imageEntity.getSourceId().toString() : "");
        textCopyright.setText(imageEntity.getCopyright());
        textSite.setText(imageEntity.getSite());
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().cancel();
            }
        });
    }

}