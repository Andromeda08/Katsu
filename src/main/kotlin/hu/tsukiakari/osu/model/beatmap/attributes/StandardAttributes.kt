package hu.tsukiakari.osu.model.beatmap.attributes

import com.google.gson.annotations.SerializedName

data class StandardAttributes(
    @SerializedName("aim_difficulty")        val aimDifficulty: Double,
    @SerializedName("approach_rate")         val approachRate: Double,
    @SerializedName("flashlight_difficulty") val flashlightDifficulty: Double,
    @SerializedName("overall_difficulty")    val overallDifficulty: Double,
    @SerializedName("slider_factor")         val sliderFactor: Double,
    @SerializedName("speed_difficulty")      val speedDifficulty: Double
)
