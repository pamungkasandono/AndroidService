package com.example.androidmyservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log

class MyBoundService : Service() {
    companion object {
        private val TAG = MyBoundService::class.java.simpleName
    }

    private var mBinder = MyBinder()
    private val startTime = System.currentTimeMillis()

    private val mTimer: CountDownTimer = object : CountDownTimer(100000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            val elapsed = System.currentTimeMillis() - startTime
            Log.d("Debug", "onTick : $elapsed")
        }

        override fun onFinish() {

        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("Debug", "onCreate")
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d("Debug", "onBind")
        mTimer.start()
        return mBinder
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Debug", "onDestroy")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d("Debug", "onUnbind")
        mTimer.cancel()
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.d("Debug", "onRebind")
    }

    internal inner class MyBinder : Binder() {
        val getService: MyBoundService = this@MyBoundService
    }
}