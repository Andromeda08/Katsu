package hu.tsukiakari.osu.model.score

import com.google.gson.annotations.SerializedName
import hu.tsukiakari.osu.model.beatmap.Beatmap
import hu.tsukiakari.osu.model.beatmap.BeatmapSet

data class Score(
    val id: Long,
    @SerializedName("best_id") val bestId: Long,
    @SerializedName("user_id") val userId: Int,
    val accuracy: Double,
    val score: Long,
    @SerializedName("max_combo") val maxCombo: Int,
    val perfect: Boolean,
    val statistics: Statistics,
    val passed: Boolean,
    val pp: Double,
    val rank: String,
    @SerializedName("created_at") val createdAt: String,
    val mode: String,
    @SerializedName("mode_int") val modeInt: Int,
    val beatmap: Beatmap,
    val beatmapset: BeatmapSet,
    @SerializedName("rank_country") val rankCountry: Int,
    @SerializedName("rank_global") val rankGlobal: Int
)
