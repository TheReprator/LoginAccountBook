package dev.reprator.khatabook

import retrofit2.Response

class GitHubRepo {

    suspend fun getAuthCode(
        clientId:String,
        clientSecret:String,
        code:String
    ): Response<GithubAccessToken> {

        return RetrofitInstance.api.getAccessToken(clientId, clientSecret, code)
    }
}