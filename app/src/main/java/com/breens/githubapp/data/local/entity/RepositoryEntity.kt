package com.breens.githubapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity

@Entity
data class RepositoryEntity(
    val id: Int,
    val name: String,
    @ColumnInfo(name = "full_name")
    val fullName: String,
    @Embedded(prefix = "owner_info")
    val owner: OwnerEntity,
    @ColumnInfo(name = "stargazers_count")
    val stargazersCount: Int,
    @ColumnInfo(name = "watchers_count")
    val watchersCount: Int,
    @ColumnInfo(name = "forks_count")
    val forksCount: Int,
    val language: String?,
    @ColumnInfo(name = "open_issues")
    val openIssues: Int,
    val description: String,
    @ColumnInfo(name = "updated_at")
    val updatedAt: String
)