/**
 * Created by Sayed Mahmudul Alam on 3/25/2017.
 */

package com.smalam.pseudozero.androidmvp.Service;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.smalam.pseudozero.androidmvp.Model.MovieResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiClient {
    private static String BASE_URL =  "https://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;

    public interface ApiInterface {
        @GET("movie/now_playing")
        Call<MovieResponse> getMovieResponseByCallBack(@Query("api_key") String apiKey);

        @GET("movie/now_playing")
        Observable<MovieResponse> getMovieResponseByObservable(@Query("api_key") String apiKey);
    }

    public static Retrofit getClient() {
        if(retrofit ==  null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
