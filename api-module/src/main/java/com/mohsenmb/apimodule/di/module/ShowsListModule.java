package com.mohsenmb.apimodule.di.module;

import com.mohsenmb.apimodule.interaction.ShowsListInteractor;
import com.mohsenmb.apimodule.interaction.ShowsListInteractorImpl;
import com.mohsenmb.apimodule.presentation.ShowsListPresenter;
import com.mohsenmb.apimodule.presentation.ShowsListPresenterImpl;
import com.mohsenmb.apimodule.view.ShowsListView;

import dagger.Module;
import dagger.Provides;

@Module
public class ShowsListModule {
    private ShowsListView view;

    public ShowsListModule(ShowsListView view) {
        this.view = view;
    }

    @Provides
    public ShowsListView provideShowsListView() {
        return view;
    }

    @Provides
    public ShowsListInteractor provideShowsListInteractor(ShowsListInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    public ShowsListPresenter provideShowsListPresenter(ShowsListPresenterImpl presenter) {
        presenter.setView(view);
        return presenter;
    }
}
