package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

public abstract class getLatestImagesButtonObserver extends BusObserver<getLatestImagesButtonObserver.GetLatestImagesButtonPressed> {
    public getLatestImagesButtonObserver() {
        super(GetLatestImagesButtonPressed.class);
    }

    public static class GetLatestImagesButtonPressed {
    }
}