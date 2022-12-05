package hu.tsukiakari.osu.model.user

import com.google.gson.annotations.SerializedName
import hu.tsukiakari.osu.model.Country

data class UserCompact(
    @SerializedName("avatar_url")       val avatarUrl: String,
    @SerializedName("default_group")    val defaultGroup: String,
    val id: Int,
    @SerializedName("is_active")        val isActive: Boolean,
    @SerializedName("is_bot")           val isBot: Boolean,
    @SerializedName("is_deleted")       val isDeleted: Boolean,
    @SerializedName("is_online")        val isOnline: Boolean,
    @SerializedName("is_supporter")     val isSupporter: Boolean,
    @SerializedName("last_visit")       val lastVisit: String,
    @SerializedName("pm_friends_only")  val pmFriendsOnly: Boolean,
    @SerializedName("profile_colour")   val profileColor: String?,
    val username: String,
    val country: Country,
    val cover: Cover,
)
