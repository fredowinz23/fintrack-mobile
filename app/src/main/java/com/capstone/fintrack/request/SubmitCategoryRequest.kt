package com.capstone.fintrack.request

import com.capstone.fintrack.models.Account
import com.capstone.fintrack.models.Category
import com.google.gson.annotations.SerializedName

data class SubmitCategoryRequest(
    @SerializedName("username")
    var username: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("type")
    var type: String,

    @SerializedName("success")
    var success: String = "",
)