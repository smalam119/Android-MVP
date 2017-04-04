package com.smalam.pseudozero.androidmvp.MovieDetail.Interface;

import com.smalam.pseudozero.androidmvp.BaseInterface.IBasePresenter;
import com.smalam.pseudozero.androidmvp.BaseInterface.IBaseView;

/**
 * Created by Sayed Mahmudul Alam on 4/4/2017.
 */

public interface IMovieDetailContract {

    interface IPresenter extends IBasePresenter {
        void getMovieDetailData(int movieId);
        void onStopAPIService(int movieId);
    }

    interface IView extends IBaseView {
    }
}
