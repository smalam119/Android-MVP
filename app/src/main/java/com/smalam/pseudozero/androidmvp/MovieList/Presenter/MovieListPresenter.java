package com.smalam.pseudozero.androidmvp.MovieList.Presenter;

import com.smalam.pseudozero.androidmvp.Application.App;
import com.smalam.pseudozero.androidmvp.Model.MovieResponse;
import com.smalam.pseudozero.androidmvp.MovieList.Interface.IMovieListContact;
import com.smalam.pseudozero.androidmvp.Service.ApiClient;
import com.smalam.pseudozero.androidmvp.Service.NetworkConnectivityReceiverListener;

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
 * Created by Sayed Mahmudul Alam on 4/1/2017.
 */

public class MovieListPresenter implements IMovieListContact.IPresenter, NetworkConnectivityReceiverListener {

    public Retrofit retrofit;
    IMovieListContact.IView mView;
    CompositeDisposable compositeDisposable;
    DisposableObserver movieObserver;

    @Inject
    public MovieListPresenter(IMovieListContact.IView view, Retrofit retrofit,CompositeDisposable compositeDisposable) {
        this.mView = view;
        this.retrofit = retrofit;
        this.compositeDisposable = compositeDisposable;
        App.getInstance().setConnectivityListener(this);
    }

    @Override
    public void getMovieData() {
        observeMovieList();
    }

    @Override
    public void onStopAPIService() {
        compositeDisposable.dispose();
    }

    private void getMovieDataByCallBack() {

        mView.showLoader();

        retrofit.create(ApiClient.ApiInterface.class)
                .getMovieResponseByCallBack()
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

    private void observeMovieList() {

        mView.showLoader();

        movieObserver = new DisposableObserver<MovieResponse>() {

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

        retrofit.create(ApiClient.ApiInterface.class)
                .getMovieListObservable()
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieObserver);

        compositeDisposable.add(movieObserver);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        observeMovieList();
    }

}
