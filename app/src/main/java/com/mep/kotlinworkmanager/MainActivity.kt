package com.mep.kotlinworkmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        private val TAG = MainActivity::class.java.simpleName
        private const val TIME_NUM = "num"
        private const val TIME_STRING = "string"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runOneTimeWork.setOnClickListener {

            // one time worker request with input/output data pass
            OneTimeWorkManager.run(this)
                .observe(this, Observer { workInfo ->
                    if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                        // FINISHED SUCCESSFULLY!
                        Log.d(TAG, ">>>> SUCCEEDED STATUS - ONE TIME WORKER. ")

                        val output = workInfo.outputData
                        val la = output.getLong("LATITUDE", 1)
                        val lo = output.getLong("LONGITUDE", 1)
                        Log.d(MainActivity.TAG, "$$$$$$$$$$$$$$$$$$$$$$$$$$")
                        Log.d(MainActivity.TAG, "output latitude : $la")
                        Log.d(MainActivity.TAG, "output longitude : $lo")
                    }else if(workInfo.state == WorkInfo.State.RUNNING)
                        Log.d(TAG, ">>>> RUNNING STATUS - ONE TIME WORKER. ")
                    if(workInfo.state == WorkInfo.State.ENQUEUED)
                        Log.d(TAG, ">>>> ENQUEUED STATUS - ONE TIME WORKER. ")
                    if(workInfo.state == WorkInfo.State.FAILED)
                        Log.d(TAG, ">>>> FAILED STATUS - ONE TIME WORKER. ")
                    if(workInfo.state == WorkInfo.State.BLOCKED)
                        Log.d(TAG, ">>>> BLOCKED STATUS - ONE TIME WORKER. ")

                })

        }
        start.setOnClickListener {

            // run CoroutineWorker
            val liveData = OneTimeProgressWorkManager.run(this)
            liveData.observe(this, Observer {

                if(it != null) {
                    if (it.state == WorkInfo.State.SUCCEEDED)
                        Log.d(TAG, ">>>> SUCCEEDED STATUS - ONE TIME PROGRESS WORKER. ")
                    if (it.state == WorkInfo.State.RUNNING)
                        Log.d(TAG, ">>>> RUNNING STATUS - ONE TIME PROGRESS WORKER. ")
                    if (it.state == WorkInfo.State.ENQUEUED)
                        Log.d(TAG, ">>>> ENQUEUED STATUS - ONE TIME PROGRESS WORKER. ")
                    if (it.state == WorkInfo.State.FAILED)
                        Log.d(TAG, ">>>> FAILED STATUS - ONE TIME PROGRESS WORKER. ")
                    if (it.state == WorkInfo.State.BLOCKED)
                        Log.d(TAG, ">>>> BLOCKED STATUS - ONE TIME PROGRESS WORKER. ")

                    Log.d(TAG, "Running=${it.state.isFinished}")
                    val progress = it.progress.getInt(OneTimeProgressWorkManager.PARAM_PROGRESS, -1)
                    Log.d(TAG, "Progress=$progress")
                }
            })
        }

        stop.setOnClickListener {

            // stop coroutineWorker
            OneTimeProgressWorkManager.stop(this)
            Log.d(TAG, ">>>> CANCELED STATUS - ONE TIME PROGRESS WORKER. ")

        }


        startPeriodicWork.setOnClickListener {

            val time = System.currentTimeMillis()
            val input = workDataOf(TIME_NUM to time)
            Log.d(TAG, "input - $time")
            PeriodicWorkManager.run(this, input)
                .observe(this, Observer{ workInfo ->

                    Log.d(TAG, "Live Data")
                    if (workInfo != null && workInfo.state == WorkInfo.State.ENQUEUED){

                        Log.d(TAG, ">>>> ENQUEUED STATUS - PERIODIC WORKER. ")

                    }else if (workInfo != null && workInfo.state == WorkInfo.State.RUNNING){
                        Log.d(TAG, ">>>> RUNNING STATUS - PERIODIC WORKER. ")
                    }
                })
        }

        stopPeriodicWork.setOnClickListener {
            PeriodicWorkManager.stop(this)
            Log.d(TAG, ">>>> CANCEL STATUS - PERIODIC WORKER. ")

        }
    }


}
