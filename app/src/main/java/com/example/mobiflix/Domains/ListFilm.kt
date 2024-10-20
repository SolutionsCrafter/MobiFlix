package com.example.mobiflix.Domains

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListFilm(
    @SerializedName("data")
    @Expose
    var data: List<Datum>,

    @SerializedName("metadata")
    @Expose
    var metadata: Metadata
)