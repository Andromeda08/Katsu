package hu.tsukiakari.osu.model.user

import com.google.gson.annotations.SerializedName

data class RankHighest(
    val rank: Int,
    @SerializedName("updated_at") val updatedAt: String
)
