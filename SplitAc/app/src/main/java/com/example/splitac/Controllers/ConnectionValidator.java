package com.example.splitac.Controllers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

public class ConnectionValidator {

    public boolean connectionValidator(Context cx){
        ConnectivityManager cm = (ConnectivityManager)cx.getSystemService(cx.CONNECTIVITY_SERVICE);
        return (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED);
    }

    public static boolean isWiFI(Context cx) {
        boolean wifi = false;
        ConnectivityManager cm = (ConnectivityManager)cx.getSystemService(cx.CONNECTIVITY_SERVICE);
        for(Network network : cm.getAllNetworks()){
            NetworkInfo netInfo = cm.getNetworkInfo(network);
            if(netInfo.getType() == ConnectivityManager.TYPE_WIFI){
                wifi |= netInfo.isConnected();
            }
        }
        return wifi;
    }
}
