package com.mohsenmb.apimodule.view;

import com.mohsenmb.apimodule.service.model.Show;

import java.util.List;

public interface ShowsListView extends BaseView {
    void performShowsList(List<Show> showItems);
}
