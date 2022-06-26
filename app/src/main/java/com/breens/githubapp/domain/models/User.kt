package com.breens.githubapp.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val name: String,
    val location: String,
    val bio: String,
    @SerialName("public_repos")
    val publicRepos: Int,
)