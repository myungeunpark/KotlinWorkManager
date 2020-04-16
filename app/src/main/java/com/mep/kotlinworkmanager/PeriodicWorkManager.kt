package com.mep.kotlinworkmanager

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.work.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class PeriodicWorkManager(context : Context, params : WorkerParameters) : CoroutineWorker(context, params) {

    companion object{

        private val TAG = PeriodicWorkManager::class.java.simpleName
        private const val WORK_NAME = "Periodic Work"
        private const val TIME_STRING = "string"
        private const val TIME_NUM = "num"

        fun run(context : Context, input : Data) : LiveData<WorkInfo> {

            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .setRequiresStorageNotLow(true)
                .build()

            val work = PeriodicWorkRequestBuilder<PeriodicWorkManager>(1, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .setInputData(input)
                .build()

            val manager = WorkManager.getInstance(context)
            manager.enqueueUniquePeriodicWork(WORK_NAME, ExistingPeriodicWorkPolicy.REPLACE, work)
            return manager.getWorkInfoByIdLiveData(work.id)
        }


        fun stop(context: Context){
            Log.d(TAG, "Periodic Work is stoped.")
            WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
        }

    }

    override suspend fun doWork(): Result {

        val numTime = inputData.getLong(TIME_NUM, 0)
        val strTime = convertLongToTime(numTime)
        Log.d(TAG, "Time - $numTime   $strTime")

        //output parameter
        val output = Data.Builder()
            .putString(TIME_STRING, strTime)
            .putLong(TIME_NUM, numTime)
            .build()

        return Result.success(output)
    }

    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
        return format.format(date)
    }

}