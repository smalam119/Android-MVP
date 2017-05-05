package com.smalam.pseudozero.androidmvp.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Sayed Mahmudul Alam on 4/18/2017.
 */

//broadcast receiver for internet connection
public class NetworkConnectivityReceiver extends BroadcastReceiver {

    public static NetworkConnectivityReceiverListener networkConnectivityReceiverListener;

    @Override
    public void onReceive(Context context, Intent arg1) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null
                && networkInfo.isConnectedOrConnecting();

        if (networkConnectivityReceiverListener != null) {
            networkConnectivityReceiverListener.onNetworkConnectionChanged(isConnected);
        }
    }
}
