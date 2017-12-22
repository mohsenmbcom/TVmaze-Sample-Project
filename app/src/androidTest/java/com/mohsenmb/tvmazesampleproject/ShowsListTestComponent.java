package com.mohsenmb.tvmazesampleproject;

import com.mohsenmb.apimodule.di.module.ShowsListModule;

import dagger.Subcomponent;

@Subcomponent(modules = {
        ShowsListModule.class
})
public interface ShowsListTestComponent {

}
