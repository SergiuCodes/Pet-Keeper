package com.example.petkeeper.tools.notifications.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.petkeeper.tools.Constants

class WorkerClass(val context: Context, val workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        NotificationHelper(context).createNotification(
            inputData.getString(Constants.titleExtra).toString(),
            inputData.getString(Constants.messageExtra).toString())

        return Result.success()
    }
}

