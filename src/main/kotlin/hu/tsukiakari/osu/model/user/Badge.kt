package hu.tsukiakari.osu.model.user

import com.google.gson.annotations.SerializedName

data class Badge(
    @SerializedName("awarded_at") val awardedAt: String,
    val description: String,
    @SerializedName("image_url")  val imageUrl: String,
    val url: String
)
