package com.breens.githubapp.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: Int,
    @SerialName("avatar_url")
    val avatarUrl: String,
    val login: String,
    val name: String,
    val location: String,
    val bio: String,
    @SerialName("public_repos")
    val publicRepos: Int,
)