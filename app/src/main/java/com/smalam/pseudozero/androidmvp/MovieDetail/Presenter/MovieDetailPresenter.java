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

    private String mApiKey;
    private final Context mContext;
    private final ApiClient mApiClient;
    private final IMovieDetailContract.IView mView;

    public MovieDetailPresenter(Context context, IMovieDetailContract.IView view) {
        this.mContext = context;
        this.mView = view;
        this.mApiKey = getmApiKey();
        this.mApiClient = new ApiClient();
    }

    @Override
    public void onStopAPIService(int movieId) {
        mApiClient.getClient().getMovieDetailByObservable(0, mApiKey).unsubscribeOn(Schedulers.newThread());
    }

    @Override
    public void onStopAPIService() {

    }

    @Override
    public void getMovieDetailData(int movieId) {
        getMovieDetailDataByCallBack(movieId);
    }

    private void getMovieDetailDataByCallBack(int movieId) {

        mView.showLoader();

        mApiClient
                .getClient()
                .getMovieDetailByCallBack(movieId, mApiKey)
                .enqueue(new Callback<MovieDetail>() {

                    @Override
                    public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                        mView.hideLoader();
                        mView.onDataFetchedSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<MovieDetail> call, Throwable t) {
                        mView.onDataFetchedError(t.getMessage().toString());
                    }
                });
    }

    private void getMovieDataDetailByObserving(int movieId) {

        mView.showLoader();

        Observer movieObserver = new Observer<MovieDetail>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MovieDetail response) {
                mView.hideLoader();
                mView.onDataFetchedSuccess(response);
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
                .getMovieDetailByObservable(movieId, mApiKey)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieObserver);
    }

    private String getmApiKey() {
        String apiKey = mContext.getResources().getString(R.string.tmdb_api_key);
        return apiKey;
    }

}
