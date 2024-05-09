/*
 * Copyright 2023 The Android Open Source Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

#include "UClampVoter.h"

namespace aidl {
namespace google {
namespace hardware {
namespace power {
namespace impl {
namespace pixel {

static void confine(UclampRange *uclampRange, const VoteRange &vr,
                    std::chrono::steady_clock::time_point t) {
    if (!vr.isTimeInRange(t)) {
        return;
    }
    uclampRange->uclampMin = std::max(uclampRange->uclampMin, vr.uclampMin());
    uclampRange->uclampMax = std::min(uclampRange->uclampMax, vr.uclampMax());
}

VoteRange VoteRange::makeMinRange(int uclampMin, std::chrono::steady_clock::time_point startTime,
                                  std::chrono::nanoseconds durationNs) {
    VoteRange v(true, uclampMin, kUclampMax, startTime, durationNs);
    return v;
}

std::ostream &operator<<(std::ostream &o, const VoteRange &vr) {
    o << "[" << vr.uclampMin() << "," << vr.uclampMax() << "]";
    return o;
}

Votes::Votes() {}

void Votes::add(int voteId, const VoteRange &v) {
    mVotes[voteId] = v;
}

void Votes::updateDuration(int voteId, std::chrono::nanoseconds durationNs) {
    auto voteItr = mVotes.find(voteId);
    if (voteItr != mVotes.end()) {
        voteItr->second.updateDuration(durationNs);
    }
}

void Votes::getUclampRange(UclampRange *uclampRange,
                           std::chrono::steady_clock::time_point t) const {
    if (nullptr == uclampRange) {
        return;
    }
    for (const auto &v : mVotes) {
        confine(uclampRange, v.second, t);
    }
}

bool Votes::anyTimedOut(std::chrono::steady_clock::time_point t) const {
    for (const auto &v : mVotes) {
        if (!v.second.isTimeInRange(t)) {
            return true;
        }
    }
    return false;
}

bool Votes::allTimedOut(std::chrono::steady_clock::time_point t) const {
    for (const auto &v : mVotes) {
        if (v.second.isTimeInRange(t)) {
            return false;
        }
    }
    return true;
}

bool Votes::remove(int voteId) {
    auto itr = mVotes.find(voteId);
    if (itr == mVotes.end()) {
        return false;
    }
    mVotes.erase(itr);
    return true;
}

bool Votes::setUseVote(int voteId, bool active) {
    auto itr = mVotes.find(voteId);
    if (itr == mVotes.end()) {
        return false;
    }
    itr->second.setActive(active);
    return true;
}

size_t Votes::size() const {
    return mVotes.size();
}

bool Votes::voteIsActive(int voteId) {
    auto itr = mVotes.find(voteId);
    if (itr == mVotes.end()) {
        return false;
    }
    return itr->second.active();
}

std::chrono::steady_clock::time_point Votes::voteTimeout(int voteId) {
    auto itr = mVotes.find(voteId);
    if (itr == mVotes.end()) {
        return std::chrono::steady_clock::time_point{};
    }
    return itr->second.startTime() + itr->second.durationNs();
}

}  // namespace pixel
}  // namespace impl
}  // namespace power
}  // namespace hardware
}  // namespace google
}  // namespace aidl
