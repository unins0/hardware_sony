/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

#pragma once

#include <aidl/google/hardware/power/extension/pixel/BnPowerExt.h>
#include <perfmgr/HintManager.h>

#include <atomic>
#include <memory>
#include <thread>

#include "disp-power/DisplayLowPower.h"

namespace aidl {
namespace google {
namespace hardware {
namespace power {
namespace impl {
namespace pixel {

class PowerExt : public ::aidl::google::hardware::power::extension::pixel::BnPowerExt {
  public:
    PowerExt(std::shared_ptr<DisplayLowPower> dlpw) : mDisplayLowPower(dlpw) {}
    ndk::ScopedAStatus setMode(const std::string &mode, bool enabled) override;
    ndk::ScopedAStatus isModeSupported(const std::string &mode, bool *_aidl_return) override;
    ndk::ScopedAStatus setBoost(const std::string &boost, int32_t durationMs) override;
    ndk::ScopedAStatus isBoostSupported(const std::string &boost, bool *_aidl_return) override;

  private:
    std::shared_ptr<DisplayLowPower> mDisplayLowPower;
};

}  // namespace pixel
}  // namespace impl
}  // namespace power
}  // namespace hardware
}  // namespace google
}  // namespace aidl
