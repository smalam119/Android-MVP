package com.smalam.pseudozero.androidmvp.Dagger.Components;

import com.smalam.pseudozero.androidmvp.Dagger.CustomScopes.DetailScope;
import com.smalam.pseudozero.androidmvp.Dagger.Modules.MovieDetailViewModule;
import com.smalam.pseudozero.androidmvp.Modules.MovieDetail.View.MovieDetailActivity;

import dagger.Component;

/**
 * Created by Sayed Mahmudul Alam on 4/14/2017.
 */

@DetailScope
@Component(dependencies = {ApiServiceComponent.class}, modules = MovieDetailViewModule.class)
public interface MovieDetailComponent {
    void inject(MovieDetailActivity movieDetailActivity);
}
