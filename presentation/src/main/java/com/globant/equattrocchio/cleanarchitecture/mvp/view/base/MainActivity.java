package com.globant.equattrocchio.cleanarchitecture.mvp.view.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImageListPresenter;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImageListView;
import com.globant.equattrocchio.data.ImagesServiceImpl;
import com.globant.equattrocchio.domain.useCases.GetLatestImagesUseCase;

public class MainActivity extends AppCompatActivity {

    private ImageListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetLatestImagesUseCase getLatestImagesUseCase = new GetLatestImagesUseCase(new ImagesServiceImpl());
        presenter = new ImageListPresenter(new ImageListView(this), getLatestImagesUseCase);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unregister();
    }
}