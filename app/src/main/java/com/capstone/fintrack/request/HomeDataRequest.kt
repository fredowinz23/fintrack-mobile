package com.capstone.fintrack.request

import com.capstone.fintrack.models.Account
import com.capstone.fintrack.todelete.MedicalRecord
import com.google.gson.annotations.SerializedName

data class HomeDataRequest(
    @SerializedName("username")
    var username: String,

    @SerializedName("success")
    var success: String = "",

    @SerializedName("budget")
    var budget: Float = 0f,

    @SerializedName("account_list")
    var account_list: List<Account>? = null
)