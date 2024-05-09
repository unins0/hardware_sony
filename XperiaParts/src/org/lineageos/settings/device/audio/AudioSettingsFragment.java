/*
 * Copyright (C) 2018,2020 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.settings.device.audio;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

import androidx.preference.Preference;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.PreferenceFragment;

import org.lineageos.settings.device.R;

import java.util.Arrays;
import java.util.List;

public class AudioSettingsFragment extends PreferenceFragment implements
        OnPreferenceChangeListener {

    private static final String TAG = "AudioSettingsFragment";

    public static final String PREF_DOLBY = "dolby_top_intro";

    private Preference mDolbyPref;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.advanced_audio_settings);

        mDolbyPref = (Preference) findPreference(PREF_DOLBY);
        mDolbyPref.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
         return true;
    }
}
