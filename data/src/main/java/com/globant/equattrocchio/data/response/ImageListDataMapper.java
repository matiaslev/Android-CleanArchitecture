package com.globant.equattrocchio.data.response;

import android.support.annotation.Nullable;

import com.globant.equattrocchio.domain.model.Image;
import com.globant.equattrocchio.domain.util.BaseDataMapper;

import java.util.ArrayList;
import java.util.List;

public class ImageListDataMapper extends BaseDataMapper<List<Image>, ImageListResponse, Void> {
    @Nullable
    @Override
    public List<Image> transform(ImageListResponse input, Void aVoid) {
        List<Image> imageEntities = new ArrayList<>();
        for (ImageResponse imageResponse : input.getImages()) {
            imageEntities.add(new ImageDataMapper().transform(imageResponse, null));
        }
        return imageEntities;
    }
}
