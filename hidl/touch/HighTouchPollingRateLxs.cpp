/*
 * Copyright (C) 2019-2021 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

#define LOG_TAG "HighTouchPollingRateService_Lxs"

#include <android-base/file.h>
#include <android-base/logging.h>
#include <android-base/properties.h>
#include <touch/sony/HighTouchPollingRate.h>
#include <touch/sony/Utils.h>
#include <cstdio>
#include <fstream>

namespace vendor {
namespace lineage {
namespace touch {
namespace V1_0 {
namespace implementation {

static constexpr const char* kPanelFrameRatePath =
        "/sys/devices/virtual/input/lxs_ts_input/frame_rate_np";

#define TOUCH_RATE_PREFIX "0 "

Return<bool> HighTouchPollingRate::isEnabled() {
    std::string touch_str;

    int disp_mode, touch_mode;
    auto ret = android::base::ReadFileToString(kPanelFrameRatePath, &touch_str);;
    auto result = sscanf(touch_str.c_str(), "%d,%d", &disp_mode, &touch_mode);

    LOG(INFO) << __func__ << ": disp_mode: " << disp_mode << ", touch_mode: " << touch_mode;

    return ret && result == 2 && touch_mode > 2;
}

Return<bool> HighTouchPollingRate::setEnabled(bool enabled) {
    bool result;
    if (enabled)
        result = send_cmd(kPanelFrameRatePath, TOUCH_RATE_PREFIX "3");
    else
        result = send_cmd(kPanelFrameRatePath, TOUCH_RATE_PREFIX "2");

    if (!result) {
        LOG(ERROR) << "Failed to write sec_ts cmd!";
        return false;
    }
    return true;
}

}  // namespace implementation
}  // namespace V1_0
}  // namespace touch
}  // namespace lineage
}  // namespace vendor
