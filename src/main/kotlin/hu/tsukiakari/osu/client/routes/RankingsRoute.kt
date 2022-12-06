package hu.tsukiakari.osu.client.routes

import hu.tsukiakari.osu.client.enum.ManiaVariant
import hu.tsukiakari.osu.client.enum.PlayMode
import hu.tsukiakari.osu.model.ranking.CountryEntry
import hu.tsukiakari.osu.model.ranking.Ranking
import hu.tsukiakari.osu.model.ranking.UserEntry
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class RankingsRoute(private val client: HttpClient) {
    /**
     * Get performance (pp) rankings for a playmode
     * @param mode osu! playmode
     * @param variant mania variant (optional)
     */
    suspend fun getPerformanceRankings(
        mode: PlayMode = PlayMode.OSU,
        variant: ManiaVariant?
    ): Ranking<UserEntry> =
        client.get("rankings/${mode.url}/performance") {
            if (mode == PlayMode.MANIA) parameter("variant", variant?.str ?: ManiaVariant.FOUR.str)
        }.body()

    /**
     * Get score rankings for a playmode
     * @param mode osu! playmode
     * @param variant mania variant (optional)
     */
    suspend fun getScoreRankings(
        mode: PlayMode = PlayMode.OSU,
        variant: ManiaVariant?
    ): Ranking<UserEntry> =
        client.get("rankings/${mode.url}/score") {
            if (mode == PlayMode.MANIA) parameter("variant", variant?.str ?: ManiaVariant.FOUR.str)
        }.body()

    /**
     * Get country rankings for a playmode. (ordered by performance)
     * @param mode osu! playmode
     * @param variant mania variant (optional)
     */
    suspend fun getCountryRankings(
        mode: PlayMode = PlayMode.OSU,
        variant: ManiaVariant?
    ): Ranking<CountryEntry> =
        client.get("rankings/${mode.url}/country") {
            if (mode == PlayMode.MANIA) parameter("variant", variant?.str ?: ManiaVariant.FOUR.str)
        }.body()
}