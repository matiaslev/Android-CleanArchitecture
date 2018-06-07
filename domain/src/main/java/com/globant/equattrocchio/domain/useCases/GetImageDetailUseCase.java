package com.globant.equattrocchio.domain.useCases;

import com.globant.equattrocchio.domain.model.Image;
import com.globant.equattrocchio.domain.service.ImagesService;

import io.reactivex.observers.DisposableObserver;

public class GetImageDetailUseCase extends UseCase<Image, Integer> {

    private ImagesService imagesService;

    public GetImageDetailUseCase(ImagesService imagesService) {
        super();
        this.imagesService = imagesService;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<Image> observer, Integer id) {
        imagesService.getImageDetail(id, observer);
    }

}
