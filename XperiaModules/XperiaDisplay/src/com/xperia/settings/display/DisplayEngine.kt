/*
 * Copyright (C) 2024 XperiaLabs Project
 * Copyright (C) 2022 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.xperia.settings.display

import android.app.Activity
import android.content.Context
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.provider.Settings
import android.util.Log
import android.hardware.display.ColorDisplayManager
import vendor.semc.hardware.display.V2_0.IDisplay
import vendor.semc.hardware.display.V2_0.IDisplayCallback
import vendor.semc.hardware.display.V2_0.PccMatrix

abstract class DisplayEngine(private val context: Context) : IDisplayCallback.Stub() {
    private val colorDisplayManager: ColorDisplayManager =
            context.getSystemService(ColorDisplayManager::class.java)
                    ?: throw Exception("Display manager is NULL")
    private val semcDisplayService: IDisplay by lazy {
        val service = IDisplay.getService() ?: throw Exception("SEMC Display HIDL not found")

        service.registerCallback(this)

        service.setup()
        service
    }

    protected abstract val modeEnableSetting: String
    protected abstract val enabledColorMode: Int
    protected abstract val disabledColorMode: Int
    protected abstract val enabledSsspColorMode: Int
    protected abstract val disabledSsspColorMode: Int

    val isEnabled: Boolean
        get() = Settings.Secure.getInt(context.contentResolver, modeEnableSetting, 0) != 0

    fun setMode(enabled: Boolean) {
        semcDisplayService.set_sspp_color_mode(if (enabled) enabledSsspColorMode else disabledSsspColorMode)
        colorDisplayManager.setColorMode(if (enabled) enabledColorMode else disabledColorMode)
        semcDisplayService.set_color_mode(if (enabled) enabledSsspColorMode else disabledSsspColorMode)

        Settings.Secure.putInt(context.contentResolver, modeEnableSetting, if (enabled) 1 else 0)
    }

    fun initialize() {
        Log.e(TAG, "${javaClass.simpleName} controller setup")
        if (!isEnabled) {
            semcDisplayService.set_sspp_color_mode(1)
            colorDisplayManager.setColorMode(3)
            semcDisplayService.set_color_mode(1)
        }
        if (isEnabled) {
            semcDisplayService.registerCallback(this)
            setMode(true)
        }
    }

    override fun onWhiteBalanceMatrixChanged(matrix: PccMatrix) {
        val colorMatrix: ColorMatrix = ColorMatrix().apply {
            set(getMatrixArray(matrix))
        }

        val filter = ColorMatrixColorFilter(colorMatrix)

        val views = (context as Activity).window.decorView
        views.post { views.background.colorFilter = filter }
        Log.i(TAG, "New white balance: ${matrix.red}, ${matrix.green}, ${matrix.blue}")
    }

    protected abstract fun getMatrixArray(matrix: PccMatrix): FloatArray

    companion object {
        private const val TAG = "DisplayEngine"
    }
}
