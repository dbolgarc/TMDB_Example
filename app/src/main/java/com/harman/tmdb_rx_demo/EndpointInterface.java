package com.harman.tmdb_rx_demo;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EndpointInterface {
    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies
            (@Query("api_key") String key, @Query("language") String lang);


    //rxJava approach
    @GET("movie/top_rated")
    Single<MoviesResponse> getTopRatedMoviesRx
    (@Query("api_key") String key, @Query("language") String lang);
}
