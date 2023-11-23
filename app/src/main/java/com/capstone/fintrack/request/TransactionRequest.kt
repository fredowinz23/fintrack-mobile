package com.capstone.fintrack.request

import com.capstone.fintrack.models.Record
import com.google.gson.annotations.SerializedName

data class TransactionRequest(
    @SerializedName("username")
    var username: String,

    @SerializedName("success")
    var success: String = "",

    @SerializedName("record_list")
    var record_list: List<Record>? = null
)