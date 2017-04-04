package com.smalam.pseudozero.androidmvp.MovieDetail.Presenter;

import android.content.Context;

import com.smalam.pseudozero.androidmvp.Model.MovieDetail;
import com.smalam.pseudozero.androidmvp.MovieDetail.Interface.IMovieDetailContract;
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
 * Created by Sayed Mahmudul Alam on 4/4/2017.
 */

public class MovieDetailPresenter implements IMovieDetailContract.IPresenter {

    private String apiKey;
    private final Context context;
    private final ApiClient apiClient;
    private final IMovieDetailContract.IView view;

    public MovieDetailPresenter(Context context, IMovieDetailContract.IView view) {
        this.context = context;
        this.view = view;
        this.apiKey = getApiKey();
        this.apiClient = new ApiClient();
    }

    @Override
    public void onStopAPIService(int movieId) {
        apiClient.getClient().getMovieDetailByObservable(0,apiKey).unsubscribeOn(Schedulers.newThread());
    }

    @Override
    public void onStopAPIService() {

    }

    @Override
    public void getMovieDetailData(int movieId) {
        getMovieDetailDataByCallBack(movieId);
    }

    private void getMovieDetailDataByCallBack(int movieId) {

        view.showLoader();

        apiClient
                .getClient()
                .getMovieDetailByCallBack(movieId,apiKey)
                .enqueue(new Callback<MovieDetail>() {

                    @Override
                    public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                        view.hideLoader();
                        view.onDataFetchedSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<MovieDetail> call, Throwable t) {
                        view.onDataFetchedError(t.getMessage().toString());
                    }
                });
    }

    private void getMovieDataDetailByObserving(int movieId) {

        view.showLoader();

        Observer movieObserver = new Observer<MovieDetail>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MovieDetail response) {
                view.hideLoader();
                view.onDataFetchedSuccess(response);
            }

            @Override
            public void onError(Throwable e) {
                view.hideLoader();
                String errorMessage = e.getMessage().toString();
                view.onDataFetchedError(errorMessage);
            }

            @Override
            public void onComplete() {

            }
        };

        apiClient
                .getClient()
                .getMovieDetailByObservable(movieId,apiKey)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieObserver);
    }

    private String getApiKey() {
        String apiKey = context.getResources().getString(R.string.tmdb_api_key);
        return apiKey;
    }

}
