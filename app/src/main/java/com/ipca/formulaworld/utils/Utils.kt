package com.ipca.formulaworld.utils

import android.content.Context
import android.content.SharedPreferences
import com.ipca.formulaworld.R

fun getSharedPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences(context.resources.getString(R.string.app_name), Context.MODE_PRIVATE)
}