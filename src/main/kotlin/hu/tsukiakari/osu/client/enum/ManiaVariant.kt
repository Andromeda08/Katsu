package hu.tsukiakari.osu.client.enum

enum class ManiaVariant(val str: String) {
    FOUR("4k"),
    SEVEN("7k")
}

fun strToManiaVariant(str: String): ManiaVariant = when (str) {
    ManiaVariant.FOUR.str -> ManiaVariant.FOUR
    ManiaVariant.SEVEN.str -> ManiaVariant.SEVEN
    else -> throw IllegalArgumentException("Unknown osu!mania variant: \" $str \"")
}