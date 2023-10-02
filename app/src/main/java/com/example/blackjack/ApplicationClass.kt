package com.example.blackjack

import android.app.Application
import com.onesignal.OneSignal

const val ONESIGNAL_APP_ID = "5a804efd-cc47-4d69-86bd-bfe4dbd0d7a8"

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()

        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }
}