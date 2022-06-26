package com.breens.githubapp.data.local.entity

import androidx.room.ColumnInfo

data class OwnerEntity(
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String
)