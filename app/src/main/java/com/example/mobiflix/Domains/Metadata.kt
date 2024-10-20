package com.example.mobiflix.Domains

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("current_page")
    @Expose
    var currentPage: String,

    @SerializedName("per_page")
    @Expose
    var perPage: Int,

    @SerializedName("page_count")
    @Expose
    var pageCount: Int,

    @SerializedName("total_count")
    @Expose
    var totalCount: Int
)
