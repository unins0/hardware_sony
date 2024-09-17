/*
 * Copyright (C) 2024 XperiaLabs Project
 * Copyright (C) 2022 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.xperia.settings.display

import android.content.Context
import vendor.semc.hardware.display.V2_0.PccMatrix

class XRealityModeUtils(context: Context) : DisplayEngine(context) {
    override val modeEnableSetting: String
        get() = XREALITY_MODE_ENABLE

    override val enabledColorMode: Int
        get() = 1

    override val disabledColorMode: Int
        get() = 3

    override val enabledSsspColorMode: Int
        get() = 2

    override val disabledSsspColorMode: Int
        get() = 1

    override fun getMatrixArray(matrix: PccMatrix): FloatArray {
        return floatArrayOf(
            matrix.red * 1.0f, matrix.green * 0.9f, matrix.blue * 0.9f, 0f, 0f,
            matrix.red * 0.9f, matrix.green * 1.0f, matrix.blue * 0.9f, 0f, 0f,
            matrix.red * 0.9f, matrix.green * 0.9f, matrix.blue * 1.0f, 0f, 0f,
            0f, 0f, 0f, 1f, 0f
        )
    }

    companion object {
        private const val XREALITY_MODE_ENABLE = "xr_enable"
    }
}
