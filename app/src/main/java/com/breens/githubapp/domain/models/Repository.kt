package com.breens.githubapp.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Repository(
    val id: Int,
    val name: String,
    @SerialName("full_name")
    val fullName: String,
    val owner: Owner,
    @SerialName("stargazers_count")
    val stargazersCount: Int,
    @SerialName("watchers_count")
    val watchersCount: Int,
    @SerialName("forks_count")
    val forksCount: Int,
    val language: String?,
    @SerialName("open_issues")
    val openIssues: Int,
    val description: String,
    @SerialName("updated_at")
    val updatedAt: String
)