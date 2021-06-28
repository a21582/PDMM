package com.ipca.formulaworld.utils

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import com.ipca.formulaworld.R

fun getSharedPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences(context.resources.getString(R.string.app_name), Context.MODE_PRIVATE)
}

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as
            ConnectivityManager
    val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}