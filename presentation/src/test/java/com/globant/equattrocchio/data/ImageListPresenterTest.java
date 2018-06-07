package com.globant.equattrocchio.data;

import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImageListPresenter;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImageListView;
import com.globant.equattrocchio.domain.useCases.GetLatestImagesUseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ImageListPresenterTest {
    private ImageListPresenter presenter;
    @Mock
    ImageListView view;
    @Mock
    GetLatestImagesUseCase getLatestImagesUseCase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new ImageListPresenter(view, getLatestImagesUseCase);
    }

    @Test
    public void should() {

    }
}