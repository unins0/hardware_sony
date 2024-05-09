/*
 * Copyright 2023 The Android Open Source Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

#pragma once

#include <ostream>

#include "AdpfTypes.h"
#include "UClampVoter.h"

namespace aidl {
namespace google {
namespace hardware {
namespace power {
namespace impl {
namespace pixel {

// Per-power-session values (equivalent to original PowerHintSession)
// Responsible for maintaining the state of the power session via attributes
// Primarily this means actual uclamp value and whether session is active
// (i.e. whether to include this power session uclmap when setting task uclamp)
struct SessionValueEntry {
    int64_t sessionId{0};
    // Thread group id
    int64_t tgid{0};
    uid_t uid{0};
    std::string idString;
    bool isActive{true};
    bool isAppSession{false};
    std::chrono::steady_clock::time_point lastUpdatedTime;
    std::shared_ptr<Votes> votes;

    // Write info about power session to ostream for logging and debugging
    std::ostream &dump(std::ostream &os) const;
};

}  // namespace pixel
}  // namespace impl
}  // namespace power
}  // namespace hardware
}  // namespace google
}  // namespace aidl
