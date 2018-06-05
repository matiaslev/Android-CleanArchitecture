package com.globant.equattrocchio.domain.service;

import io.reactivex.Observer;

public interface ImagesServices {

    void getLatestImages(Observer<String> observer);
    void getImageDetail(int id, Observer<String> observer);
}
