package hu.tsukiakari.osu.model.authentication

import com.google.gson.annotations.SerializedName

data class ClientCredentialsReqBody(
    @SerializedName("client_id")
    val clientId: Int,

    @SerializedName("client_secret")
    val clientSecret: String,

    @SerializedName("grant_type")
    val grantType: String = "client_credentials",

    val scope: String = "public"
)
