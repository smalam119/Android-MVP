package com.smalam.pseudozero.androidmvp.Dagger.Components;

import com.smalam.pseudozero.androidmvp.Dagger.Modules.MovieDetailViewModule;
import com.smalam.pseudozero.androidmvp.Dagger.Modules.MovieListViewModule;
import com.smalam.pseudozero.androidmvp.MovieDetail.View.MovieDetailActivity;
import com.smalam.pseudozero.androidmvp.MovieList.View.MainActivity;
import com.smalam.pseudozero.androidmvp.Dagger.CustomScopes.CustomScope;

import dagger.Component;

/**
 * Created by Sayed Mahmudul Alam on 4/11/2017.
 */

@CustomScope
@Component(dependencies = ApiServiceComponent.class, modules = {MovieListViewModule.class, MovieDetailViewModule.class})
public interface MovieListComponent {
    void inject(MainActivity mainActivity);
    void inject(MovieDetailActivity movieDetailActivity);
}
