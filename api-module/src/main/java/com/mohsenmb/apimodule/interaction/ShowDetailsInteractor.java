package com.mohsenmb.apimodule.interaction;

import com.mohsenmb.apimodule.service.model.Show;

import rx.Observable;

public interface ShowDetailsInteractor extends BaseInteractor {
    Observable<Show> loadShowDetails(int showId);
}
