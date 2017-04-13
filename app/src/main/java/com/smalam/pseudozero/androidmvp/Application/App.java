package com.smalam.pseudozero.androidmvp.Application;

import android.app.Application;

import com.smalam.pseudozero.androidmvp.Constants;
import com.smalam.pseudozero.androidmvp.MovieList.Dagger.Components.ApiServiceComponent;
import com.smalam.pseudozero.androidmvp.MovieList.Dagger.Components.DaggerApiServiceComponent;
import com.smalam.pseudozero.androidmvp.MovieList.Dagger.Modules.ApiServiceModule;
import com.smalam.pseudozero.androidmvp.MovieList.Dagger.Modules.AppModule;
import com.smalam.pseudozero.androidmvp.R;

/**
 * Created by Sayed Mahmudul Alam on 4/8/2017.
 */

public class App extends Application {
    private ApiServiceComponent mApiServiceComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        String apiKey = getApiKey();

        mApiServiceComponent = DaggerApiServiceComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule( new ApiServiceModule(Constants.BASE_URL,apiKey))
                .build();
    }

    public ApiServiceComponent getApiServiceComponent() {
        return mApiServiceComponent;
    }

        private String getApiKey() {
        String apiKey = this.getResources().getString(R.string.tmdb_api_key);
        return apiKey;
    }
}
