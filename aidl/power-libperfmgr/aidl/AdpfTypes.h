/*
 * Copyright 2023 The Android Open Source Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

#pragma once

#include <cstdint>

namespace aidl {
namespace google {
namespace hardware {
namespace power {
namespace impl {
namespace pixel {

enum class AdpfErrorCode : int32_t { ERR_OK = 0, ERR_BAD_STATE = -1, ERR_BAD_ARG = -2 };

enum class AdpfHintType : int32_t {
    ADPF_VOTE_DEFAULT = 1,
    ADPF_CPU_LOAD_UP = 2,
    ADPF_CPU_LOAD_RESET = 3,
    ADPF_CPU_LOAD_RESUME = 4,
    ADPF_VOTE_POWER_EFFICIENCY = 5
};

constexpr int kUclampMin{0};
constexpr int kUclampMax{1024};

}  // namespace pixel
}  // namespace impl
}  // namespace power
}  // namespace hardware
}  // namespace google
}  // namespace aidl
