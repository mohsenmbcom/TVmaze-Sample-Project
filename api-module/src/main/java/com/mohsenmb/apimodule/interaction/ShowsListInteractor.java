package com.mohsenmb.apimodule.interaction;

import com.mohsenmb.apimodule.service.model.Show;

import java.util.List;

import rx.Observable;

public interface ShowsListInteractor extends BaseInteractor {
    Observable<List<Show>> loadShowsList(int page);
}
