package hu.tsukiakari.osu.model.beatmap.attributes

import com.google.gson.annotations.SerializedName

data class FruitsAttributes(
    @SerializedName("approach_rate") val approachRate: Double,
)
