package com.smalam.pseudozero.androidmvp.Dagger.Modules;

import com.smalam.pseudozero.androidmvp.Dagger.CustomScopes.DetailScope;
import com.smalam.pseudozero.androidmvp.MovieDetail.Interface.IMovieDetailContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sayed Mahmudul Alam on 4/14/2017.
 */

@Module
public class MovieDetailViewModule {
    private final IMovieDetailContract.IView mMovieDetailView;

    public MovieDetailViewModule(IMovieDetailContract.IView mMovieDetailView) {
        this.mMovieDetailView = mMovieDetailView;
    }

    @Provides
    @DetailScope
    IMovieDetailContract.IView provideMovieDetailContactView() {
        return mMovieDetailView;
    }
}
