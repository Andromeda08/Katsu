package hu.tsukiakari.osu.model.beatmap.attributes

import com.google.gson.annotations.SerializedName

data class TaikoAttributes(
    @SerializedName("stamina_difficulty") val staminaDifficulty: Double,
    @SerializedName("rhythm_difficulty")  val rhythmDifficulty: Double,
    @SerializedName("colour_difficulty")  val colorDifficulty: Double,
    @SerializedName("approach_rate")      val approachRate: Double,
    @SerializedName("great_hit_window")   val greatHitWindow: Double
)
