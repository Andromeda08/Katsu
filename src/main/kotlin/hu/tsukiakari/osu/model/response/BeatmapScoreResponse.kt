package hu.tsukiakari.osu.model.response

import hu.tsukiakari.osu.model.score.Score

data class BeatmapScoreResponse(
    val position: Int,
    val score: Score
)
