package hu.tsukiakari.osu.model.user

import com.google.gson.annotations.SerializedName

data class Cover(
    @SerializedName("custom_url") val customUrl: String?,
    val url: String,
    val id: Int?
)
