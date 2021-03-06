package com.rudderstack.android.sdk.core;

import android.os.Build;

import com.google.gson.annotations.SerializedName;

class RudderDeviceInfo {
    @SerializedName("id")
    private String deviceId;
    @SerializedName("manufacturer")
    private String manufacturer = Build.MANUFACTURER;
    @SerializedName("model")
    private String model = Build.MODEL;
    @SerializedName("name")
    private String name = Build.DEVICE;
    @SerializedName("type")
    private String type = "android";
    @SerializedName("token")
    private String token;

    RudderDeviceInfo(String deviceId) {
        this.deviceId = deviceId;
    }

    String getDeviceId() {
        return deviceId;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
