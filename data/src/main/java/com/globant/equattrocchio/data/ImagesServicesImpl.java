package com.globant.equattrocchio.data;

import com.globant.equattrocchio.data.response.Image;
import com.globant.equattrocchio.data.response.ImageDataMapper;
import com.globant.equattrocchio.data.response.Result;
import com.globant.equattrocchio.data.response.ResultDataMapper;
import com.globant.equattrocchio.data.service.api.SplashbaseApi;
import com.globant.equattrocchio.domain.model.ImageEntity;
import com.globant.equattrocchio.domain.service.ImagesServices;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImagesServicesImpl implements ImagesServices {

    private static final String URL= "http://splashbase.co/";
    SplashbaseApi api;
    private ResultDataMapper resultDataMapper;
    private ImageDataMapper imageDataMapper;

    public ImagesServicesImpl() {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(URL).
                addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api  = retrofit.create(SplashbaseApi.class);
    }

    @Override
    public void getLatestImages(final Observer<List<ImageEntity>> observer) {
        api.getImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Result>() {
                    @Override
                    public void onNext(Result result) {
                        observer.onNext(getResultDataMapper().transform(result, null));
                    }

                    @Override
                    public void onError(Throwable e) {
                        observer.onError(new RuntimeException("getLatestImages service error"));
                    }

                    @Override
                    public void onComplete() {
                        observer.onComplete();
                    }
                });
    }

    @Override
    public void getImageDetail(int id, final Observer<ImageEntity> observer) {
        api.getImageDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Image>() {
                    @Override
                    public void onNext(Image image) {
                        observer.onNext(getImageDataMapper().transform(image, null));

                    }

                    @Override
                    public void onError(Throwable e) {
                        observer.onError(new RuntimeException("getImageDetail service error"));
                    }

                    @Override
                    public void onComplete() {
                        observer.onComplete();
                    }
                });
    }

    private ResultDataMapper getResultDataMapper() {
        if (resultDataMapper == null) {
            resultDataMapper = new ResultDataMapper();
        }
        return resultDataMapper;
    }

    private ImageDataMapper getImageDataMapper() {
        if (imageDataMapper == null) {
            imageDataMapper = new ImageDataMapper();
        }
        return imageDataMapper;
    }
}
