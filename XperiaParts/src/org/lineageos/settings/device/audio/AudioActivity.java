/*
 * Copyright (C) 2018 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.settings.device.audio;

import android.os.Bundle;

import com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity;

public class AudioActivity extends CollapsingToolbarBaseActivity {

    private static final String TAG_AUDIO = "audio";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager()
             .beginTransaction()
             .replace(com.android.settingslib.collapsingtoolbar.R.id.content_frame,
                new AudioSettingsFragment(),
                TAG_AUDIO
             )
             .commit();
    }
}
