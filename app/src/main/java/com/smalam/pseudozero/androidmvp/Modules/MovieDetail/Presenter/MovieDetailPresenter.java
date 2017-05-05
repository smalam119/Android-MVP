package com.smalam.pseudozero.androidmvp.Modules.MovieDetail.Presenter;

import com.smalam.pseudozero.androidmvp.Model.MovieDetail;
import com.smalam.pseudozero.androidmvp.Modules.MovieDetail.Interface.IMovieDetailContract;
import com.smalam.pseudozero.androidmvp.Service.ApiClient;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Sayed Mahmudul Alam on 4/4/2017.
 */

public class MovieDetailPresenter implements IMovieDetailContract.IPresenter {

    IMovieDetailContract.IView mView;
    Retrofit retrofit;
    CompositeDisposable compositeDisposable;
    DisposableObserver movieObserver;

    @Inject
    public MovieDetailPresenter(IMovieDetailContract.IView view, Retrofit retrofit, CompositeDisposable compositeDisposable) {
        this.mView = view;
        this.retrofit = retrofit;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void onStopAPIService() {
        //compositeDisposable.dispose();
    }

    @Override
    public void getMovieDetailData(int movieId) {
        observeMovieDetail(movieId);
    }

    private void getMovieDetailDataByCallBack(int movieId) {

        mView.showLoader();

        retrofit.create(ApiClient.ApiInterface.class)
                .getMovieDetailByCallBack(movieId)
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

    private void observeMovieDetail(int movieId) {

        mView.showLoader();

        movieObserver = new DisposableObserver<MovieDetail>() {

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

        retrofit.create(ApiClient.ApiInterface.class)
                .getMovieDetailObservable(movieId)
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(movieObserver);

        compositeDisposable.add(movieObserver);
    }

}
