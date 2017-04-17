package com.smalam.pseudozero.androidmvp.Application;

import android.app.Application;

import com.smalam.pseudozero.androidmvp.Constants;
import com.smalam.pseudozero.androidmvp.Dagger.Components.ApiServiceComponent;
import com.smalam.pseudozero.androidmvp.Dagger.Components.DaggerApiServiceComponent;
import com.smalam.pseudozero.androidmvp.Dagger.Modules.ApiServiceModule;
import com.smalam.pseudozero.androidmvp.Dagger.Modules.AppModule;
import com.smalam.pseudozero.androidmvp.R;
import com.smalam.pseudozero.androidmvp.Service.NetworkConnectivityReceiver;
import com.smalam.pseudozero.androidmvp.Service.NetworkConnectivityReceiverListener;

/**
 * Created by Sayed Mahmudul Alam on 4/8/2017.
 */

public class App extends Application {
    private ApiServiceComponent mApiServiceComponent;
    private static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();

        mApp = this;

        String apiKey = getApiKey();

        mApiServiceComponent = DaggerApiServiceComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule( new ApiServiceModule(Constants.BASE_URL,apiKey))
                .build();
    }

    public static synchronized App getInstance() {
        return mApp;
    }

    public ApiServiceComponent getApiServiceComponent() {
        return mApiServiceComponent;
    }

    private String getApiKey() {
        String apiKey = this.getResources().getString(R.string.tmdb_api_key);
        return apiKey;
    }

    public void setConnectivityListener(NetworkConnectivityReceiverListener listener) {
        NetworkConnectivityReceiver.networkConnectivityReceiverListener = listener;
    }
}
