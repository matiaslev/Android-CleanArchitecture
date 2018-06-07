package com.globant.equattrocchio.domain.useCases;

import com.globant.equattrocchio.domain.model.ImageEntity;
import com.globant.equattrocchio.domain.service.ImagesService;

import io.reactivex.observers.DisposableObserver;

public class GetImageDetailUseCase extends UseCase<ImageEntity,Void> {

    private ImagesService imagesService;
    private int id;

    public GetImageDetailUseCase(ImagesService imagesService) {
        super();
        this.imagesService = imagesService;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<ImageEntity> observer, Void aVoid) {
        imagesService.getImageDetail(id, observer);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
