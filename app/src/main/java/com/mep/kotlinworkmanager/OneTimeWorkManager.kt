package com.mep.kotlinworkmanager

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.work.*

class OneTimeWorkManager(context: Context, workerParams: WorkerParameters) :Worker(context, workerParams) {

    companion object{

        private val TAG = OneTimeWorkManager::class.java.simpleName

        fun createConstraints() = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)  // if connected to WIFI
            // other values(NOT_REQUIRED, CONNECTED, NOT_ROAMING, METERED)
            .setRequiresBatteryNotLow(true)                 // if the battery is not low
            .setRequiresStorageNotLow(true)                 // if the storage is not low
            .build()

        fun createWorkRequest(data: Data) = OneTimeWorkRequestBuilder<OneTimeWorkManager>()
            // set input data for the work
            .setInputData(data)
            .setConstraints(createConstraints())
            // setting a backoff on case the work needs to retry
            //.setBackoffCriteria(BackoffPolicy.LINEAR, PeriodicWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
            .build()

        fun createData() = Data.Builder()
            .putLong("LATITUDE", 42)
            .putLong("LONGITUDE",66)
            .build()

        fun run(context: Context) : LiveData<WorkInfo> {

            // set the input data, it is like a Bundle
            val work = createWorkRequest(createData())

            /* enqueue a work, ExistingPeriodicWorkPolicy.KEEP means that if this work already existits, it will be kept
            if the value is ExistingPeriodicWorkPolicy.REPLACE, then the work will be replaced */
            //WorkManager.getInstance().enqueueUniqueWork("Smart Work", ExistingWorkPolicy.KEEP, work)
            WorkManager.getInstance(context).enqueue(work)
            // Observe the result od the work
            return WorkManager.getInstance(context).getWorkInfoByIdLiveData(work.id)

        }
    }


    override fun doWork(): Result {

        val latitude = inputData.getLong("LATITUDE", 0)
        val longitude = inputData.getLong("LONGITUDE", 0)

        Log.d(TAG, "############################")
        Log.d(TAG, "LATITUDE : $latitude")
        Log.d(TAG, "LONGITUDE : $longitude")


        //output parameter
        val output = Data.Builder()
                            .putLong("LATITUDE", latitude)
                            .putLong("LONGITUDE", longitude)
                            .build()

        
        return Result.success(output)
    }

}