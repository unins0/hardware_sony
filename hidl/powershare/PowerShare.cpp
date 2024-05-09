/*
 * Copyright (C) 2022 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

#define LOG_TAG "vendor.lineage.powershare@1.0-service.sony"

#include "PowerShare.h"

#include <android-base/file.h>

using ::android::base::ReadFileToString;
using ::android::base::WriteStringToFile;

namespace vendor {
namespace lineage {
namespace powershare {
namespace V1_0 {
namespace implementation {

Return<bool> PowerShare::isEnabled() {
    std::string value;
    return ReadFileToString(WIRELESS_TX_ENABLE_PATH, &value) && value != "0\n";
}

Return<bool> PowerShare::setEnabled(bool enable) {
    return WriteStringToFile(enable ? "1" : "0", WIRELESS_TX_ENABLE_PATH, true);
}

Return<uint32_t> PowerShare::getMinBattery() {
    return 0;
}

Return<uint32_t> PowerShare::setMinBattery(uint32_t /*minBattery*/) {
    return getMinBattery();
}

}  // namespace implementation
}  // namespace V1_0
}  // namespace powershare
}  // namespace lineage
}  // namespace vendor
