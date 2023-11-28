package com.capstone.fintrack.models

import com.google.gson.annotations.SerializedName

data class Analysis(
    @SerializedName("name")
    var name: String = "",

    @SerializedName("amount")
    var amount: Float = 0f,
)