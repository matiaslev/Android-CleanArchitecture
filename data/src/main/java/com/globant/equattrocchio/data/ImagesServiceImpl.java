package com.globant.equattrocchio.data;

import com.globant.equattrocchio.data.response.ImageResponse;
import com.globant.equattrocchio.data.response.ImageDataMapper;
import com.globant.equattrocchio.data.response.ImageListResponse;
import com.globant.equattrocchio.data.response.ImageListDataMapper;
import com.globant.equattrocchio.data.service.api.SplashbaseApi;
import com.globant.equattrocchio.domain.model.Image;
import com.globant.equattrocchio.domain.service.ImagesService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImagesServiceImpl implements ImagesService {

    private SplashbaseApi api;
    private ImageListDataMapper imageListDataMapper;
    private ImageDataMapper imageDataMapper;

    public ImagesServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api  = retrofit.create(SplashbaseApi.class);
    }

    @Override
    public void getLatestImages(final Observer<List<Image>> observer) {
        api.getImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<ImageListResponse>() {
                    @Override
                    public void onNext(ImageListResponse imageListResponse) {
                        observer.onNext(getImageListDataMapper().transform(imageListResponse, null));
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
    public void getImageDetail(int id, final Observer<Image> observer) {
        api.getImageDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<ImageResponse>() {
                    @Override
                    public void onNext(ImageResponse imageResponse) {
                        observer.onNext(getImageDataMapper().transform(imageResponse, null));
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

    private ImageListDataMapper getImageListDataMapper() {
        if (imageListDataMapper == null) {
            imageListDataMapper = new ImageListDataMapper();
        }
        return imageListDataMapper;
    }

    private ImageDataMapper getImageDataMapper() {
        if (imageDataMapper == null) {
            imageDataMapper = new ImageDataMapper();
        }
        return imageDataMapper;
    }
}
