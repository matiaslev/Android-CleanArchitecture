package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.getLatestImagesButtonObserver;
import com.globant.equattrocchio.data.response.Image;
import com.globant.equattrocchio.data.response.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImagesView extends ActivityView {

    @BindView(R.id.recycler_images)
    RecyclerView recyclerImages;
    private ImagesAdapter adapter = new ImagesAdapter();

    public ImagesView(AppCompatActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
        recyclerImages.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerImages.setAdapter(adapter);
    }

    public void showImageCards(Result result) {
        adapter.result = result;
        adapter.notifyDataSetChanged();
    }

    public void showImageDetailDialogFragment(String jsonImage) {
        ImageDialogFragment newFragment = new ImageDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("image", jsonImage);
        newFragment.setArguments(bundle);
        newFragment.show(getFragmentManager(), "Image");
    }

    @OnClick(R.id.btn_call_service)
    public void callServiceBtnPressed() {
        RxBus.post(new getLatestImagesButtonObserver.GetLatestImagesButtonPressed());
    }

    public void showError() {
        //todo: show toast with the error
    }
}
