package com.smalam.pseudozero.androidmvp.Dagger.Modules;

import com.smalam.pseudozero.androidmvp.MovieList.Interface.IMovieListContact;
import com.smalam.pseudozero.androidmvp.Dagger.CustomScopes.ListScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sayed Mahmudul Alam on 4/11/2017.
 */

@Module
public class MovieListViewModule {
    private final IMovieListContact.IView mMovieListView;

    public MovieListViewModule(IMovieListContact.IView mMovieListView) {
        this.mMovieListView = mMovieListView;
    }

    @Provides
    @ListScope
    IMovieListContact.IView provideMovieListContactView() {
        return mMovieListView;
    }
}
