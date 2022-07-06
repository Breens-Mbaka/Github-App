package com.breens.githubapp.util

import android.content.Context
import android.net.ConnectivityManager

object Network {
    private const val NETWORK_STATUS_NOT_CONNECTED = 0
    private const val NETWORK_STATUS_WIFI = 1
    private const val NETWORK_STATUS_MOBILE = 2
    private const val TYPE_WIFI = 1
    private const val TYPE_MOBILE = 2
    private const val TYPE_NOT_CONNECTED = 0
    private fun connectivityStatus(context: Context): Int {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        if (null != activeNetwork) {
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) return TYPE_WIFI
            if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) return TYPE_MOBILE
        }
        return TYPE_NOT_CONNECTED
    }


    private fun connectivityStatusString(context: Context): Int {
        val connection = connectivityStatus(context)
        var status = -1
        if (connection == TYPE_WIFI) status = NETWORK_STATUS_WIFI else if (connection == TYPE_MOBILE) status = NETWORK_STATUS_MOBILE else if (connection == TYPE_NOT_CONNECTED) status = NETWORK_STATUS_NOT_CONNECTED
        return status
    }

    fun checkConnectivity(context : Context):Boolean{
        val status = connectivityStatusString(context)
        return status == NETWORK_STATUS_WIFI || status == NETWORK_STATUS_MOBILE
    }
}