package hu.tsukiakari.osu.model.beatmap

import com.google.gson.annotations.SerializedName

data class BeatmapSet(
    val artist: String,
    @SerializedName("artist_unicode") val artistUnicode: String?,
    val covers: Covers,
    val creator: String,
    @SerializedName("favourite_count") val favouriteCount: Int,
    val nsfw: Boolean,
    @SerializedName("play_count") val playCount: Int,
    @SerializedName("preview_url") val previewUrl: String,
    val source: String?,
    val status: String,
    val title: String,
    @SerializedName("title_unicode") val titleUnicode: String?,
    @SerializedName("user_id") val userId: Int,
    val video: Boolean,
    val genre: String?,
    val description: String?,
    val language: String?,
    @SerializedName("has_favourited") val hasFavourited: Boolean,
    val bpm: Double,
    @SerializedName("can_be_hyped") val canByHyped: Boolean,
    @SerializedName("discussion_locked") val discussionLocked: Boolean,
    val hype: Hype,
    @SerializedName("is_scoreable") val isScoreable: Boolean,
    @SerializedName("last_updated") val lastUpdated: String,
    @SerializedName("legacy_thread_url") val legacyThreadUrl: String?,
    val nominations: Nominations,
    val ranked: Int,
    @SerializedName("ranked_date") val rankedDate: String?,
    val storyboard: Boolean,
    @SerializedName("submitted_date") val submittedDate: String,
    val tags: String
)
