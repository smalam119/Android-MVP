package com.smalam.pseudozero.androidmvp.MovieList.Dagger.Components;

import com.smalam.pseudozero.androidmvp.MovieList.Dagger.Modules.MovieListViewModule;
import com.smalam.pseudozero.androidmvp.MovieList.View.MainActivity;
import com.smalam.pseudozero.androidmvp.Utils.CustomScope;

import dagger.Component;

/**
 * Created by Sayed Mahmudul Alam on 4/11/2017.
 */

@CustomScope
@Component(dependencies = ApiServiceComponent.class, modules = MovieListViewModule.class)
public interface MovieListComponent {
    void inject(MainActivity mainActivity);
}
