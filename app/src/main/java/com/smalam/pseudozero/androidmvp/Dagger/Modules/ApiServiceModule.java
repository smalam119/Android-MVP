package com.smalam.pseudozero.androidmvp.Dagger.Modules;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import java.io.IOException;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sayed Mahmudul Alam on 4/8/2017.
 */

@Module
public class ApiServiceModule {
    String mBaseUrl;
    String apiKey;

    public ApiServiceModule(String mBaseUrl, String apiKey) {
        this.mBaseUrl = mBaseUrl;
        this.apiKey = apiKey;
    }

    @Provides
    @Singleton
    Interceptor provideInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original  = chain.request();
                HttpUrl originalHttpUrl = original.url();
                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", apiKey)
                        .build();
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };

        return interceptor;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Interceptor interceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        OkHttpClient client = builder.build();
        return client;
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    @Provides
    @Singleton
    CompositeDisposable providesCompositeDisposable() {
       return new CompositeDisposable();
    }
}

