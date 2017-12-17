package com.mohsenmb.apimodule.view;

public interface BaseView {

    void showMessage(String message);

    void showOfflineMessage();

    void showConnectionError();

    void toggleProgress(boolean show);

    void showRetry();
}
