package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

public abstract class LatestImagesButtonObserver extends BusObserver<LatestImagesButtonObserver.OnLatestImagesButtonPressed> {
    public LatestImagesButtonObserver() {
        super(OnLatestImagesButtonPressed.class);
    }

    public static class OnLatestImagesButtonPressed {
    }
}