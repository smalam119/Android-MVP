package com.smalam.pseudozero.androidmvp.MovieList.Interface;

import com.smalam.pseudozero.androidmvp.Model.MovieResponse;

import retrofit2.Response;

/**
 * Created by Sayed Mahmudul Alam on 4/1/2017.
 */

public interface MovieListContact {
    interface View {
        void onMovieDataFetched(Response<MovieResponse> response);
        void onMovieDataFetched(MovieResponse response);

    }

    interface Presenter {
        void fetchMovieData();
    }
}
