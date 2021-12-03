package com.fury.workmanager.ui

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.work.*
import com.fury.workmanager.R
import com.fury.workmanager.base.BaseActivity
import com.fury.workmanager.databinding.ActivityHomeBinding
import com.fury.workmanager.extension.string
import com.fury.workmanager.utility.Constants
import com.fury.workmanager.workmanager.TaskWorker


/***
 * Created By Amir Fury on December 3 2021
 *
 * Email: Fury.amir93@gmail.com
 * */


class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    private val workManager by lazy { WorkManager.getInstance(this) }

    private val workData by lazy {
        Data.Builder().apply {
            putString(Constants.workDescriptionKey, string(R.string.workDescription))
        }.build()
    }

    private val workRequest by lazy {
        OneTimeWorkRequest.Builder(TaskWorker::class.java).setConstraints(workerConstraints)
            .setInputData(workData).build()
    }

    private val workerConstraints by lazy {
        Constraints.Builder().apply {
            setRequiredNetworkType(NetworkType.CONNECTED)
        }.build()
    }

    override val layoutRes: Int get() = R.layout.activity_home

    override fun getToolbar(binding: ActivityHomeBinding): Toolbar = binding.toolbar

    override fun onActivityReady(instanceState: Bundle?, binding: ActivityHomeBinding) {
        binding.performWorkButton.setOnClickListener {
            startWorker()
        }
    }

    private fun startWorker() {
        workManager.enqueue(workRequest)
        workManager.getWorkInfoByIdLiveData(workRequest.id).observe(this, { info ->
            val status = info.state.name
            binding.workStateTv.text = status
        })
    }
}