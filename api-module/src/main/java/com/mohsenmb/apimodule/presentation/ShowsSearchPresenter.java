package com.mohsenmb.apimodule.presentation;

import com.mohsenmb.apimodule.view.ShowsSearchView;

public interface ShowsSearchPresenter extends BasePresenter<ShowsSearchView> {
    void searchForShows(String query, boolean isConnected);
}
