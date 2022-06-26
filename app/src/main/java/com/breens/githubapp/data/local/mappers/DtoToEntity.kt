package com.breens.githubapp.data.local.mappers

import com.breens.githubapp.data.local.entity.FollowersEntity
import com.breens.githubapp.data.local.entity.UserEntity
import com.breens.githubapp.data.network.response.FollowersDto
import com.breens.githubapp.data.network.response.UserDto

internal fun UserDto.toEntity(): UserEntity {
    return UserEntity(
        this.id,
        this.avatarUrl,
        this.login,
        this.name,
        this.location,
        this.bio,
        this.publicRepos
    )
}

internal fun FollowersDto.toEntity(): FollowersEntity {
    return FollowersEntity(
        this.id,
        this.avatarUrl,
        this.login,
        this.name,
        this.location,
        this.bio,
        this.publicRepos
    )
}