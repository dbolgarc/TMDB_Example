package com.harman.tmdb_rx_demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {
    static final String BASE_URL = "https://api.themoviedb.org/3/";
    //todo: put your key here
    static final String API_KEY = "1fbbae194035822592ba00434ba9f78d";

    private Retrofit prepare() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                //add RXJava Converter
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;
    }

    public Call<MoviesResponse> prepareMoviesCall() {
        EndpointInterface movieApi = prepare().create(EndpointInterface.class);
        Call<MoviesResponse> call = movieApi.getTopRatedMovies(API_KEY, "ru");
        return call;
    }

    public Single<MoviesResponse> prepareMoviesCallRX() {
        EndpointInterface movieApi = prepare().create(EndpointInterface.class);
        Single<MoviesResponse> single = movieApi.getTopRatedMoviesRx(API_KEY, "ru");
        return single;
    }
}
