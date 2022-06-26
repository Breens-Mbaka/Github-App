package com.breens.githubapp.data.network

import com.breens.githubapp.data.network.response.FollowersDto
import com.breens.githubapp.data.network.response.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    /**
     * This endpoint gets a Github user's profile information
     **/
    @GET("/users/{user}")
    suspend fun getUserProfile(
        @Path("user") user: String
    ): UserDto

    /**
     *  This endpoint gets a Github user's followers
    **/
    @GET("/users/{user}/followers")
    suspend fun getUsersFollowers(
        @Path("user") user: String
    ): List<FollowersDto>
}