package com.mohsenmb.apimodule.interaction;

import com.mohsenmb.apimodule.service.TvMazeApiService;
import com.mohsenmb.apimodule.service.model.Show;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.ReplaySubject;

public class ShowsListInteractorImpl implements ShowsListInteractor {
    private TvMazeApiService apiService;

    private ReplaySubject<List<Show>> showsReplaySubject;
    private Subscription showsSubscription;

    @Inject
    public ShowsListInteractorImpl(TvMazeApiService apiService) {
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
    public Observable<List<Show>> loadShowsList(int page) {
        if (showsSubscription == null || showsSubscription.isUnsubscribed()) {
            showsReplaySubject = ReplaySubject.create();

            showsSubscription = apiService.loadShowsList(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(showsReplaySubject);
        }
        return showsReplaySubject.asObservable();
    }
}
