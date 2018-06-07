package com.globant.equattrocchio.data.response;

import java.util.List;

public class ImageListResponse {
    private List<ImageResponse> images = null;

    public List<ImageResponse> getImages() {
        return images;
    }

    public void setImages(List<ImageResponse> images) {
        this.images = images;
    }

}