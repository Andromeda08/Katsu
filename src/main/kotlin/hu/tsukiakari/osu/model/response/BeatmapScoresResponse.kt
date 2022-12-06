package hu.tsukiakari.osu.model.response

import hu.tsukiakari.osu.model.score.Score

data class BeatmapScoresResponse(
    val scores: List<Score>
)
