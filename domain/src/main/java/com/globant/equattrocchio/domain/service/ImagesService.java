package com.globant.equattrocchio.domain.service;

import com.globant.equattrocchio.domain.model.ImageEntity;

import java.util.List;

import io.reactivex.Observer;

public interface ImagesService {

    void getLatestImages(Observer<List<ImageEntity>> observer);
    void getImageDetail(int id, Observer<ImageEntity> observer);
}
