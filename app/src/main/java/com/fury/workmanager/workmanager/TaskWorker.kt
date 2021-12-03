package com.fury.workmanager.workmanager

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.fury.workmanager.R
import com.fury.workmanager.extension.string
import com.fury.workmanager.utility.Constants

class TaskWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {

        val data = inputData
        val description = data.getString(Constants.workDescriptionKey)

        applicationContext.apply {
            createNotification(
                string(R.string.notificationTitle),
                description ?: ""
            )
        }
        return Result.success()
    }

    private fun createNotification(title: String, description: String) {
        val builder = NotificationCompat.Builder(
            applicationContext,
            applicationContext.string(R.string.channelId)
        ).apply {
            setContentTitle(title)
            setContentText(description)
            setSmallIcon(R.mipmap.ic_launcher)
        }

        val manager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1, builder.build())
    }
}