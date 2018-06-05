package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

public abstract class ImageDetailButtonObserver extends BusObserver<ImageDetailButtonObserver.OnImageDetailButtonPressed> {
    public ImageDetailButtonObserver() {
        super(OnImageDetailButtonPressed.class);
    }

    public static class OnImageDetailButtonPressed {
        public int id;

        public OnImageDetailButtonPressed(int id) {
            this.id = id;
        }
    }
}