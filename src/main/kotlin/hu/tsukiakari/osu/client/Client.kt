package hu.tsukiakari.osu.client

import hu.tsukiakari.osu.client.routes.*
import hu.tsukiakari.osu.model.authentication.ClientCredentialsReqBody
import hu.tsukiakari.osu.model.authentication.TokenResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.gson.*

class Client(
    private val clientId: Int,
    private val clientSecret: String
) {
    private val tokens = mutableListOf<BearerTokens>()

    // HttpClient used to communicate with the osu! api
    private val osuClient: HttpClient = HttpClient(CIO) {
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "osu.ppy.sh"
                path("api/v2/")
            }
        }
        install(ContentNegotiation) { gson() }
        install(Auth) {
            bearer {
                loadTokens {
                    val bearer = BearerTokens(fetchToken().accessToken, "")
                    tokens.add(bearer)
                    bearer
                }
                refreshTokens {
                    val bearer = BearerTokens(fetchToken().accessToken, "")
                    tokens.add(bearer)
                    bearer
                }
            }
        }
    }

    // Supported api routes
    val users: UsersRoute       = UsersRoute(osuClient)
    val beatmaps: BeatmapRoute  = BeatmapRoute(osuClient)
    val rankings: RankingsRoute = RankingsRoute(osuClient)

    // Token fetching client
    private val tokenClient: HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) { gson() }
    }

    /**
     * Request access token from api.
     * @return TokenResponse object
     */
    private suspend fun fetchToken(): TokenResponse =
        tokenClient.post("https://osu.ppy.sh/oauth/token") {
            setBody(ClientCredentialsReqBody(clientId = clientId, clientSecret = clientSecret))
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }.body();
}