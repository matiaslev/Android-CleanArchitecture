package com.globant.equattrocchio.data.response;

import android.support.annotation.Nullable;

import com.globant.equattrocchio.domain.model.Image;
import com.globant.equattrocchio.domain.util.BaseDataMapper;

public class ImageDataMapper extends BaseDataMapper<Image, ImageResponse, Void> {

    @Nullable
    @Override
    public Image transform(ImageResponse input, Void aVoid) {
        Image image = new Image();
        image.setId(input.getId());
        image.setUrl(input.getUrl());
        image.setLargeUrl(input.getLargeUrl());
        image.setSourceId(input.getSourceId());
        image.setCopyright(input.getCopyright());
        image.setSite(input.getSite());
        return image;
    }
}
