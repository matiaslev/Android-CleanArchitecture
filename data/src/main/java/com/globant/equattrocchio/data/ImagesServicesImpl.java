package com.globant.equattrocchio.data;

import com.globant.equattrocchio.data.response.Image;
import com.globant.equattrocchio.data.response.ImageDataMapper;
import com.globant.equattrocchio.data.response.Result;
import com.globant.equattrocchio.data.response.ResultDataMapper;
import com.globant.equattrocchio.data.service.api.SplashbaseApi;
import com.globant.equattrocchio.domain.model.ImageEntity;
import com.globant.equattrocchio.domain.service.ImagesServices;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImagesServicesImpl implements ImagesServices {

    private static final String URL= "http://splashbase.co/";
    SplashbaseApi api;

    public ImagesServicesImpl() {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(URL).
                addConverterFactory(GsonConverterFactory.create())
                .build();
        api  = retrofit.create(SplashbaseApi.class);
    }

    @Override
    public void getLatestImages(final Observer<List<ImageEntity>> observer) {
        api.getImages().enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    observer.onNext(new ResultDataMapper().transform(response.body(), null));
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                //todo: update the UI with a connection error message
            }
        });
    }

    @Override
    public void getImageDetail(int id, final Observer<ImageEntity> observer) {
        api.getImageDetail(id).enqueue(new Callback<Image>() {
            @Override
            public void onResponse(Call<Image> call, Response<Image> response) {
                if (response.isSuccessful()) {
                    observer.onNext(new ImageDataMapper().transform(response.body(), null));
                }
            }

            @Override
            public void onFailure(Call<Image> call, Throwable t) {
                //todo: update the UI with a connection error message
            }
        });
    }
}
