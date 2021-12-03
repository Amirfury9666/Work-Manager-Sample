package com.fury.workmanager.base

import android.app.Application
import com.fury.workmanager.R
import com.fury.workmanager.extension.registerNotificationChannel
import com.fury.workmanager.extension.string

/***
 * Created By Amir Fury on December 3 2021
 *
 * Email: Fury.amir93@gmail.com
 * */


class WorkerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        registerNotificationChannel(string(R.string.channelId), string(R.string.channelName))
    }
}