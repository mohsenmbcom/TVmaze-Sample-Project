package com.mohsenmb.apimodule.interaction;

import com.mohsenmb.apimodule.service.TvMazeApiService;
import com.mohsenmb.apimodule.service.model.SearchShowItem;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.ReplaySubject;

public class ShowsSearchInteractorImpl implements ShowsSearchInteractor {
    private TvMazeApiService apiService;

    private ReplaySubject<List<SearchShowItem>> showsReplaySubject;
    private Subscription showsSubscription;

    @Inject
    public ShowsSearchInteractorImpl(TvMazeApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void destroy() {
        if (showsSubscription != null && !showsSubscription.isUnsubscribed()) {
            showsSubscription.unsubscribe();
        }
        showsSubscription = null;
        showsReplaySubject = null;
    }

    @Override
    public Observable<List<SearchShowItem>> searchForShows(String query) {
        if (showsSubscription == null || showsSubscription.isUnsubscribed()) {
            showsReplaySubject = ReplaySubject.create();

            showsSubscription = apiService.search(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(showsReplaySubject);
        }
        return showsReplaySubject.asObservable();
    }
}
