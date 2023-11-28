package com.capstone.fintrack.request

import com.capstone.fintrack.models.Analysis
import com.capstone.fintrack.models.Record
import com.google.gson.annotations.SerializedName

data class AnalysisListRequest(
    @SerializedName("username")
    var username: String,

    @SerializedName("type")
    var type: String,

    @SerializedName("success")
    var success: String = "",

    @SerializedName("analysis_list")
    var analysis_list: List<Analysis>? = null
)