package com.mohsenmb.apimodule.presentation;

public interface BasePresenter<T> {
    void setView(T view);

    void destroy();
}
