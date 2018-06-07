package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import com.globant.equattrocchio.cleanarchitecture.mvp.model.ImageDetailDialogFragmentModel;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImageDetailDialogFragmentView;
import com.globant.equattrocchio.domain.model.Image;
import com.globant.equattrocchio.domain.useCases.GetImageDetailUseCase;

import io.reactivex.observers.DisposableObserver;

public class ImageDetailPresenter {

    private ImageDetailDialogFragmentView view;
    private GetImageDetailUseCase getImageDetailUseCase;
    private ImageDetailDialogFragmentModel imageDetailDialogFragmentModel;

    public ImageDetailPresenter(ImageDetailDialogFragmentView view,
                                GetImageDetailUseCase getImageDetailUseCase,
                                ImageDetailDialogFragmentModel imageDetailDialogFragmentModel) {
        this.view = view;
        this.getImageDetailUseCase = getImageDetailUseCase;
        this.imageDetailDialogFragmentModel = imageDetailDialogFragmentModel;
    }

    public void init() {
        view.updateImageDialogFragment(imageDetailDialogFragmentModel);
        getImageDetailUseCase.execute(new DisposableObserver<Image>() {
            @Override
            public void onNext(Image image) {
                imageDetailResponse(image);
            }

            @Override
            public void onError(Throwable e) {
                view.showError(e.getMessage());
                view.close();
            }

            @Override
            public void onComplete() {
                view.hideLoader();
            }
        }, imageDetailDialogFragmentModel.getImageId());
    }

    private void imageDetailResponse(Image image) {
        view.updateImageDetailDialogFragment(image);
    }
}
