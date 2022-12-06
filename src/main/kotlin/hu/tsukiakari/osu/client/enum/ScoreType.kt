package hu.tsukiakari.osu.client.enum

import io.ktor.util.*

enum class ScoreType(val str: String) {
    BEST("best"),
    FIRSTS("firsts"),
    RECENT("recent")
}

fun strToScoreType(str: String): ScoreType = when (str) {
    ScoreType.BEST.str -> ScoreType.BEST
    ScoreType.FIRSTS.str -> ScoreType.FIRSTS
    ScoreType.RECENT.str -> ScoreType.RECENT
    else -> throw IllegalArgumentException("Unknown score type: \" $str \"")
}