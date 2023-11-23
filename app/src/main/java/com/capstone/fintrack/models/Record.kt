package com.capstone.fintrack.models

import com.google.gson.annotations.SerializedName

data class Record(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("amount")
    var amount: Float = 0f,

    @SerializedName("type")
    var type: String = "",

    @SerializedName("account")
    var account: Account? = null,

    @SerializedName("category")
    var category: Category? = null,

    @SerializedName("dateAdded")
    var dateAdded: String = "",

)