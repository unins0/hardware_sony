/*
 * Copyright (C) 2025 XperiaLabs Project
 * Copyright (C) 2021 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.xperia.settings.disabler

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.SystemProperties
import android.util.Log

class BootCompletedReceiver : BroadcastReceiver() {

    companion object {
        private const val TAG = "XperiaDisabler"
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG, "Starting")
        radioDisabler(context)
    }

    private fun radioDisabler(context: Context) {
        val model = SystemProperties.get("vendor.radio.ltalabel.model", "")
        val isJP = model.equals("JP")
        val flag = if (isJP) {
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED
        } else {
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED
        }

        Log.d(TAG, "Detected model: $model, com.caf.fmradio isEnabled: $isJP")
        context.packageManager.setApplicationEnabledSetting("com.caf.fmradio", flag, 0)
    }

}
