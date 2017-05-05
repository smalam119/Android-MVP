package com.smalam.pseudozero.androidmvp.Modules.MovieList.Interface;

import com.smalam.pseudozero.androidmvp.BaseInterface.IBasePresenter;
import com.smalam.pseudozero.androidmvp.BaseInterface.IBaseView;
import com.smalam.pseudozero.androidmvp.Model.Movie;

/**
 * Created by Sayed Mahmudul Alam on 4/1/2017.
 */

// contracts to agreed upon by presenter and view
public interface IMovieListContact {

    // data can be generic
    interface IView<T> extends IBaseView {
        void onDataFetchedSuccess(T data);
        void onDataFetchedError(T data);
    }

    interface IPresenter extends IBasePresenter {
        void getMovieData();
        void onStopAPIService();
    }

    interface OnItemClickListener {
        void onClick(Movie item);
    }
}
