package com.mohsenmb.apimodule.presentation;

import com.mohsenmb.apimodule.interaction.ShowsSearchInteractor;
import com.mohsenmb.apimodule.service.model.SearchShowItem;
import com.mohsenmb.apimodule.view.ShowsSearchView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.Subscriptions;

public class ShowsSearchPresenterImpl implements ShowsSearchPresenter {
    private ShowsSearchInteractor interactor;

    private ShowsSearchView view;
    private Subscription subscription;

    @Inject
    public ShowsSearchPresenterImpl(ShowsSearchInteractor interactor) {
        this.interactor = interactor;
        subscription = Subscriptions.empty();
    }

    @Override
    public void setView(ShowsSearchView view) {
        this.view = view;
    }

    @Override
    public void destroy() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        subscription = null;
        interactor.destroy();

        view = null;
        interactor = null;
    }

    @Override
    public void searchForShows(String query, final boolean isConnected) {
        if (view != null) {
            view.toggleProgress(true);
        }
        subscription = interactor
                .searchForShows(query)
                .subscribe(new Action1<List<SearchShowItem>>() {
                    @Override
                    public void call(List<SearchShowItem> searchShowItems) {
                        if (view != null) {
                            view.toggleProgress(false);
                            view.performShowsSearchResult(searchShowItems);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (view != null) {
                            view.toggleProgress(false);
                        }

                        if (isConnected) {
                            if (view != null) {
                                view.showRetry();
                            }
                        } else {
                            if (view != null) {
                                view.showOfflineMessage();
                            }
                        }
                    }
                });
    }
}
