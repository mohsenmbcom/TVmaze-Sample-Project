package com.mohsenmb.apimodule.presentation;

import com.mohsenmb.apimodule.view.ShowsListView;

public interface ShowsListPresenter extends BasePresenter<ShowsListView> {
    void loadShowsList(int page, boolean isConnected);
}
