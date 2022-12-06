package hu.tsukiakari.osu.model.score

import com.google.gson.annotations.SerializedName
import hu.tsukiakari.osu.model.beatmap.Beatmap
import hu.tsukiakari.osu.model.beatmap.BeatmapSet
import hu.tsukiakari.osu.model.user.UserCompact

data class Score(
    val accuracy: Double,
    @SerializedName("best_id") val bestId: Long,
    @SerializedName("created_at") val createdAt: String,
    val id: Long,
    @SerializedName("max_combo") val maxCombo: Int,
    val mode: String,
    @SerializedName("mode_int") val modeInt: Int,
    val mods: List<String>,
    val passed: Boolean,
    val perfect: Boolean,
    val pp: Double,
    val rank: String,
    val replay: Boolean,
    val score: Long,
    val statistics: Statistics,
    val type: String,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("current_user_attributes") val currentUserAttributes: CurrentUserAttributes,

    // Optionals
    val beatmap: Beatmap?,
    val beatmapset: BeatmapSet?,
    val user: UserCompact?,
    val weight: Weight?,
)
