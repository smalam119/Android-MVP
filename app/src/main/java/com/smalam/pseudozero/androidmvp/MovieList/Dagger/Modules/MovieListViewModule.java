package com.smalam.pseudozero.androidmvp.MovieList.Dagger.Modules;

import com.smalam.pseudozero.androidmvp.MovieList.Interface.IMovieListContact;
import com.smalam.pseudozero.androidmvp.Utils.CustomScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sayed Mahmudul Alam on 4/11/2017.
 */

@Module
public class MovieListViewModule {
    private final IMovieListContact.IView mView;

    public MovieListViewModule(IMovieListContact.IView mView) {
        this.mView = mView;
    }

    @Provides
    @CustomScope
    IMovieListContact.IView provideMovieListContactView() {
        return mView;
    }
}
