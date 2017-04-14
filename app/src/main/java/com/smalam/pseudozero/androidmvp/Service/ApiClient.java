/**
 * Created by Sayed Mahmudul Alam on 3/25/2017.
 */

package com.smalam.pseudozero.androidmvp.Service;

import com.smalam.pseudozero.androidmvp.Model.MovieDetail;
import com.smalam.pseudozero.androidmvp.Model.MovieResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class ApiClient {
    private static String BASE_URL =  "https://api.themoviedb.org/3/";
    private static Retrofit mRetrofit = null;

    public interface ApiInterface {
        @GET("movie/now_playing")
        Call<MovieResponse> getMovieResponseByCallBack();

        @GET("movie/now_playing")
        Observable<MovieResponse> getMovieResponseByObservable();

        @GET("movie/{movie_id}")
        Call<MovieDetail> getMovieDetailByCallBack(@Path("movie_id") int movieId);

        @GET("movie/{movie_id}")
        Observable<MovieDetail> getMovieDetailByObservable(@Path("movie_id") int movieId);
    }
}
