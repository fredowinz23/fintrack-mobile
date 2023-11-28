package com.capstone.fintrack.request

import com.capstone.fintrack.models.Account
import com.capstone.fintrack.models.Category
import com.google.gson.annotations.SerializedName

data class CategoryRequest(
    @SerializedName("username")
    var username: String,

    @SerializedName("type")
    var type: String,

    @SerializedName("success")
    var success: String = "",

    @SerializedName("category_list")
    var category_list: List<Category>? = null
)