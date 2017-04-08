package com.smalam.pseudozero.androidmvp.Application;

import android.app.Application;

import com.smalam.pseudozero.androidmvp.MovieList.Dagger.Components.ApiServiceComponent;
import com.smalam.pseudozero.androidmvp.MovieList.Dagger.Components.DaggerApiServiceComponent;
import com.smalam.pseudozero.androidmvp.MovieList.Dagger.Modules.ApiServiceModule;
import com.smalam.pseudozero.androidmvp.MovieList.Dagger.Modules.AppModule;

/**
 * Created by Sayed Mahmudul Alam on 4/8/2017.
 */

public class App extends Application {
    private ApiServiceComponent mApiServiceComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApiServiceComponent = DaggerApiServiceComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule( new ApiServiceModule(""))
                .build();
    }

    public ApiServiceComponent getmApiServiceComponent() {
        return mApiServiceComponent;
    }
}
