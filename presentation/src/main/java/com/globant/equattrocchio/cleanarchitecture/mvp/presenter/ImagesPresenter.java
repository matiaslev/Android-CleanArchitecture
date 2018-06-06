package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import android.app.Activity;

import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.ImageDetailButtonObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.LatestImagesButtonObserver;
import com.globant.equattrocchio.data.ImagesServicesImpl;
import com.globant.equattrocchio.data.response.Result;
import com.globant.equattrocchio.domain.model.ImageEntity;
import com.globant.equattrocchio.domain.useCases.GetImageDetailUseCase;
import com.globant.equattrocchio.domain.useCases.GetLatestImagesUseCase;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class ImagesPresenter {

    private Gson gson;
    private ImagesView view;
    private GetLatestImagesUseCase getLatestImagesUseCase;
    private GetImageDetailUseCase getImageDetailUseCase;

    public ImagesPresenter(ImagesView view, GetLatestImagesUseCase getLatestImagesUseCase,
                           GetImageDetailUseCase getImageDetailUseCase) {
        gson = new Gson();
        this.view = view;
        this.getLatestImagesUseCase = getLatestImagesUseCase;
        this.getImageDetailUseCase = getImageDetailUseCase;
    }

    public ImagesPresenter(ImagesView view, GetLatestImagesUseCase getLatestImagesUseCase) {
        this.view = view;
        this.getLatestImagesUseCase = getLatestImagesUseCase;
    }

    private void onGetLatestImageResponse(List<ImageEntity> imageEntities) {
        view.showImageCards(imageEntities);
    }

    private void onGetLatestImagesButtonPressed() {
        getLatestImagesUseCase.execute(new DisposableObserver<List<ImageEntity>>() {
            @Override
            public void onNext(@NonNull List<ImageEntity> imageEntities) {
                onGetLatestImageResponse(imageEntities);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.showError();
            }

            @Override
            public void onComplete() {
                new ImagesServicesImpl().getLatestImages(null);
            }
        },null);
    }

    private void onGetImageDetailResponse(ImageEntity imageEntity) {
        view.showImageDetailDialogFragment(imageEntity);
    }

    private void onGetImageDetailButtonObserver(int id) {
        getImageDetailUseCase.setId(id);
        getImageDetailUseCase.execute(new DisposableObserver<ImageEntity>() {
            @Override
            public void onNext(ImageEntity imageEntity) {
                onGetImageDetailResponse(imageEntity);
            }

            @Override
            public void onError(Throwable e) {
                view.showError();
            }

            @Override
            public void onComplete() {
                new ImagesServicesImpl().getImageDetail(-1,null);
            }
        }, null);
    }

    public void register() {
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }

        RxBus.subscribe(activity, new LatestImagesButtonObserver() {
            @Override
            public void onEvent(OnLatestImagesButtonPressed event) {
                onGetLatestImagesButtonPressed();
            }
        });

        RxBus.subscribe(activity, new ImageDetailButtonObserver() {
            @Override
            public void onEvent(OnImageDetailButtonPressed event) {
                onGetImageDetailButtonObserver(event.id);
            }
        });
    }

    public void unregister() {
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }
        RxBus.clear(activity);
    }
}
