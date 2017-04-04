package com.smalam.pseudozero.androidmvp.MovieList.Interface;

import com.smalam.pseudozero.androidmvp.BaseInterface.IBasePresenter;
import com.smalam.pseudozero.androidmvp.BaseInterface.IBaseView;
import com.smalam.pseudozero.androidmvp.Model.Movie;

/**
 * Created by Sayed Mahmudul Alam on 4/1/2017.
 */

public interface IMovieListContact {

    interface IView extends IBaseView {
    }

    interface IPresenter extends IBasePresenter {
        void getMovieData();
    }

    interface OnItemClickListener {
        void onClick(Movie item);
    }
}
