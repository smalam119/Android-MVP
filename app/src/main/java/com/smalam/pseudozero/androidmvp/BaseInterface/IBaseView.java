package com.smalam.pseudozero.androidmvp.BaseInterface;

/**
 * Created by Sayed Mahmudul Alam on 4/4/2017.
 */

public interface IBaseView<T> {
    void showLoader();
    void hideLoader();
    void onDataFetchedSuccess(T data);
    void onDataFetchedError(T data);
}
