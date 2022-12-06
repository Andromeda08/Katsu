package hu.tsukiakari.osu.client.routes

import hu.tsukiakari.osu.client.enum.PlayMode
import hu.tsukiakari.osu.model.beatmap.Beatmap
import hu.tsukiakari.osu.model.beatmap.attributes.BeatmapAttributes
import hu.tsukiakari.osu.model.response.BeatmapScoreResponse
import hu.tsukiakari.osu.model.response.BeatmapScoresResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class BeatmapRoute(private val client: HttpClient) {
    /**
     * Fetch beatmap details by id
     * @param beatmapId id of beatmap
     * @return Beatmap object
     */
    suspend fun getBeatmap(beatmapId: Int): Beatmap = client.get("beatmaps/$beatmapId").body()

    /**
     * Fetch multiple (max. 50) beatmaps at once.
     * @param beatmapIds list of beatmap ids
     * @return List of Beatmap objects
     */
    suspend fun getBeatmaps(beatmapIds: List<Int>): List<Beatmap> =
        client.get("beatmaps") {
            for (i in 0..50) {
                parameter("ids[$i]", beatmapIds[i])
            }
        }.body()

    /**
     * Get beatmap attributes for a play mode
     * @param beatmapId id of beatmap
     * @param ruleset osu! play mode
     * @return BeatmapAttributes object
     */
    suspend fun getBeatmapAttributes(beatmapId: Int, ruleset: PlayMode = PlayMode.OSU): BeatmapAttributes =
        client.post("beatmaps/$beatmapId/attributes") {
            parameter("ruleset", ruleset.url)
        }.body()

    /**
     * Lookup scores for a certain beatmap
     * @param beatmapId beatmap id
     * @param mode osu! playmode
     * @return BeatmapScoresResponse object
     */
    suspend fun getBeatmapScores(beatmapId: Int, mode: PlayMode = PlayMode.OSU): BeatmapScoresResponse =
        client.get("beatmaps/$beatmapId/scores") {
            parameter("mode", mode.url)
        }.body()

    /**
     * Get the best score of a user on a beatmap
     * @param beatmapId id of the beatmap
     * @param userId osu! user id
     * @param mode osu! playmode
     * @return BeatmapScoreResponse object
     */
    suspend fun getUserScoreOnBeatmap(beatmapId: Int, userId: Int, mode: PlayMode = PlayMode.OSU): BeatmapScoreResponse =
        client.get("beatmaps/$beatmapId/scores/users/$userId") {
            parameter("mode", mode.url)
        }.body()

    /**
     * Get the all scores of a user on a beatmap
     * @param beatmapId id of the beatmap
     * @param userId osu! user id
     * @param mode osu! playmode
     * @return BeatmapScoresResponse object
     */
    suspend fun getAllUserScoresOnBeatmap(beatmapId: Int, userId: Int, mode: PlayMode = PlayMode.OSU): BeatmapScoresResponse =
        client.get("beatmaps/$beatmapId/scores/users/$userId/all") {
            parameter("mode", mode.url)
        }.body()
}