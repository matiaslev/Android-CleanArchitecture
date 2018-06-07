package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import android.app.Activity;

import com.globant.equattrocchio.cleanarchitecture.mvp.view.imageList.ImageListView;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.ImageDetailButtonObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.LatestImagesButtonObserver;
import com.globant.equattrocchio.domain.model.ImageEntity;
import com.globant.equattrocchio.domain.useCases.GetLatestImagesUseCase;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class ImageListPresenter {

    private ImageListView view;
    private GetLatestImagesUseCase getLatestImagesUseCase;

    public ImageListPresenter(ImageListView view, GetLatestImagesUseCase getLatestImagesUseCase) {
        this.view = view;
        this.getLatestImagesUseCase = getLatestImagesUseCase;
    }

    private void getLatestImageResponse(List<ImageEntity> imageEntities) {
        view.showImageCards(imageEntities);
    }

    private void onLatestImagesButtonPressed() {
        getLatestImagesUseCase.execute(new DisposableObserver<List<ImageEntity>>() {
            @Override
            public void onNext(@NonNull List<ImageEntity> imageEntities) {
                getLatestImageResponse(imageEntities);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.showError(e.getMessage());
            }

            @Override
            public void onComplete() {

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
                onLatestImagesButtonPressed();
            }
        });

        RxBus.subscribe(activity, new ImageDetailButtonObserver() {
            @Override
            public void onEvent(OnImageDetailButtonPressed event) {
                view.showImageDetailDialogFragment(event.id);
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
