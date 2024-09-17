/*
 * Copyright (C) 2023-2024 XperiaLabs Project
 * Copyright (C) 2022 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.xperia.settings.display

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

import com.xperia.settings.display.DisplayEngine
import com.xperia.settings.display.CreatorModeUtils
import com.xperia.settings.display.XRealityModeUtils

class BootCompletedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "Starting")
        CreatorModeUtils(context).initialize()
        XRealityModeUtils(context).initialize()
    }

    companion object {
        private const val TAG = "XperiaDisplay"
    }
}