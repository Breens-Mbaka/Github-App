package com.breens.githubapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Contextual

@Entity
data class UserEntity(
    val avatar_url: String?,
    val bio: String?,
    val followers: Int?,
    val following: Int?,
    @PrimaryKey
    val id: Int?,
    val location: String?,
    val login: String?,
    val name: String?,
    val public_repos: Int?
)