package com.capstone.fintrack.auth

import com.google.gson.annotations.SerializedName
import com.capstone.fintrack.models.User

data class LoginInfo(
    @SerializedName("username")
    var username: String,

    @SerializedName("password")
    var password: String,

    @SerializedName("success")
    var success: String = "",

    @SerializedName("profile")
    var profile: User? = null
)