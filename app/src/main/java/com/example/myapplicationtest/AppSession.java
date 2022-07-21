package com.example.myapplicationtest;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import com.example.myapplicationtest.Views.interfaces.progressBarOperations;

import okhttp3.Cache;

public class AppSession extends Application {
    public static progressBarOperations operations;
    public static Cache cache;
    private static Context context;
    public static long perPage= 100;

    public static void setProgressBarOperations(progressBarOperations operations) {
        AppSession.operations = operations;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        cache = new Cache(context.getCacheDir(), cacheSize);
    }


    public static boolean isNetworkAvailable_b() {
        return haveNetworkConnection();
    }

    private static boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = connectivityManager.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}
