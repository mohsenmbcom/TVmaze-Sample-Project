package com.mohsenmb.apimodule.di.module;

import com.mohsenmb.apimodule.interaction.ShowDetailsInteractor;
import com.mohsenmb.apimodule.interaction.ShowDetailsInteractorImpl;
import com.mohsenmb.apimodule.presentation.ShowDetailsPresenter;
import com.mohsenmb.apimodule.presentation.ShowDetailsPresenterImpl;
import com.mohsenmb.apimodule.view.ShowDetailsView;

import dagger.Module;
import dagger.Provides;

@Module
public class ShowDetailsModule {
    private ShowDetailsView view;

    public ShowDetailsModule(ShowDetailsView view) {
        this.view = view;
    }

    @Provides
    public ShowDetailsView provideShowDetailsView() {
        return view;
    }

    @Provides
    public ShowDetailsInteractor provideShowDetailsInteractor(ShowDetailsInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    public ShowDetailsPresenter provideShowDetailsPresenter(ShowDetailsPresenterImpl presenter) {
        presenter.setView(view);
        return presenter;
    }
}
