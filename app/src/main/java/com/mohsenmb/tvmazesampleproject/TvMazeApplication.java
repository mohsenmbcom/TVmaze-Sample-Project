package com.mohsenmb.tvmazesampleproject;

import android.app.Application;

import com.mohsenmb.tvmazesampleproject.di.component.DaggerMainComponent;
import com.mohsenmb.tvmazesampleproject.di.component.MainComponent;

public class TvMazeApplication extends Application {
    private static MainComponent component;

    public static MainComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = createComponent();
    }

    public MainComponent createComponent() {
        return DaggerMainComponent
                .builder()
                .build();
    }
}
