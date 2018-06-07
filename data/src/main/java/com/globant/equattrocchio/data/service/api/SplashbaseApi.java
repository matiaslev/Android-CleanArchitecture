package com.globant.equattrocchio.data.service.api;

import com.globant.equattrocchio.data.response.ImageResponse;
import com.globant.equattrocchio.data.response.ImageListResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SplashbaseApi {

    @GET("api/v1/images/latest")
    Observable<ImageListResponse> getImages();

    @GET("api/v1/images/{id}")
    Observable<ImageResponse> getImageDetail(@Path("id") int id);
}
