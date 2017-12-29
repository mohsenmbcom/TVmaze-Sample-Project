package com.mohsenmb.tvmazesampleproject.service;

import com.google.gson.Gson;
import com.mohsenmb.apimodule.service.TvMazeApiService;
import com.mohsenmb.apimodule.service.model.SearchShowItem;
import com.mohsenmb.apimodule.service.model.Show;
import com.mohsenmb.tvmazesampleproject.MainTestComponent;
import com.mohsenmb.tvmazesampleproject.TvMazeTestApplication;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class ApiServiceTestImpl implements TvMazeApiService {

    @Inject
    Gson gson;

    public ApiServiceTestImpl() {
        MainTestComponent component = (MainTestComponent) TvMazeTestApplication.getComponent();
        component.inject(this);
    }

    @Override
    public Observable<List<Show>> loadShowsList(int page) {
        List<Show> showsList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            showsList.add(gson.fromJson("{\"id\":250,\"url\":\"http://www.tvmaze.com/shows/250/kirby-buckets\",\"name\":\"Kirby Buckets\",\"type\":\"Scripted\",\"language\":\"English\",\"genres\":[\"Comedy\"],\"status\":\"Running\",\"runtime\":30,\"premiered\":\"2014-10-20\",\"officialSite\":\"http://disneyxd.disney.com/kirby-buckets\",\"schedule\":{\"time\":\"07:00\",\"days\":[\"Monday\",\"Tuesday\",\"Wednesday\",\"Thursday\",\"Friday\"]},\"rating\":{\"average\":10},\"weight\":21,\"network\":{\"id\":25,\"name\":\"Disney XD\",\"country\":{\"name\":\"United States\",\"code\":\"US\",\"timezone\":\"America/New_York\"}},\"webChannel\":{\"id\":123,\"name\":\"WATCH Disney XD\",\"country\":{\"name\":\"United States\",\"code\":\"US\",\"timezone\":\"America/New_York\"}},\"externals\":{\"tvrage\":37394,\"thetvdb\":278449,\"imdb\":\"tt3544772\"},\"image\":{\"medium\":\"http://static.tvmaze.com/uploads/images/medium_portrait/1/4600.jpg\",\"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/1/4600.jpg\"},\"summary\":\"<p>The single-camera series that mixes live-action and animation stars Jacob Bertrand as the title character. <b>Kirby Buckets</b> introduces viewers to the vivid imagination of charismatic 13-year-old Kirby Buckets, who dreams of becoming a famous animator like his idol, Mac MacCallister. With his two best friends, Fish and Eli, by his side, Kirby navigates his eccentric town of Forest Hills where the trio usually find themselves trying to get out of a predicament before Kirby's sister, Dawn, and her best friend, Belinda, catch them. Along the way, Kirby is joined by his animated characters, each with their own vibrant personality that only he and viewers can see.</p>\",\"updated\":1504098220,\"_links\":{\"self\":{\"href\":\"http://api.tvmaze.com/shows/250\"},\"previousepisode\":{\"href\":\"http://api.tvmaze.com/episodes/1051658\"}}}", Show.class));
        }
        return Observable.just(showsList);
    }

    @Override
    public Observable<List<SearchShowItem>> search(String query) {
        return null;
    }

    @Override
    public Observable<Show> loadShowDetails(int showId) {
        return Observable.just(gson.fromJson("{\"id\":250,\"url\":\"http://www.tvmaze.com/shows/250/kirby-buckets\",\"name\":\"Kirby Buckets\",\"type\":\"Scripted\",\"language\":\"English\",\"genres\":[\"Comedy\"],\"status\":\"Running\",\"runtime\":30,\"premiered\":\"2014-10-20\",\"officialSite\":\"http://disneyxd.disney.com/kirby-buckets\",\"schedule\":{\"time\":\"07:00\",\"days\":[\"Monday\",\"Tuesday\",\"Wednesday\",\"Thursday\",\"Friday\"]},\"rating\":{\"average\":10},\"weight\":21,\"network\":{\"id\":25,\"name\":\"Disney XD\",\"country\":{\"name\":\"United States\",\"code\":\"US\",\"timezone\":\"America/New_York\"}},\"webChannel\":{\"id\":123,\"name\":\"WATCH Disney XD\",\"country\":{\"name\":\"United States\",\"code\":\"US\",\"timezone\":\"America/New_York\"}},\"externals\":{\"tvrage\":37394,\"thetvdb\":278449,\"imdb\":\"tt3544772\"},\"image\":{\"medium\":\"http://static.tvmaze.com/uploads/images/medium_portrait/1/4600.jpg\",\"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/1/4600.jpg\"},\"summary\":\"<p>The single-camera series that mixes live-action and animation stars Jacob Bertrand as the title character. <b>Kirby Buckets</b> introduces viewers to the vivid imagination of charismatic 13-year-old Kirby Buckets, who dreams of becoming a famous animator like his idol, Mac MacCallister. With his two best friends, Fish and Eli, by his side, Kirby navigates his eccentric town of Forest Hills where the trio usually find themselves trying to get out of a predicament before Kirby's sister, Dawn, and her best friend, Belinda, catch them. Along the way, Kirby is joined by his animated characters, each with their own vibrant personality that only he and viewers can see.</p>\",\"updated\":1504098220,\"_links\":{\"self\":{\"href\":\"http://api.tvmaze.com/shows/250\"},\"previousepisode\":{\"href\":\"http://api.tvmaze.com/episodes/1051658\"}}}", Show.class));
    }
}
