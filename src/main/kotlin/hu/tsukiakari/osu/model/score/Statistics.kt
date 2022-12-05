package hu.tsukiakari.osu.model.score

import com.google.gson.annotations.SerializedName

data class Statistics(
    @SerializedName("count_50")    val count50:    Int,
    @SerializedName("count_100")   val count100:   Int,
    @SerializedName("count_300")   val count300:   Int,
    @SerializedName("count_genki") val countGenki: Int,
    @SerializedName("count_katu")  val countKatu:  Int,
    @SerializedName("count_miss")  val countMiss:  Int,
)
