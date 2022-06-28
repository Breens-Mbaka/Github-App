package com.breens.githubapp.data.network.response

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val avatar_url: String?,
    val bio: String?,
    val followers: Int?,
    val following: Int?,
    val id: Int?,
    val location: String?,
    val login: String?,
    val name: String?,
    val public_repos: Int?
)