#
# Copyright 2020 The LineageOS Project
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
AUDIO_DIR := vendor/qcom/opensource/audio-hal/primary-hal

include $(CLEAR_VARS)
LOCAL_SHARED_LIBRARIES := \
    audio.primary.$(TARGET_BOARD_PLATFORM) \
    libaudioroute \
    libaudioutils \
    libcutils \
    liblog \
    libtinyalsa \
    libtinycompress

LOCAL_C_INCLUDES += \
    $(AUDIO_DIR) \
    $(AUDIO_DIR)/hal \
    $(AUDIO_DIR)/hal/msm8974 \
    $(AUDIO_DIR)/hal/audio_extn \
    external/tinycompress/include \
    $(TARGET_OUT_INTERMEDIATES)/KERNEL_OBJ/usr/techpack/audio/include

LOCAL_CFLAGS += -DTA_DEBUG -DDEBUG_SHOW_VALUES -DDEBUG_RUN_ONLY_READ_TA

LOCAL_SRC_FILES := audio_amplifier.c
LOCAL_MODULE := audio_amplifier.$(TARGET_BOARD_PLATFORM)
LOCAL_HEADER_LIBRARIES := \
    qti_kernel_headers \
    libhardware_headers

LOCAL_MODULE_RELATIVE_PATH := hw
LOCAL_VENDOR_MODULE := true

include $(BUILD_SHARED_LIBRARY)