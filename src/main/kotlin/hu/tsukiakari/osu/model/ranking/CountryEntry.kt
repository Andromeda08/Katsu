package hu.tsukiakari.osu.model.ranking

import com.google.gson.annotations.SerializedName
import hu.tsukiakari.osu.model.Country

data class CountryEntry(
    @SerializedName("active_users") val activeUsers: Long,
    @SerializedName("play_count")   val playCount: Long,
    @SerializedName("ranked_score") val rankedScore: Long,
    val performance: Long,
    val country: Country
)
