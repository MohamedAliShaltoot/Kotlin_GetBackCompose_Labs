package com.example.kotlin_getback_compose_lab1

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object NetworkUtil {
    fun isConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cm.activeNetwork ?: return false
        val cap = cm.getNetworkCapabilities(network) ?: return false
        return cap.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                cap.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
    }
}
