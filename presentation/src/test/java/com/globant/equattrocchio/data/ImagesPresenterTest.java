package com.globant.equattrocchio.data;

import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImagesPresenter;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ImagesPresenterTest {
    private ImagesPresenter presenter;
    @Mock
    ImagesView view;
    @Mock
    GetLatestImagesUseCase getLatestImagesUseCase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new ImagesPresenter(view, getLatestImagesUseCase);
    }

    @Test
    public void should() {

    }
}