package com.mohsenmb.apimodule.presentation;

import com.mohsenmb.apimodule.interaction.ShowsListInteractor;
import com.mohsenmb.apimodule.service.model.Show;
import com.mohsenmb.apimodule.view.ShowsListView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.Subscriptions;

public class ShowsListPresenterImpl implements ShowsListPresenter {
    private ShowsListInteractor interactor;

    private ShowsListView view;
    private Subscription subscription;


    @Inject
    public ShowsListPresenterImpl(ShowsListInteractor interactor) {
        this.interactor = interactor;
        subscription = Subscriptions.empty();
    }

    @Override
    public void loadShowsList(int page, final boolean isConnected) {
        if (view != null) {
            view.toggleProgress(true);
        }
        subscription = interactor
                .loadShowsList(page)
                .subscribe(new Action1<List<Show>>() {
                    @Override
                    public void call(List<Show> showItems) {
                        if (view != null) {
                            view.toggleProgress(false);
                            view.performShowsList(showItems);
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

    @Override
    public void setView(ShowsListView view) {
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
}
