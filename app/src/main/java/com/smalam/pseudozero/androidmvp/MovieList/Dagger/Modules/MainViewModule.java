package com.smalam.pseudozero.androidmvp.MovieList.Dagger.Modules;

import com.smalam.pseudozero.androidmvp.MovieDetail.Interface.IMovieDetailContract;
import com.smalam.pseudozero.androidmvp.Utils.CustomScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sayed Mahmudul Alam on 4/8/2017.
 */

@Module
public class MainViewModule {
    private final IMovieDetailContract.IView mView;

    public MainViewModule(IMovieDetailContract.IView mView) {
        this.mView = mView;
    }

    @Provides
    @CustomScope
    IMovieDetailContract.IView providesView()git status {
        return mView;
    }
}
