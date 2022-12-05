package hu.tsukiakari.osu.model.beatmap.attributes

import com.google.gson.annotations.SerializedName

data class BeatmapAttributes(
    @SerializedName("max_combo")    val maxCombo: Int,
    @SerializedName("star_rating")  val starRating: Double,
    val osu: StandardAttributes?,
    val taiko: TaikoAttributes?,
    val fruits: FruitsAttributes?,
    val mania: ManiaAttributes?
)