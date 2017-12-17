package com.mohsenmb.tvmazesampleproject.di.component;

import com.mohsenmb.apimodule.di.module.ShowsListModule;
import com.mohsenmb.tvmazesampleproject.fragments.ShowsListFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {
        ShowsListModule.class
})
public interface ShowsListComponent {
    void inject(ShowsListFragment fragment);
}
