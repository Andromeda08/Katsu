package hu.tsukiakari.osu.model.user.statistics

import com.google.gson.annotations.SerializedName

data class Statistics(
    val level: Level,
    @SerializedName("global_rank")   val globalRank: Int,
    val pp: Double,
    @SerializedName("ranked_score")  val rankedScore: Long,
    @SerializedName("hit_accuracy")  val hitAccuracy: Double,
    @SerializedName("play_count")    val playCount: Int,
    @SerializedName("play_time")     val playTime: Long,
    @SerializedName("total_score")   val totalScore: Long,
    @SerializedName("total_hits")    val totalHits: Long,
    @SerializedName("maximum_combo") val maximumCombo: Int,
    @SerializedName("replays_watched_by_others") val replaysWatchedByOthers: Int,
    @SerializedName("is_ranked")     val isRanked: Boolean,
    @SerializedName("grade_counts")  val gradeCounts: GradeCounts,
    @SerializedName("country_rank")  val countryRank: Int
)
