/*
 * Copyright (C) 2025 XperiaLabs Project
 * Copyright (C) 2023 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xperia.settings.charger.hspc;

import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;

public class HSPCQSTileService extends TileService {
    private static final String ACTION_HSPC = "com.sonymobile.smartcharger.GE_CHARGE";
    private static final String PREFS_NAME = "HSPC_Prefs";
    private static final String KEY_IS_ACTIVE = "is_active";
    private static final String EXTRA_SUSPEND = "SUSPEND";
    private static final String EXTRA_THRESHOLD = "THRESHOLD";

    public static void setHspcState(Context context, boolean enable) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        prefs.edit().putBoolean(KEY_IS_ACTIVE, enable).apply();

        Intent intent = new Intent(ACTION_HSPC)
            .putExtra(EXTRA_SUSPEND, enable)
            .putExtra(EXTRA_THRESHOLD, calculateThreshold(context));
        context.sendBroadcast(intent);
    }

    public static int calculateThreshold(Context context) {
        Intent batteryIntent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        int level = 0;
        int scale = 0;
        if (batteryIntent != null) {
            level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        }

        int percentage = 0;
        if (scale > 0) {
            percentage = Math.round((level * 100.0f) / scale);
        }

        return percentage > 19 ? 23 :
               percentage > 5  ? percentage + 3 : 8;
    }

    @Override
    public void onStartListening() {
        updateTileState();
    }

    private void updateTileState() {
        Tile tile = getQsTile();
        if (tile == null) return;

        boolean isActive = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                            .getBoolean(KEY_IS_ACTIVE, false);

        tile.setState(isActive ? Tile.STATE_ACTIVE : Tile.STATE_INACTIVE);
        tile.updateTile();
    }

    @Override
    public void onClick() {
        boolean newState = !getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                            .getBoolean(KEY_IS_ACTIVE, false);
        setHspcState(this, newState);
        updateTileState();
    }
}
