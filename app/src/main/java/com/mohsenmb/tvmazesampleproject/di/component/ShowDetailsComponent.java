package com.mohsenmb.tvmazesampleproject.di.component;

import com.mohsenmb.apimodule.di.module.ShowDetailsModule;
import com.mohsenmb.tvmazesampleproject.fragments.ShowDetailsFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {
        ShowDetailsModule.class
})
public interface ShowDetailsComponent {
    void inject(ShowDetailsFragment fragment);
}
