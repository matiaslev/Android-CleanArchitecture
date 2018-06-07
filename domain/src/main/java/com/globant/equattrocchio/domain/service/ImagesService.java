package com.globant.equattrocchio.domain.service;

import com.globant.equattrocchio.domain.model.Image;

import java.util.List;

import io.reactivex.Observer;

public interface ImagesService {

    void getLatestImages(Observer<List<Image>> observer);
    void getImageDetail(int id, Observer<Image> observer);
}
