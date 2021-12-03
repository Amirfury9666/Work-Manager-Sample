package com.fury.workmanager.base

import android.app.Application
import com.fury.workmanager.R
import com.fury.workmanager.extension.registerNotificationChannel
import com.fury.workmanager.extension.string

class WorkerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        registerNotificationChannel(string(R.string.channelId), string(R.string.channelName))
    }
}