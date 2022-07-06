package com.breens.githubapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity
data class FollowersEntity(
    @PrimaryKey
    val id: Int?,
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String?,
    val login: String?,
)
