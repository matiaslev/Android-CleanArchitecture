package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import android.app.Activity;

import com.globant.equattrocchio.cleanarchitecture.mvp.view.imageDetail.ImageDetailDialogFragment;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.ImageDetailButtonObserver;
import com.globant.equattrocchio.data.ImagesServicesImpl;
import com.globant.equattrocchio.domain.model.ImageEntity;
import com.globant.equattrocchio.domain.useCases.GetImageDetailUseCase;

import io.reactivex.observers.DisposableObserver;

public class ImageDetailPresenter {

    private ImageDetailDialogFragment view;
    private GetImageDetailUseCase getImageDetailUseCase;

    public ImageDetailPresenter(ImageDetailDialogFragment view, GetImageDetailUseCase getImageDetailUseCase) {
        this.view = view;
        this.getImageDetailUseCase = getImageDetailUseCase;
    }

    private void onGetImageDetailResponse(ImageEntity imageEntity) {
        view.updateImageDetailDialogFragment(imageEntity);
    }

    public void onGetImageDetailButtonObserver(int id) {
        getImageDetailUseCase.setId(id);
        getImageDetailUseCase.execute(new DisposableObserver<ImageEntity>() {
            @Override
            public void onNext(ImageEntity imageEntity) {
                onGetImageDetailResponse(imageEntity);
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
}
