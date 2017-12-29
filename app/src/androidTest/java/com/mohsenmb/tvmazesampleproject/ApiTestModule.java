package com.mohsenmb.tvmazesampleproject;

import com.mohsenmb.apimodule.di.module.ApiModule;
import com.mohsenmb.apimodule.service.TvMazeApiService;
import com.mohsenmb.tvmazesampleproject.service.ApiServiceTestImpl;

import static org.mockito.Mockito.*;

import retrofit2.Retrofit;

public class ApiTestModule extends ApiModule {
    @Override
    public TvMazeApiService provideApiService(Retrofit retrofit) {
        return new ApiServiceTestImpl();
    }
}
