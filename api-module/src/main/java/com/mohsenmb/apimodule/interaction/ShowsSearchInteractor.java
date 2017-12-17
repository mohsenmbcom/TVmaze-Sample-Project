package com.mohsenmb.apimodule.interaction;

import com.mohsenmb.apimodule.service.model.SearchShowItem;

import java.util.List;

import rx.Observable;

public interface ShowsSearchInteractor extends BaseInteractor {
    Observable<List<SearchShowItem>> searchForShows(String query);
}
