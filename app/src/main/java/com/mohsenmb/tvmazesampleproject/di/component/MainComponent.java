package com.mohsenmb.tvmazesampleproject.di.component;

import com.mohsenmb.apimodule.di.module.ApiModule;
import com.mohsenmb.apimodule.di.module.ShowsListModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApiModule.class
})
public interface MainComponent {
    ShowsListComponent plus(ShowsListModule module);
}
