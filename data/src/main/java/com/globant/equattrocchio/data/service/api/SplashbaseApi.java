package com.globant.equattrocchio.data.service.api;

import com.globant.equattrocchio.data.response.Image;
import com.globant.equattrocchio.data.response.Result;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SplashbaseApi {

    @GET("api/v1/images/latest")
    Observable<Result> getImages();

    @GET("api/v1/images/{id}")
    Observable<Image> getImageDetail(@Path("id") int id);
}
