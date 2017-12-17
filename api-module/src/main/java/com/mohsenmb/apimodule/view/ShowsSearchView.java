package com.mohsenmb.apimodule.view;

import com.mohsenmb.apimodule.service.model.SearchShowItem;

import java.util.List;

public interface ShowsSearchView extends BaseView {
    void performShowsSearchResult(List<SearchShowItem> searchShowItems);
}
