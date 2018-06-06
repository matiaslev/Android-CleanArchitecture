package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import com.globant.equattrocchio.cleanarchitecture.mvp.view.imageDetail.ImageDetailDialogFragment;
import com.globant.equattrocchio.data.ImagesServicesImpl;
import com.globant.equattrocchio.domain.model.ImageEntity;
import com.globant.equattrocchio.domain.useCases.GetImageDetailUseCase;

import io.reactivex.observers.DisposableObserver;

public class ImageDetailPresenter {

    private ImageDetailDialogFragment view;
    private GetImageDetailUseCase getImageDetailUseCase;
    private int imageId;

    public ImageDetailPresenter(ImageDetailDialogFragment view,
                                GetImageDetailUseCase getImageDetailUseCase,
                                int imageId) {
        this.view = view;
        this.getImageDetailUseCase = getImageDetailUseCase;
        this.imageId = imageId;
        init();
    }

    public void init() {
        getImageDetailUseCase.setId(imageId);
        getImageDetailUseCase.execute(new DisposableObserver<ImageEntity>() {
            @Override
            public void onNext(ImageEntity imageEntity) {
                onImageDetailResponse(imageEntity);
            }

            @Override
            public void onError(Throwable e) {
                //view.showError();
            }

            @Override
            public void onComplete() {
                new ImagesServicesImpl().getImageDetail(-1,null);
            }
        }, null);
    }

    private void onImageDetailResponse(ImageEntity imageEntity) {
        view.updateImageDetailDialogFragment(imageEntity);
    }
}
