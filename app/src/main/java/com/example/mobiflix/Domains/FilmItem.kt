package com.example.mobiflix.Domains

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FilmItem(
    @SerializedName("id") @Expose var id: Int? = null,
    @SerializedName("title") @Expose var title: String? = null,
    @SerializedName("poster") @Expose var poster: String? = null,
    @SerializedName("year") @Expose var year: String? = null,
    @SerializedName("rated") @Expose var rated: String? = null,
    @SerializedName("released") @Expose var released: String? = null,
    @SerializedName("runtime") @Expose var runtime: String? = null,
    @SerializedName("director") @Expose var director: String? = null,
    @SerializedName("writer") @Expose var writer: String? = null,
    @SerializedName("actors") @Expose var actors: String? = null,
    @SerializedName("plot") @Expose var plot: String? = null,
    @SerializedName("country") @Expose var country: String? = null,
    @SerializedName("awards") @Expose var awards: String? = null,
    @SerializedName("metascore") @Expose var metascore: String? = null,
    @SerializedName("imdb_rating") @Expose var imdbRating: String? = null,
    @SerializedName("imdb_votes") @Expose var imdbVotes: String? = null,
    @SerializedName("imdb_id") @Expose var imdbId: String? = null,
    @SerializedName("type") @Expose var type: String? = null,
    @SerializedName("genres") @Expose var genres: List<String>? = null,
    @SerializedName("images") @Expose var images: List<String>? = null
)
