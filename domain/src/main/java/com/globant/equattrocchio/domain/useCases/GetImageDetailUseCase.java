package com.globant.equattrocchio.domain.useCases;

import com.globant.equattrocchio.domain.model.ImageEntity;
import com.globant.equattrocchio.domain.service.ImagesServices;

import io.reactivex.observers.DisposableObserver;

public class GetImageDetailUseCase extends UseCase<ImageEntity,Void> {

    private ImagesServices imagesServices;
    private int id;

    public GetImageDetailUseCase(ImagesServices imagesServices) {
        super();
        this.imagesServices = imagesServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<ImageEntity> observer, Void aVoid) {
        imagesServices.getImageDetail(id, observer);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
