package hu.tsukiakari.osu.model.ranking

data class Ranking<T>(
    val cursor: Cursor,
    val ranking: List<T>
)
