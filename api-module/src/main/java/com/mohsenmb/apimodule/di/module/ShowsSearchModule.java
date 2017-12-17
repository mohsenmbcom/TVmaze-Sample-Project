package com.mohsenmb.apimodule.di.module;

import com.mohsenmb.apimodule.interaction.ShowsSearchInteractor;
import com.mohsenmb.apimodule.interaction.ShowsSearchInteractorImpl;
import com.mohsenmb.apimodule.presentation.ShowsSearchPresenter;
import com.mohsenmb.apimodule.presentation.ShowsSearchPresenterImpl;
import com.mohsenmb.apimodule.view.ShowsSearchView;

import dagger.Module;
import dagger.Provides;

@Module
public class ShowsSearchModule {
    private ShowsSearchView view;

    public ShowsSearchModule(ShowsSearchView view) {
        this.view = view;
    }

    @Provides
    public ShowsSearchView provideShowsSearchView() {
        return view;
    }

    @Provides
    public ShowsSearchInteractor provideShowsSearchInteractor(ShowsSearchInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    public ShowsSearchPresenter provideShowsSearchPresenter(ShowsSearchPresenterImpl presenter) {
        presenter.setView(view);
        return presenter;
    }
}
