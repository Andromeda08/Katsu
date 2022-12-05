package hu.tsukiakari.osu.model.beatmap.attributes

import com.google.gson.annotations.SerializedName

data class ManiaAttributes(
    @SerializedName("great_hit_window") val greatHitWindow: Double,
    @SerializedName("score_multiplier") val scoreMultiplier: Double
)
