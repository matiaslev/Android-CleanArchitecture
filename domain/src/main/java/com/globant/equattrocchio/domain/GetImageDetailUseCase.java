package com.globant.equattrocchio.domain;

import com.globant.equattrocchio.domain.service.ImagesServices;

import io.reactivex.observers.DisposableObserver;

public class GetImageDetailUseCase extends UseCase<String,Void> {

    private ImagesServices imagesServices;
    private int id;

    public GetImageDetailUseCase(ImagesServices imagesServices) {
        super();
        this.imagesServices = imagesServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<String> observer, Void aVoid) {
        imagesServices.getImageDetail(id, observer);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
