package com.smalam.pseudozero.androidmvp.Dagger.Components;

import com.smalam.pseudozero.androidmvp.Dagger.Modules.MovieListViewModule;
import com.smalam.pseudozero.androidmvp.MovieList.View.MainActivity;
import com.smalam.pseudozero.androidmvp.Dagger.CustomScopes.ListScope;

import dagger.Component;

/**
 * Created by Sayed Mahmudul Alam on 4/11/2017.
 */

@ListScope
@Component(dependencies = ApiServiceComponent.class, modules = MovieListViewModule.class)
public interface MovieListComponent {
    void inject(MainActivity mainActivity);
}
