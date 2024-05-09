/*
 * Copyright (C) 2019-2021 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

#define LOG_TAG "SunlightEnhancementService"

#include <livedisplay/sony/SunlightEnhancement.h>
#include <android-base/logging.h>
#include <fstream>

namespace vendor {
namespace lineage {
namespace livedisplay {
namespace V2_1 {
namespace implementation {

static constexpr const char* kHbmPath =
    "/sys/devices/dsi_panel_driver/hbm_mode";

Return<bool> SunlightEnhancement::isEnabled() {
    std::ifstream file(kHbmPath);
    int result = -1;
    file >> result;
    LOG(DEBUG) << "Got result " << result << " fail " << file.fail();
    return !file.fail() && result > 0;
}

Return<bool> SunlightEnhancement::setEnabled(bool enabled) {
    std::ofstream file(kHbmPath);
    file << (enabled ? "1" : "0");
    LOG(DEBUG) << "setEnabled fail " << file.fail();
    return !file.fail();
}

}  // namespace implementation
}  // namespace V2_1
}  // namespace livedisplay
}  // namespace lineage
}  // namespace vendor
