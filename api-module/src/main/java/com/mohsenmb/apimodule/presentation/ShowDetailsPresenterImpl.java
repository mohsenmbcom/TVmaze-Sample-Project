package com.mohsenmb.apimodule.presentation;

import com.mohsenmb.apimodule.interaction.ShowDetailsInteractor;
import com.mohsenmb.apimodule.service.model.Show;
import com.mohsenmb.apimodule.view.ShowDetailsView;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.Subscriptions;

public class ShowDetailsPresenterImpl implements ShowDetailsPresenter {
    private ShowDetailsInteractor interactor;

    private ShowDetailsView view;
    private Subscription subscription;

    @Inject
    public ShowDetailsPresenterImpl(ShowDetailsInteractor interactor) {
        this.interactor = interactor;
        subscription = Subscriptions.empty();
    }

    @Override
    public void setView(ShowDetailsView view) {
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
    public void loadShowDetails(int showId, final boolean isConnected) {
        if (view != null) {
            view.toggleProgress(true);
        }
        subscription = interactor
                .loadShowDetails(showId)
                .subscribe(new Action1<Show>() {
                    @Override
                    public void call(Show show) {
                        if (view != null) {
                            view.toggleProgress(false);
                            view.performShowDetails(show);
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
