/*
 * Copyright (C) 2023-2024 XperiaLabs Project
 * Copyright (C) 2022 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.xperia.settings.display

import android.content.Context
import vendor.semc.hardware.display.V2_0.PccMatrix

class CreatorModeUtils(context: Context) : DisplayEngine(context) {
    override val modeEnableSetting: String
        get() = CREATOR_MODE_ENABLE

    override val enabledColorMode: Int
        get() = 0

    override val disabledColorMode: Int
        get() = 3

    override val enabledSsspColorMode: Int
        get() = 0

    override val disabledSsspColorMode: Int
        get() = 1

    override fun getMatrixArray(matrix: PccMatrix): FloatArray {
        return floatArrayOf(
            matrix.red, matrix.green, matrix.blue, 0f, 0f,
            matrix.red, matrix.green, matrix.blue, 0f, 0f,
            matrix.red, matrix.green, matrix.blue, 0f, 0f,
            0f, 0f, 0f, 1f, 0f
        )
    }

    companion object {
        private const val CREATOR_MODE_ENABLE = "cm_enable"
    }
}
