package com.example.digiitplay.login;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkState {
    public boolean networkstate(Context context){

        ConnectivityManager cm= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isconnected = cm.getActiveNetworkInfo()!=null && cm.getActiveNetworkInfo().isConnected();
        return isconnected;
    }
}
