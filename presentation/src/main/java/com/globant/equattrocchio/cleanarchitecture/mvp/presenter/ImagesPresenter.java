package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import android.app.Activity;

import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.getImageDetailButtonObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.getLatestImagesButtonObserver;
import com.globant.equattrocchio.data.ImagesServicesImpl;
import com.globant.equattrocchio.data.response.Image;
import com.globant.equattrocchio.data.response.Result;
import com.globant.equattrocchio.domain.GetImageDetailUseCase;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;
import com.google.gson.Gson;

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

    private void onGetLatestImageResponse(String jsonResult) {
        view.showImageCards(gson.fromJson(jsonResult, Result.class));
    }

    private void onGetLatestImagesButtonPressed() {
        getLatestImagesUseCase.execute(new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String jsonResult) {
                onGetLatestImageResponse(jsonResult);
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

    private void onGetImageDetailResponse(String jsonImage) {
        view.showImageDetailDialogFragment(jsonImage);
    }

    private void onGetImageDetailButtonObserver(int id) {
        getImageDetailUseCase.id = id;
        getImageDetailUseCase.execute(new DisposableObserver<String>() {
            @Override
            public void onNext(String jsonImage) {
                onGetImageDetailResponse(jsonImage);
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

        RxBus.subscribe(activity, new getLatestImagesButtonObserver() {
            @Override
            public void onEvent(GetLatestImagesButtonPressed event) {
                onGetLatestImagesButtonPressed();
            }
        });

        RxBus.subscribe(activity, new getImageDetailButtonObserver() {
            @Override
            public void onEvent(GetImageDetailButtonPressed event) {
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
