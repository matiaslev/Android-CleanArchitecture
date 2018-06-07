package com.globant.equattrocchio.cleanarchitecture.mvp.model;

public class ImageDetailDialogFragmentModel {

    private Integer imageId;
    private String imageUrl;

    public ImageDetailDialogFragmentModel(Integer imageId, String imageUrl) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }

    public Integer getImageId() {
        return imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
