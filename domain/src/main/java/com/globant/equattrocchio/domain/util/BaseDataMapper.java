package com.globant.equattrocchio.domain.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseDataMapper<O, I, AA> {

    @Nullable
    public abstract O transform(I input, AA additionalArg);

    @Nullable
    public O transform(I input) {
        return transform(input, null);
    }

    @NonNull
    public List<O> transform(@Nullable Collection<I> collection) {
        return transform(collection, null);
    }

    @NonNull
    public List<O> transform(@Nullable Collection<I> collection, AA additionalArg) {
        final List<O> list = new ArrayList<>();
        if (collection == null) {
            return list;
        }
        for (I element : collection) {
            final O item = transform(element, additionalArg);
            if (item != null) {
                list.add(item);
            }
        }
        return list;
    }
}
