package com.breens.githubapp.data.local.mappers

import com.breens.githubapp.data.local.entity.UserEntity
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