package com.smalam.pseudozero.androidmvp.MovieList.Presenter;

import android.content.Context;

import com.smalam.pseudozero.androidmvp.Model.MovieResponse;
import com.smalam.pseudozero.androidmvp.MovieList.Interface.IMovieListContact;
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

public class MovieListPresenter implements IMovieListContact.IPresenter {

    private String mApiKey;
    private final Context mContext;
    private final ApiClient mApiClient;
    private final IMovieListContact.IView mView;

    public MovieListPresenter(IMovieListContact.IView view, Context context) {
        this.mView = view;
        this.mContext = context;
        this.mApiClient = new ApiClient();
        this.mApiKey = getmApiKey();
    }

    @Override
    public void getMovieData() {
        //getMovieDataByCallBack();
        getMovieDataByObserving();
    }

    @Override
    public void onStopAPIService() {
        mApiClient.getClient().getMovieResponseByObservable(mApiKey).unsubscribeOn(Schedulers.newThread());
    }

    private void getMovieDataByCallBack() {

        mView.showLoader();

        mApiClient
                .getClient()
                .getMovieResponseByCallBack(mApiKey)
                .enqueue(new Callback<MovieResponse>() {

            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                mView.hideLoader();
                mView.onDataFetchedSuccess(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                mView.hideLoader();
                mView.onDataFetchedSuccess(t.getMessage().toString());
            }
        });
    }

    private void getMovieDataByObserving() {

        mView.showLoader();

        Observer movieObserver = new Observer<MovieResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MovieResponse response) {
                mView.hideLoader();
                mView.onDataFetchedSuccess(response.getResults());
            }

            @Override
            public void onError(Throwable e) {
                mView.hideLoader();
                String errorMessage = e.getMessage().toString();
                mView.onDataFetchedError(errorMessage);
            }

            @Override
            public void onComplete() {

            }
        };

        mApiClient
                .getClient()
                .getMovieResponseByObservable(mApiKey)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieObserver);
    }

    private String getmApiKey() {
        String apiKey = mContext.getResources().getString(R.string.tmdb_api_key);
        return apiKey;
    }
}
