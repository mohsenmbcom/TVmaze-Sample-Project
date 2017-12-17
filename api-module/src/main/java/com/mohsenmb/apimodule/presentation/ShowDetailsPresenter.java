package com.mohsenmb.apimodule.presentation;

import com.mohsenmb.apimodule.view.ShowDetailsView;

public interface ShowDetailsPresenter extends BasePresenter<ShowDetailsView> {
    void loadShowDetails(int showId, boolean isConnected);
}
