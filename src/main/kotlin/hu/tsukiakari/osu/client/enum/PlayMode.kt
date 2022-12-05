package hu.tsukiakari.osu.client.enum

enum class PlayMode(
    val str: String,
    val url: String
) {
    OSU("osu!standard", "osu"),
    TAIKO("osu!taiko", "taiko"),
    FRUITS("osu!catch", "fruits"),
    MANIA("osu!mania", "mania")
}