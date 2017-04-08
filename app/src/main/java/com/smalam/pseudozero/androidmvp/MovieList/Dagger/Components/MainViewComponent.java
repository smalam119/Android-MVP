package com.smalam.pseudozero.androidmvp.MovieList.Dagger.Components;

import com.smalam.pseudozero.androidmvp.MovieList.Dagger.Modules.MainViewModule;
import com.smalam.pseudozero.androidmvp.MovieList.View.MainActivity;
import com.smalam.pseudozero.androidmvp.Utils.CustomScope;

import dagger.Component;

/**
 * Created by Sayed Mahmudul Alam on 4/8/2017.
 */

@CustomScope
@Component(dependencies = ApiServiceComponent.class, modules = MainViewModule.class)
public interface MainViewComponent {
    void inject(MainActivity mainActivity);
}
