package com.mep.kotlinworkmanager

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.work.*
import kotlinx.coroutines.*

class OneTimeProgressWorkManager(context: Context, parameters: WorkerParameters) : CoroutineWorker(context, parameters) {


    companion object{

        private val TAG = OneTimeProgressWorkManager::class.java.simpleName
        private const val WORK_NAME = "ProgressWorker"
        const val PARAM_PROGRESS = "progress"

        fun run(context: Context): LiveData<WorkInfo> {

            Log.d(TAG,"===== Worker Run ======")
            val work = OneTimeWorkRequestBuilder<OneTimeProgressWorkManager>()
                .build()

            val manager = WorkManager.getInstance(context)
            manager.enqueueUniqueWork(WORK_NAME, ExistingWorkPolicy.REPLACE, work)
            return manager.getWorkInfoByIdLiveData(work.id)
        }

        fun stop(context: Context) {
            Log.d(TAG,"===== Worker Stop ======")
            val manager = WorkManager.getInstance(context)
            manager.cancelUniqueWork(WORK_NAME)
        }
    }

    override suspend fun doWork(): Result {

        for (x in 1..100) {

            setProgress(workDataOf(PARAM_PROGRESS to x))

            // do something
            try {
                delay(2000)
            } catch (e: CancellationException) {
                Log.d(TAG, "Cancelled")
            }


            if (isStopped) {
                Log.d(TAG, "isStopped")
                return Result.success(workDataOf(PARAM_PROGRESS to x))
            }
        }

        return Result.success(workDataOf(PARAM_PROGRESS to 100))
    }
}