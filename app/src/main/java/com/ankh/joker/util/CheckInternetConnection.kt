package com.ankh.joker.util

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.view.View
import com.google.android.material.snackbar.Snackbar

class CheckInternetConnection(val context: Context) {

    private val runningAndroidQOrLater = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q)

    fun isInternetConnected(): Boolean = run {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return when (runningAndroidQOrLater) {
            true -> {
                var isConnected = false
                val networks = connectivityManager.allNetworks

                if (networks.isNotEmpty()) {
                    networks.forEach { network ->
                        val networkCapabilities: NetworkCapabilities? = connectivityManager.getNetworkCapabilities(network)
                        if (networkCapabilities!!.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) isConnected = true
                    }
                }
                isConnected
            }
            else -> {
                val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                networkInfo?.isConnectedOrConnecting ?: false
            }
        }
    }

    fun showErrorSnackBar(
        view: View,
        actionText: String? = "OK",
        errorMessage: String? = "You need an active internet connection",
        action: () -> Unit? = ::openNetworkSettings
    ) {

        Snackbar.make(view, errorMessage!!, Snackbar.LENGTH_INDEFINITE).apply {
            setAction(actionText) {
                action()
            }.show()
        }

    }

    private fun openNetworkSettings() {
        context.startActivity(Intent("android.settings.DATA_ROAMING_SETTINGS").also { intent ->
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }
}
