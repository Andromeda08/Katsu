package hu.tsukiakari.osu.model.beatmap

import com.google.gson.annotations.SerializedName

data class Availability(
    @SerializedName("download_disabled") val downloadDisabled: Boolean,
    @SerializedName("more_information")  val moreInformation: String?
)
