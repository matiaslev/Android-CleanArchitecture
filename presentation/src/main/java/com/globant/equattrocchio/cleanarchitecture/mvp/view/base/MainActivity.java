package com.globant.equattrocchio.cleanarchitecture.mvp.view.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImagesPresenter;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.data.ImagesServicesImpl;
import com.globant.equattrocchio.domain.useCases.GetImageDetailUseCase;
import com.globant.equattrocchio.domain.useCases.GetLatestImagesUseCase;

public class MainActivity extends AppCompatActivity {

    private ImagesPresenter presenter;
    private GetLatestImagesUseCase getLatestImagesUseCase;
    private GetImageDetailUseCase getImageDetailUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLatestImagesUseCase = new GetLatestImagesUseCase(new ImagesServicesImpl());
        getImageDetailUseCase = new GetImageDetailUseCase(new ImagesServicesImpl());
        presenter = new ImagesPresenter(new ImagesView(this), getLatestImagesUseCase,
                getImageDetailUseCase);
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