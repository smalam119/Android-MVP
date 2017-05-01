package com.smalam.pseudozero.androidmvp.Dagger.Components;

import com.smalam.pseudozero.androidmvp.Dagger.Modules.ApiServiceModule;
import com.smalam.pseudozero.androidmvp.Dagger.Modules.AppModule;

import javax.inject.Singleton;
import dagger.Component;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

/**
 * Created by Sayed Mahmudul Alam on 4/8/2017.
 */

@Singleton
@Component(modules = {AppModule.class, ApiServiceModule.class})
public interface ApiServiceComponent {
    Retrofit retrofit();
    CompositeDisposable compositeDisposable();
}
