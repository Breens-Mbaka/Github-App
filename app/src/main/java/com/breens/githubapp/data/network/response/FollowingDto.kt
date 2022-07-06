package com.breens.githubapp.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FollowingDto(
    val id: Int?,
    @SerialName("avatar_url")
    val avatarUrl: String?,
    val login: String?,
)