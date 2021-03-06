package com.rudderstack.android.sample.kotlin

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import com.rudderstack.android.sdk.core.RudderConfig
import com.rudderstack.android.sdk.core.RudderLogger
import com.rudderstack.android.sdk.core.RudderClient

class MainApplication : Application() {
    companion object {
        var rudderClient: RudderClient? = null
        var sqLiteHelper: SQLiteHelper? = null
        const val TAG = "MainApplication"
        const val DATA_PLANE_URL = "https://hosted.rudderlabs.com"
        const val WRITE_KEY = "1cZKYwoLsDiMaw9WUvxLsG8GDc8"
    }

    override fun onCreate() {
        super.onCreate()

        rudderClient = RudderClient.getInstance(
            this,
            WRITE_KEY,
            RudderConfig.Builder()
                .withDataPlaneUrl(DATA_PLANE_URL)
                .withLogLevel(RudderLogger.RudderLogLevel.DEBUG)
                .withTrackLifecycleEvents(true)
                .withRecordScreenViews(true)
                .build()
        )

        sqLiteHelper = SQLiteHelper(this);

        rudderClient!!.rudderContext.putDeviceToken("some_device_token")



        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(p0: Activity) {
                Log.d(TAG, "onActivityPaused ${p0.localClassName}")
            }

            override fun onActivityStarted(p0: Activity) {
                Log.d(TAG, "onActivityStarted ${p0.localClassName}")
            }

            override fun onActivityDestroyed(p0: Activity) {
                Log.d(TAG, "onActivityDestroyed ${p0.localClassName}")
            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
                Log.d(TAG, "onActivitySaveInstanceState ${p0.localClassName}, Bundle: $p1")
            }

            override fun onActivityStopped(p0: Activity) {
                Log.d(TAG, "onActivityStopped ${p0.localClassName}")
            }

            override fun onActivityCreated(p0: Activity, p1: Bundle?) {
                Log.d(TAG, "onActivityCreated ${p0.localClassName} Bundle: ${p1.toString()}")
            }

            override fun onActivityResumed(p0: Activity) {
                Log.d(TAG, "onActivityResumed ${p0.localClassName}")
            }
        })
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.d(TAG, "onLowMemory")
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG, "onTerminate")
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Log.d(TAG, "onTrimMemory: $level")
    }
}