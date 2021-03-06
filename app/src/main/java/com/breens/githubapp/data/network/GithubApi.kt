package com.breens.githubapp.data.network

import com.breens.githubapp.data.network.response.FollowersDto
import com.breens.githubapp.data.network.response.FollowingDto
import com.breens.githubapp.data.network.response.RepositoryDto
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

    /**
     *  This endpoint gets who the Github user is following
     **/
    @GET("/users/{user}/following")
    suspend fun getUsersFollowing(
        @Path("user") user: String
    ): List<FollowingDto>

    /**
     *  This endpoint gets a Github user's repos
     **/
    @GET("/users/{user}/repos")
    suspend fun getUsersRepos(
        @Path("user") user: String
    ): List<RepositoryDto>
}