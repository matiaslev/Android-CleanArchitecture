package com.globant.equattrocchio.domain.useCases;

import com.globant.equattrocchio.domain.model.ImageEntity;
import com.globant.equattrocchio.domain.service.ImagesService;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class GetLatestImagesUseCase extends UseCase<List<ImageEntity>, Void> {

    private ImagesService imagesService;

    public GetLatestImagesUseCase(ImagesService imagesService) {
        super();
        this.imagesService = imagesService;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<List<ImageEntity>> observer, Void aVoid) {
        imagesService.getLatestImages(observer);
    }
}
