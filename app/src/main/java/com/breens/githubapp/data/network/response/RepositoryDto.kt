package com.breens.githubapp.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryDto(
    val id: Int,
    val name: String,
    @SerialName("full_name")
    val fullName: String,
    val owner: OwnerDto,
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