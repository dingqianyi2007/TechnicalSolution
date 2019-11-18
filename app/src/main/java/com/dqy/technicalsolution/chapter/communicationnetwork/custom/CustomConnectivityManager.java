package com.dqy.technicalsolution.chapter.communicationnetwork.custom;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public  class CustomConnectivityManager {

    public static boolean hasNetworkConnection(Context context){
        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        //
        boolean connected = (null != activeNetworkInfo) && activeNetworkInfo.isConnected();
        if(!connected) return false;
        boolean routeExists;
        //
        try {
            InetAddress host = InetAddress.getByName("8.8.8.8");
            Socket s = new Socket();
            s.connect(new InetSocketAddress(host,53),5000);
            //
            routeExists = true;
            s.close();
        }catch (Exception e){
            routeExists = false;
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("No Network Connection");
            builder.setMessage("The Network is unavailable.Please try your request again later");
            builder.create().show();
        }
        return (connected && routeExists);
    }

    public boolean isWifiReachable(Context context){
        ConnectivityManager mManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo current = mManager.getActiveNetworkInfo();
        if(current != null){
            return false;
        }
        return (current.getType() == ConnectivityManager.TYPE_WIFI);
    }
}
