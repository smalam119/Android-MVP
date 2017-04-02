package com.smalam.pseudozero.androidmvp.MovieList.Interface;

import com.smalam.pseudozero.androidmvp.Model.Movie;
import com.smalam.pseudozero.androidmvp.Model.MovieResponse;

import retrofit2.Response;

/**
 * Created by Sayed Mahmudul Alam on 4/1/2017.
 */

public interface MovieListContact {
    interface View {
        void onMovieDataFetchedSuccess(Response<MovieResponse> response);
        void onMovieDataFetchedSuccess(MovieResponse response);
        void onMovieDataFetchedError(String errorMessage);
        void showLoader();
        void removeLoader();
    }

    interface Presenter {
        void fetchMovieData();
        void onStop();
    }

    interface OnItemClickListener {
        void onClick(Movie item);
    }
}
