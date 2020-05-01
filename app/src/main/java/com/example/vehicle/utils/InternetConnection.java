package com.example.vehicle.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import androidx.annotation.NonNull;

/**
 * Created by anyma on 16.05.2017.
 */

public class InternetConnection {
    /**
     * Check Internet connection
     */
    public static boolean checkConnection(@NonNull Context context) {
        return ((ConnectivityManager) context.getSystemService
                (Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}
