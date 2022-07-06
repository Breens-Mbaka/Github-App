package com.breens.githubapp.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Followers(
    val id: Int?,
    @SerialName("avatar_url")
    val avatarUrl: String?,
    val login: String?,
)