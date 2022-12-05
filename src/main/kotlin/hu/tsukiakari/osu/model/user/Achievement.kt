package hu.tsukiakari.osu.model.user

import com.google.gson.annotations.SerializedName

data class Achievement(
    @SerializedName("achieved_at")    val achievedAt: String,
    @SerializedName("achievement_id") val achievementId: Int
)
