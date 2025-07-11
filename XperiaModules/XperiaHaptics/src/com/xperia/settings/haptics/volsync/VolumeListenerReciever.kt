/*
 * Copyright (c) 2025, Alcatraz323 <alcatraz32323@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.xperia.settings.haptics.volsync

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager

class VolumeListenerReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) return

        if (intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", 0) == AudioManager.STREAM_MUSIC) {
            val audioManager = context.getSystemService(AudioManager::class.java)
            val current = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", 0)
            audioManager.setParameters("stream_music_volume=$current")
            audioManager.setParameters("somc.media_vibration_audio_volume=${kMediaVibVolTable[current]}")
        }
    }

    companion object {
        @JvmField
        val kMediaVibVolTable = floatArrayOf(
            0.0000000000f, 0.0068881093f, 0.0078567471f, 0.0093633886f,
            0.0106801121f, 0.0121819992f, 0.0145180682f, 0.0165596586f,
            0.0188883636f, 0.0225104671f, 0.0256759953f, 0.0292866733f,
            0.0349028111f, 0.0398109965f, 0.0501190498f, 0.0681296214f,
            0.0857700631f, 0.1079780608f, 0.1467805654f, 0.1847856790f,
            0.2326312810f, 0.3162285388f, 0.3981079757f, 0.4410067499f,
            0.5054804087f, 0.5599492788f, 0.6202875376f, 0.7109714746f,
            0.7875833511f, 0.8724506497f, 1.0000000000f
        )
    }
}
