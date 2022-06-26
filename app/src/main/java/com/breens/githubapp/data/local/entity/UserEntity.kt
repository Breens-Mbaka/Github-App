package com.breens.githubapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "user"
)
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val location: String,
    val bio: String,
    @ColumnInfo(name = "public_repos")
    val publicRepos: Int,
)
