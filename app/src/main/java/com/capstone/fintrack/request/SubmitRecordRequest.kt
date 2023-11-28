package com.capstone.fintrack.request

import com.capstone.fintrack.models.Account
import com.capstone.fintrack.models.Category
import com.google.gson.annotations.SerializedName

data class SubmitRecordRequest(
    @SerializedName("username")
    var username: String,

    @SerializedName("amount")
    var amount: String,

    @SerializedName("accountId")
    var accountId: Int,

    @SerializedName("categoryId")
    var categoryId: Int,

    @SerializedName("type")
    var type: String,

    @SerializedName("success")
    var success: String = "",

    @SerializedName("successMessage")
    var successMessage: String = "",
)