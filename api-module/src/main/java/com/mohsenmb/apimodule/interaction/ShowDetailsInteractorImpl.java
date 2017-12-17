package com.mohsenmb.apimodule.interaction;

import com.mohsenmb.apimodule.service.TvMazeApiService;
import com.mohsenmb.apimodule.service.model.Show;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.ReplaySubject;

public class ShowDetailsInteractorImpl implements ShowDetailsInteractor {
    private TvMazeApiService apiService;

    private ReplaySubject<Show> showReplaySubject;
    private Subscription showSubscription;

    @Inject
    public ShowDetailsInteractorImpl(TvMazeApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void destroy() {
        if (showSubscription != null && !showSubscription.isUnsubscribed()) {
            showSubscription.unsubscribe();
        }
        showSubscription = null;
        showReplaySubject = null;
    }

    @Override
    public Observable<Show> loadShowDetails(int showId) {
        if (showSubscription == null || showSubscription.isUnsubscribed()) {
            showReplaySubject = ReplaySubject.create();

            showSubscription = apiService.loadShowDetails(showId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(showReplaySubject);
        }
        return showReplaySubject.asObservable();
    }
}
