package com.globant.equattrocchio.cleanarchitecture.mvp.view.base;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.model.ImageDetailDialogFragmentModel;
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImageDetailPresenter;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImageDetailDialogFragmentView;
import com.globant.equattrocchio.data.ImagesServiceImpl;
import com.globant.equattrocchio.domain.model.Image;
import com.globant.equattrocchio.domain.useCases.GetImageDetailUseCase;

public class ImageDetailDialogFragment extends DialogFragment {

    private static final String IMAGE_ID = "imageId";
    private static final String IMAGE_URL = "imageUrl";
    private int imageId;
    private String imageUrl;

    public static ImageDetailDialogFragment newInstance(Image image) {
        ImageDetailDialogFragment imageDetailDialogFragment = new ImageDetailDialogFragment();

        Bundle args = new Bundle();
        args.putInt(IMAGE_ID, image.getId());
        args.putString(IMAGE_URL, image.getUrl());
        imageDetailDialogFragment.setArguments(args);

        return imageDetailDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageId = getArguments().getInt(IMAGE_ID);
        imageUrl = getArguments().getString(IMAGE_URL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_image, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ImageDetailDialogFragmentView imageDetailDialogFragmentView = new ImageDetailDialogFragmentView(this);

        GetImageDetailUseCase getImageDetailUseCase = new GetImageDetailUseCase(new ImagesServiceImpl());

        ImageDetailDialogFragmentModel imageDetailDialogFragmentModel =
                new ImageDetailDialogFragmentModel(imageId, imageUrl);

        ImageDetailPresenter imageDetailPresenter = new ImageDetailPresenter(
                imageDetailDialogFragmentView, getImageDetailUseCase, imageDetailDialogFragmentModel);

        imageDetailPresenter.init();
    }
}
