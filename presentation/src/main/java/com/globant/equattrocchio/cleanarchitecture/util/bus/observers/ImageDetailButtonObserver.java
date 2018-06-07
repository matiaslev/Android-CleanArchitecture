package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

import com.globant.equattrocchio.domain.model.Image;

public abstract class ImageDetailButtonObserver extends BusObserver<ImageDetailButtonObserver.OnImageDetailButtonPressed> {
    public ImageDetailButtonObserver() {
        super(OnImageDetailButtonPressed.class);
    }

    public static class OnImageDetailButtonPressed {
        public Image image;

        public OnImageDetailButtonPressed(Image image) {
            this.image = image;
        }
    }
}