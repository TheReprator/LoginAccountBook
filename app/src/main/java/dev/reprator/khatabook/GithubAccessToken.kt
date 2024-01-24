package dev.reprator.khatabook

import com.google.gson.annotations.SerializedName


data class GithubAccessToken(
    @SerializedName("access_token")
    val accessToken:String,
    @SerializedName("token_type")
    val tokenType:String,
)