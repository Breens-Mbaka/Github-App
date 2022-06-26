package com.breens.githubapp.data.local.mappers

import com.breens.githubapp.data.local.entity.UserEntity
import com.breens.githubapp.domain.models.User

internal fun UserEntity.toDomain(): User {
    return User(
        this.id,
        this.avatarUrl,
        this.login,
        this.name,
        this.location,
        this.bio,
        this.publicRepos
    )
}