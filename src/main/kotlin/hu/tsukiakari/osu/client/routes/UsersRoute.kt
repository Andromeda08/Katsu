package hu.tsukiakari.osu.client.routes

import hu.tsukiakari.osu.client.enum.PlayMode
import hu.tsukiakari.osu.client.enum.ScoreType
import hu.tsukiakari.osu.model.score.Score
import hu.tsukiakari.osu.model.user.User
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class UsersRoute(private val client: HttpClient) {
    /**
     * Fetch user data by id, this is the preferred method.
     * @param id the id of the user
     * @param mode osu! playmode
     * @return User object
     */
    suspend fun getUser(id: Int, mode: PlayMode = PlayMode.OSU): User =
        client.get("users/$id/${mode.url}") {
            parameter("key", "id")
        }.body();

    /**
     * Fetch user data by username.
     * @param username osu! username
     * @param mode osu! playmode
     * @return User object
     */
    suspend fun getUser(username: String, mode: PlayMode = PlayMode.OSU): User =
        client.get("users/$username/${mode.url}") {
            parameter("key", "username")
        }.body();

    /**
     * Fetch scores for a user.
     * @param userId the id of the user
     * @param mode osu! playmode
     * @param type type of scores to fetch
     * @param includeFails should the response contain failed scores,
     * @param limit amount of scores to fetch,
     * @param offset pagination offset
     * @return List of Score objects
     */
    suspend fun getUserScores(
        userId: Int,
        mode: PlayMode = PlayMode.OSU,
        type: ScoreType = ScoreType.BEST,
        includeFails: Boolean = false,
        limit: Int = 10,
        offset: Int = 1
    ): List<Score> = client.get("users/$userId/scores/${type.str}") {
        parameter("include_fails", if (includeFails) "1" else "0")
        parameter("mode", mode.url)
        parameter("limit", limit.toString())
        parameter("offset", offset.toString())
    }.body()
}