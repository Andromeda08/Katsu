package hu.tsukiakari.osu.model.beatmap

import com.google.gson.annotations.SerializedName

data class Covers(
    val cover: String,
    @SerializedName("cover@2x") val cover2x: String,

    val card: String,
    @SerializedName("card@2x") val card2x: String,

    val list: String,
    @SerializedName("list@2x") val list2x: String,

    val slimcover: String,
    @SerializedName("slimcover@2x") val slimcover2x: String,
)
