package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

public abstract class getImageDetailButtonObserver extends BusObserver<getImageDetailButtonObserver.GetImageDetailButtonPressed> {
    public getImageDetailButtonObserver() {
        super(GetImageDetailButtonPressed.class);
    }

    public static class GetImageDetailButtonPressed {
        public int id;

        public GetImageDetailButtonPressed(int id) {
            this.id = id;
        }
    }
}