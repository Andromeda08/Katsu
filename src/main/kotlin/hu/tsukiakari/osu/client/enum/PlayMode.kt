package hu.tsukiakari.osu.client.enum

import io.ktor.util.*

enum class PlayMode(
    val str: String,
    val url: String
) {
    OSU("osu!standard", "osu"),
    TAIKO("osu!taiko", "taiko"),
    FRUITS("osu!catch", "fruits"),
    MANIA("osu!mania", "mania")
}

fun strToPlayMode(str: String): PlayMode = when (str) {
    PlayMode.TAIKO.str -> PlayMode.TAIKO
    PlayMode.FRUITS.str -> PlayMode.FRUITS
    PlayMode.MANIA.str -> PlayMode.MANIA
    PlayMode.OSU.str -> PlayMode.OSU
    else -> throw IllegalArgumentException("Unknown play mode: \" $str \"")
}
