package com.mohsenmb.tvmazesampleproject;

public class TvMazeTestApplication extends TvMazeApplication {

    @Override
    public MainTestComponent createComponent() {
        return DaggerMainTestComponent
                .builder()
                .apiModule(new ApiTestModule())
                .build();
    }
}
