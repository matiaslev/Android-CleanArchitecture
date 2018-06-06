package com.globant.equattrocchio.data.response;

import android.support.annotation.Nullable;

import com.globant.equattrocchio.domain.model.ImageEntity;
import com.globant.equattrocchio.domain.util.BaseDataMapper;

public class ImageDataMapper extends BaseDataMapper<ImageEntity, Image, Object> {

    @Nullable
    @Override
    public ImageEntity transform(Image input, Object additionalArg) {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setId(input.getId());
        imageEntity.setUrl(input.getUrl());
        imageEntity.setLargeUrl(input.getLargeUrl());
        imageEntity.setSourceId(input.getSourceId());
        imageEntity.setCopyright(input.getCopyright());
        imageEntity.setSite(input.getSite());
        return imageEntity;
    }
}
