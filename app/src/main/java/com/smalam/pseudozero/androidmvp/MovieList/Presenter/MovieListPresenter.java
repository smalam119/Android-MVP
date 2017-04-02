package com.smalam.pseudozero.androidmvp.MovieList.Presenter;

import android.content.Context;
import android.util.Log;

import com.smalam.pseudozero.androidmvp.Model.MovieResponse;
import com.smalam.pseudozero.androidmvp.MovieList.Interface.MovieListContact;
import com.smalam.pseudozero.androidmvp.R;
import com.smalam.pseudozero.androidmvp.Service.ApiClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sayed Mahmudul Alam on 4/1/2017.
 */

public class MovieListPresenter implements MovieListContact.Presenter {

    private String apiKey;
    private Observer movieObserver;
    private final Context context;
    private final ApiClient apiClient;
    private final MovieListContact.View view;

    public MovieListPresenter( MovieListContact.View view, Context context) {
        this.view = view;
        this.context = context;
        this.apiClient = new ApiClient();
        this.apiKey = getApiKey();
    }

    @Override
    public void fetchMovieData() {
        //getMovieDataByCallBack();
        getMovieDataByObserving();
    }

    @Override
    public void onStop() {
        apiClient.getClient().getMovieResponseByObservable(apiKey).unsubscribeOn(Schedulers.newThread());
    }

    private void getMovieDataByCallBack() {
        apiClient
                .getClient()
                .getMovieResponseByCallBack(apiKey)
                .enqueue(new Callback<MovieResponse>() {

            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                view.onMovieDataFetchedSuccess(response);

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                view.onMovieDataFetchedError(t.getMessage().toString());
            }
        });
    }

    private void getMovieDataByObserving() {

        view.showLoader();

        Observer movieObserver = new Observer<MovieResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MovieResponse response) {
                view.removeLoader();
                view.onMovieDataFetchedSuccess(response);
            }

            @Override
            public void onError(Throwable e) {
                String errorMessage = e.getMessage().toString();
                view.removeLoader();
                view.onMovieDataFetchedError(errorMessage);
            }

            @Override
            public void onComplete() {

            }
        };

        apiClient
                .getClient()
                .getMovieResponseByObservable(apiKey)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieObserver);
    }

    private String getApiKey() {
        String apiKey = context.getResources().getString(R.string.tmdb_api_key);
        return apiKey;
    }
}
