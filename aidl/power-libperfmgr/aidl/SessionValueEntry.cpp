/*
 * Copyright 2023 The Android Open Source Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

#include "SessionValueEntry.h"

#include <sstream>

namespace aidl {
namespace google {
namespace hardware {
namespace power {
namespace impl {
namespace pixel {

std::ostream &SessionValueEntry::dump(std::ostream &os) const {
    auto timeNow = std::chrono::steady_clock::now();
    os << "ID.Min.Act(" << idString;
    if (votes) {
        UclampRange uclampRange;
        votes->getUclampRange(&uclampRange, timeNow);
        os << ", " << uclampRange.uclampMin;
        os << "-" << uclampRange.uclampMax;
    } else {
        os << ", votes nullptr";
    }
    os << ", " << isActive;
    return os;
}

}  // namespace pixel
}  // namespace impl
}  // namespace power
}  // namespace hardware
}  // namespace google
}  // namespace aidl
