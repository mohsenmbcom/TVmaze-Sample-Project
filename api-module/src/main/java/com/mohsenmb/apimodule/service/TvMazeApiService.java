package com.mohsenmb.apimodule.service;

import com.mohsenmb.apimodule.service.model.Show;
import com.mohsenmb.apimodule.service.model.SearchShowItem;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface TvMazeApiService {

    @Headers("Content-Type: application/json")
    @GET("shows")
    Observable<List<Show>> loadShowsList(@Query("page") int page);

    @Headers("Content-Type: application/json")
    @GET("search/shows")
    Observable<List<SearchShowItem>> search(@Query("q") String query);

    @Headers("Content-Type: application/json")
    @GET("shows/{show-id}")
    Observable<Show> loadShowDetails(@Path("show-id") int showId);
}
