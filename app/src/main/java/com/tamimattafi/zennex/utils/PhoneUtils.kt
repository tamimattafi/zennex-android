
package com.tamimattafi.zennex.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


object PhoneUtils {

    fun isConnected(context: Context) : Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val capabilities = connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)
            capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else @Suppress("DEPRECATION") {
            val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
            activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
        }
    }

}