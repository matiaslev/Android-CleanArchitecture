package com.globant.equattrocchio.domain.useCases;

import com.globant.equattrocchio.domain.model.Image;
import com.globant.equattrocchio.domain.service.ImagesService;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class GetLatestImagesUseCase extends UseCase<List<Image>, Void> {

    private ImagesService imagesService;

    public GetLatestImagesUseCase(ImagesService imagesService) {
        super();
        this.imagesService = imagesService;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<List<Image>> observer, Void aVoid) {
        imagesService.getLatestImages(observer);
    }
}
