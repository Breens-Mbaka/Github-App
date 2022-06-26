package com.breens.githubapp.data.local.mappers

import com.breens.githubapp.data.local.entity.*
import com.breens.githubapp.data.network.response.*

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

internal fun FollowingDto.toEntity(): FollowingEntity {
    return FollowingEntity(
        this.id,
        this.avatarUrl,
        this.login,
        this.name,
        this.location,
        this.bio,
        this.publicRepos
    )
}

internal fun RepositoryDto.toEntity(): RepositoryEntity {
    return RepositoryEntity(
        this.id,
        this.name,
        this.fullName,
        this.owner.toEntity(),
        this.stargazersCount,
        this.watchersCount,
        this.forksCount,
        this.language,
        this.openIssues,
        this.description,
        this.updatedAt,
    )
}

internal fun OwnerDto.toEntity(): OwnerEntity {
    return OwnerEntity(
        this.avatarUrl
    )
}