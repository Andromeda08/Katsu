package hu.tsukiakari.osu.model.user

import com.google.gson.annotations.SerializedName

data class Group(
    @SerializedName("colour")          val color: String,
    @SerializedName("has_listing")     val hasListing: Boolean,
    @SerializedName("has_playmodes")   val hasPlaymodes: Boolean,
    val id: Int,
    val identifier: String,
    @SerializedName("is_probationary") val isProbationary: Boolean,
    val name: String,
    @SerializedName("short_name")      val shortName: String,
    val playmodes: List<String>?
)
