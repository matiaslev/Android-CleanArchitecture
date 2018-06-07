package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.ImageDetailDialogFragment;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.adapter.ImageListAdapter;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.LatestImagesButtonObserver;
import com.globant.equattrocchio.domain.model.Image;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageListView extends ActivityView {

    @BindView(R.id.recycler_images) RecyclerView recyclerImages;
    private String IMAGE_ENTITY_TAG = "ImageResponse Entity";
    private ImageListAdapter adapter = new ImageListAdapter();

    public ImageListView(AppCompatActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
        recyclerImages.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerImages.setAdapter(adapter);
    }

    public void showImageCards(List<Image> imageEntities) {
        adapter.setImageEntities(imageEntities);
    }

    public void showImageDetailDialogFragment(Image image) {
        ImageDetailDialogFragment.newInstance(image).show(getFragmentManager(),
                IMAGE_ENTITY_TAG);
    }

    @OnClick(R.id.btn_call_service)
    public void callServiceBtnPressed() {
        RxBus.post(new LatestImagesButtonObserver.OnLatestImagesButtonPressed());
    }

    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
