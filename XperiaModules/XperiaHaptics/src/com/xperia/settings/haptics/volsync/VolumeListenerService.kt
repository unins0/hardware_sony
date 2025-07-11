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

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.os.IBinder

class VolumeListenerService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val intentFilter = IntentFilter().apply {
            addAction("android.media.VOLUME_CHANGED_ACTION")
        }

         val receiver = VolumeListenerReceiver()
        registerReceiver(receiver, intentFilter)

        val audioManager = getSystemService(AudioManager::class.java)
        val current = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        audioManager.setParameters("volume_change=$current")
        audioManager.setParameters("somc.media_vibration_audio_volume=${VolumeListenerReceiver.kMediaVibVolTable[current]}")

        return super.onStartCommand(intent, flags, startId)
    }

    companion object {
        fun onBoot(context: Context) {
            context.startService(Intent(context, VolumeListenerService::class.java))
        }
    }
}
