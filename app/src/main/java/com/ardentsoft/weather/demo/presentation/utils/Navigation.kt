package com.ardentsoft.weather.demo.presentation.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlin.jvm.internal.Intrinsics

object Navigation {

    fun navigateScreen(
        callerActivity: Activity,
        activity: Class<*>,
        finishCallerActivity: Boolean,
        bundleData: Bundle? = null
    ) {
        Intrinsics.checkParameterIsNotNull(callerActivity, "callerActivity")
        Intrinsics.checkParameterIsNotNull(activity, "activity")
        val intent = Intent(callerActivity, activity)
        if (bundleData != null) {
            intent.putExtras(bundleData)
        }
        callerActivity.startActivity(intent)
        if (finishCallerActivity) {
            callerActivity.finish()
        }
    }


}


