package com.app.bitesmart.onBoard

import android.content.Context

class OnBoardingUtils(private val context: Context) {
    fun isOnBoardingCompleted(): Boolean{
        return context.getSharedPreferences("onboarding",Context.MODE_PRIVATE)
            .getBoolean("completed", false)
    }
    fun setOnBoardingCompleted(){
        context.getSharedPreferences("onboarding",Context.MODE_PRIVATE)
            .edit()
            .putBoolean("completed", true)
            .apply()
    }
}