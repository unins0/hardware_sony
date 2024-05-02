#
# Copyright (C) 2021 The LineageOS Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE_RELATIVE_PATH := hw

LOCAL_SHARED_LIBRARIES := \
        libbase \
        libcutils \
        liblog \
        libutils \
        libbinder_ndk \
        libdisppower-pixel \
        libperfmgr \
        libprocessgroup \
        pixel-power-ext-V1-ndk \

LOCAL_SRC_FILES := \
        BackgroundWorker.cpp \
        service.cpp \
        Power.cpp \
        PowerExt.cpp \
        PowerHintSession.cpp \
        PowerSessionManager.cpp \
        UClampVoter.cpp \
        SessionTaskMap.cpp \
        SessionValueEntry.cpp \

LOCAL_CFLAGS := -Wno-unused-parameter -Wno-unused-variable

LOCAL_MODULE := android.hardware.power-service.sony-libperfmgr
LOCAL_INIT_RC := android.hardware.power-service.sony-libperfmgr.rc
LOCAL_MODULE_TAGS := optional
LOCAL_VENDOR_MODULE := true
LOCAL_VINTF_FRAGMENTS := android.hardware.power-service.sony.xml

include $(BUILD_EXECUTABLE)
