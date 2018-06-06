package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.LatestImagesButtonObserver;
import com.globant.equattrocchio.data.response.Result;
import com.globant.equattrocchio.domain.model.ImageEntity;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImagesView extends ActivityView {

    @BindView(R.id.recycler_images)
    RecyclerView recyclerImages;
    private ImagesAdapter adapter = new ImagesAdapter();
    private Gson gson = new Gson();

    public ImagesView(AppCompatActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
        recyclerImages.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerImages.setAdapter(adapter);
    }

    public void showImageCards(List<ImageEntity> imageEntities) {
        adapter.setImageEntities(imageEntities);
        adapter.notifyDataSetChanged();
    }

    public void showImageDetailDialogFragment(ImageEntity imageEntity) {
        ImageDialogFragment newFragment = new ImageDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("image", gson.toJson(imageEntity));
        newFragment.setArguments(bundle);
        newFragment.show(getFragmentManager(), "ImageEntity");
    }

    @OnClick(R.id.btn_call_service)
    public void callServiceBtnPressed() {
        RxBus.post(new LatestImagesButtonObserver.OnLatestImagesButtonPressed());
    }

    public void showError() {
        //todo: show toast with the error
    }
}
