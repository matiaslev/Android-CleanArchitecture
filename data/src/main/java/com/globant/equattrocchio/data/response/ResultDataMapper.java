package com.globant.equattrocchio.data.response;

import android.support.annotation.Nullable;

import com.globant.equattrocchio.domain.model.ImageEntity;
import com.globant.equattrocchio.domain.util.BaseDataMapper;

import java.util.ArrayList;
import java.util.List;

public class ResultDataMapper extends BaseDataMapper<List<ImageEntity>, Result, Void> {
    @Nullable
    @Override
    public List<ImageEntity> transform(Result input, Void aVoid) {
        List<ImageEntity> imageEntities = new ArrayList<>();
        for (Image image : input.getImages()) {
            imageEntities.add(new ImageDataMapper().transform(image, null));
        }
        return imageEntities;
    }
}
