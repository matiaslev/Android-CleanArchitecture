package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.data.response.Result;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImagesView extends ActivityView {

    @BindView(R.id.recycler_images)
    RecyclerView recyclerImages;
    private Gson gson = new Gson();
    private ImagesAdapter adapter = new ImagesAdapter();

    public ImagesView(AppCompatActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
        recyclerImages.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerImages.setAdapter(adapter);
    }

    public void showImageCards(String jsonResult) {
        Result result =  gson.fromJson(jsonResult, Result.class);
        adapter.result = result;
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_call_service)
    public void callServiceBtnPressed() {
        RxBus.post(new CallServiceButtonObserver.CallServiceButtonPressed());
    }

    public void showError() {
        //todo: show toast with the error
    }
}
