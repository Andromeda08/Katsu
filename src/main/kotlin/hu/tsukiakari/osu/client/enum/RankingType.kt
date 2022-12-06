package hu.tsukiakari.osu.client.enum

enum class RankingType(val str: String, val url: String) {
    //CHARTS("Spotlight", "charts"),
    COUNTRY("Country", "country"),
    PERFORMANCE("Performance", "performance"),
    SCORE("Score", "score")
}

fun strToRankingType(str: String): RankingType = when (str) {
    RankingType.COUNTRY.str -> RankingType.COUNTRY
    RankingType.PERFORMANCE.str -> RankingType.PERFORMANCE
    RankingType.SCORE.str -> RankingType.SCORE
    else -> throw IllegalArgumentException("Unknown ranking type: \" $str \"")
}