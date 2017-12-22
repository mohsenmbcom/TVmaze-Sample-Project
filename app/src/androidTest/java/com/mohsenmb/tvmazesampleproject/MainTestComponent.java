package com.mohsenmb.tvmazesampleproject;


import com.mohsenmb.apimodule.di.module.ApiModule;
import com.mohsenmb.tvmazesampleproject.activity.MainActivityTest;
import com.mohsenmb.tvmazesampleproject.di.component.MainComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApiModule.class
})
public interface MainTestComponent extends MainComponent {
    void inject(MainActivityTest mainActivityTest);
}
