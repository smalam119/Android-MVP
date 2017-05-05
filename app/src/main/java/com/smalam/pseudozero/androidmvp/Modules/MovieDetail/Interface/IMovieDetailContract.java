package com.smalam.pseudozero.androidmvp.Modules.MovieDetail.Interface;

import com.smalam.pseudozero.androidmvp.BaseInterface.IBasePresenter;
import com.smalam.pseudozero.androidmvp.BaseInterface.IBaseView;

/**
 * Created by Sayed Mahmudul Alam on 4/4/2017.
 */

//contracts that must be agreed upon by views and presenter
public interface IMovieDetailContract {

    interface IPresenter extends IBasePresenter {
        void getMovieDetailData(int movieId);
        void onStopAPIService();
    }

    interface IView<T> extends IBaseView {
        void onDataFetchedSuccess(T data);
        void onDataFetchedError(T data);
    }
}
