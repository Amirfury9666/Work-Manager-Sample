package com.fury.workmanager.extension

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

fun Any.isGreaterThanOrEqualToOreo(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

fun Any.isGreaterThanOrEqualToMarshmello(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M


fun Context.string(stringResId: Int): String = resources.getString(stringResId)

fun Context.registerNotificationChannel(channelId: String, channelName: String) {
    if (isGreaterThanOrEqualToOreo()) {
        val serviceChannel =
            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(serviceChannel)
    }
}