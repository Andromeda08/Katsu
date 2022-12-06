package hu.tsukiakari.osu.model.beatmap

import com.google.gson.annotations.SerializedName

data class BeatmapScore(
    val accuracy: Double,
    @SerializedName("best_id") val bestId: Long,
    @SerializedName("created_at") val createdAt: String,
    val id: Long,
    @SerializedName("max_combo") val maxCombo: Int,
    val mode: String,
    @SerializedName("mode_int") val modeInt: Int,
    val mods: List<String>,

)
