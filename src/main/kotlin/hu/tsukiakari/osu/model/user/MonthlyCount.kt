package hu.tsukiakari.osu.model.user

import com.google.gson.annotations.SerializedName

data class MonthlyCount(
    @SerializedName("start_date") val startDate: String,
    val count: Int
)
