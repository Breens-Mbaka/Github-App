package com.breens.githubapp.data.network

import com.breens.githubapp.data.network.response.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    /**
     * This endpoint get a Github user's profile information
     **/
    @GET("/users/{user}")
    suspend fun getUserProfile(
        @Path("user") user: String
    ): UserDto
}